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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import onlineshop.entity.CartItemsEntity;
import onlineshop.entity.CategoryEntity;
import onlineshop.entity.OrderDetail;

@Transactional
@Controller
public class CategoryManager {
	@Autowired
	SessionFactory factory;
	
	
	@RequestMapping(value="admin/category/index")
	public String index(ModelMap model) {
		CategoryEntity category = new CategoryEntity();
		List<CategoryEntity> list = this.getCategories();
		
		model.addAttribute("entity", category);
		model.addAttribute("list", list);
		return "admin/category/category";
	}
	
	@RequestMapping(value="admin/category/edit/{id}")
	public String edit(ModelMap model, @PathVariable("id") int id, @ModelAttribute("entity") CategoryEntity category) {
		CategoryEntity category1 = this.getCategoryById(id);
		List<CategoryEntity> list = this.getCategories();
	
		
		model.addAttribute("list", list);
		
		model.addAttribute("entity", category1);
		return "admin/category/category";
	}
	
	@RequestMapping(value="admin/category/create")
	public String create(ModelMap model,@ModelAttribute("entity") CategoryEntity category,
			BindingResult errors) {
		
		if(category.getName().trim().length() == 0) {
			errors.rejectValue("name","entity", "Vui lòng nhập tên loại.");
		}
		if (errors.hasErrors()) {
			return "admin/category/category";
		}
		
		this.createCategory(category);
		model.addAttribute("message", "Thêm mới thành công!");
		return "redirect:/admin/category/index";
	}
	
	@RequestMapping(value="admin/category/update")
	public String update(ModelMap model,@ModelAttribute("entity") CategoryEntity category) {
		
		this.updateCategory(category);
		model.addAttribute("message", "Cập nhật thành công!");
		return "redirect:/admin/category/edit/"+ category.getId();
	}
	
	@RequestMapping(value="admin/category/delete/{id}")
	public String delete(@PathVariable(value = "id") int id, ModelMap model) {
		CategoryEntity category = this.getCategoryById(id);
		this.deleteCate(category);
		 model.addAttribute("message", "Xóa thành công!"); 
		 return "redirect:/admin/category/index";
	}
	
	
	@RequestMapping(value="admin/category/delete")
	public String delete2(@ModelAttribute(value = "entity") CategoryEntity category, ModelMap model) {
		this.deleteCate(category);
			
		 model.addAttribute("message", "Xóa thành công!"); 
		 return "redirect:/admin/category/index";
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
	
	public void deleteCategory(CategoryEntity cate) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(cate);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
	}
	
	public CategoryEntity updateCategory(CategoryEntity cate) {
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
	
	public CategoryEntity createCategory(CategoryEntity cate) {
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
	
	public CategoryEntity getCategoryById(int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CategoryEntity c where c.id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<CategoryEntity> listcate = query.list();
		CategoryEntity cate  = new CategoryEntity();
		if(listcate.size() > 0) {
			cate = (CategoryEntity) query.list().get(0);
			return cate;
		}
		return null;
	}
	
	public List<CategoryEntity> getCategories() {
		Session session = factory.getCurrentSession();
		String hql = "FROM CategoryEntity";
		Query query = session.createQuery(hql);
		List<CategoryEntity> list = query.list();
		return list;
	}
	
	public CategoryEntity deleteCate(CategoryEntity item) {
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

















