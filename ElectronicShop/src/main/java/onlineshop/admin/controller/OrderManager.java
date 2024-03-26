package onlineshop.admin.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.jasper.tagplugins.jstl.core.Redirect;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import onlineshop.entity.CartItemsEntity;
import onlineshop.entity.OrderDetail;
import onlineshop.entity.OrderEntity;

@Transactional
@Controller
public class OrderManager {
	@Autowired
	SessionFactory factory;
	
	
	@RequestMapping(value="admin/order/index")
	public String index(ModelMap model) {
		OrderEntity order = new OrderEntity();
		List<OrderEntity> list = this.getCategories();
		
		model.addAttribute("entity", order);
		model.addAttribute("list", list);
		return "admin/order/order";
	}
	
	@RequestMapping(value="admin/order/edit/{id}")
	public String edit(ModelMap model, @PathVariable("id") int id, @ModelAttribute("entity") OrderEntity order) {
		OrderEntity order1 = this.getOrderById(id);
		
		List<OrderEntity> list = this.getCategories();
		
		List<OrderDetail> orderDetails = this.getOrderDetailByOrderId(id);
		model.addAttribute("details", orderDetails);
		
		model.addAttribute("list", list);
		
		model.addAttribute("entity", order1);
		return "admin/order/order";
	}
	
	
	@RequestMapping(value="admin/order/update")
	public String update(ModelMap model,@ModelAttribute("entity") OrderEntity order) {
		
		this.updateOrder(order);
		model.addAttribute("message", "Cập nhật thành công!");
		return "redirect:/admin/order/edit/"+ order.getId();
	}
	
	@RequestMapping(value="admin/order/delete/{id}")
	public String delete(@PathVariable(value = "id") int id, ModelMap model) {
		OrderEntity order = this.getOrderById(id);
		this.deleteCate(order);
		 model.addAttribute("message", "Xóa thành công!"); 
		 return "redirect:/admin/order/index";
	}
	
	
	@RequestMapping(value="admin/order/delete")
	public String delete2(@ModelAttribute(value = "entity") OrderEntity order, ModelMap model) {
		this.deleteCate(order);
			
		 model.addAttribute("message", "Xóa thành công!"); 
		 return "redirect:/admin/order/index";
	}
	
	// DAO
	
	public List<OrderDetail> getOrderDetailByOrderId(int oid) {
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderDetail o where o.orderEntity.id=:oid";
		Query query = session.createQuery(hql);
		query.setParameter("oid", oid);
		List<OrderDetail> list = query.list();
		return list;
	}
	
	public void deleteOrder(OrderEntity cate) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(cate);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
	}
	
	public OrderEntity updateOrder(OrderEntity cate) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(cate);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		return cate;
	}
	
	public OrderEntity createOrder(OrderEntity cate) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(cate);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		return cate;
	}
	
	public OrderEntity getOrderById(int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderEntity c where c.id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<OrderEntity> listcate = query.list();
		OrderEntity cate  = new OrderEntity();
		if(listcate.size() > 0) {
			cate = (OrderEntity) query.list().get(0);
			return cate;
		}
		return null;
	}
	
	public List<OrderEntity> getCategories() {
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderEntity";
		Query query = session.createQuery(hql);
		List<OrderEntity> list = query.list();
		return list;
	}
	
	public OrderEntity deleteCate(OrderEntity item) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(item);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		return item;
	}
	
}

















