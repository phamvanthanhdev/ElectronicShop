package onlineshop.admin.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import onlineshop.entity.OrderDetail;
import onlineshop.entity.OrderEntity;

@Transactional
@Controller
public class InventoryReport {

	@Autowired
	SessionFactory factory;

	@RequestMapping(value = "admin/inventory/index")
	public String index(ModelMap model) {
		model.addAttribute("data", this.inventory());
		
		return "admin/report/inventory";
	}
	
	@RequestMapping(value = "/admin/revenue/category")
	public String revenueCategory(ModelMap model) {
		model.addAttribute("data", this.revenueByCategory());
		
		return "admin/report/revenue-category";
	}
	
	@RequestMapping(value = "/admin/revenue/customer")
	public String revenueCustomer(ModelMap model) {
		model.addAttribute("data", this.revenueByCustomer());
		
		return "admin/report/revenue-customer";
	}
	
	@RequestMapping(value = "/admin/revenue/year")
	public String revenueYear(ModelMap model) {
		model.addAttribute("data", this.revenueByYear());
		
		return "admin/report/revenue-year";
	}
	
	@RequestMapping(value = "/admin/revenue/month")
	public String revenueMonth(ModelMap model) {
		model.addAttribute("data", this.revenueByMonth());
		
		return "admin/report/revenue-month";
	}
	
	@RequestMapping(value = "/admin/revenue/quarter")
	public String revenueQuater(ModelMap model) {
		model.addAttribute("data", this.revenueByQuarter());
		
		return "admin/report/revenue-quarter";
	}
	
	
	
	//------    DAO ----------
	
	public List<Object[]> inventory() {
		String hql = "SELECT p.categoryEntity.name, "
				+"SUM(p.quantity), "
				+"SUM(p.price*p.quantity), "
				+"MIN(p.price), "
				+"MAX(p.price), "
				+"AVG(p.price) "
				+ "FROM ProductEntity p GROUP BY p.categoryEntity.name";
		
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	public List<Object[]> revenueByCategory() {

		String hql = "SELECT d.productEntity.categoryEntity.name, "
				+"SUM(d.quantity), "
				+"SUM(d.unitPrice*d.quantity), "
				+"MIN(d.unitPrice), "
				+"MAX(d.unitPrice), "
				+"AVG(d.unitPrice) "
				+ "FROM OrderDetail d GROUP BY d.productEntity.categoryEntity.name";
		
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	public List<Object[]> revenueByCustomer() {
		String hql = "SELECT d.orderEntity.customer.id, "
				+"SUM(d.quantity), "
				+"SUM(d.unitPrice*d.quantity), "
				+"MIN(d.unitPrice), "
				+"MAX(d.unitPrice), "
				+"AVG(d.unitPrice) "
				+ "FROM OrderDetail d GROUP BY d.orderEntity.customer.id "
				+ "";
		
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	public List<Object[]> revenueByYear() {
		String hql = "SELECT YEAR(d.orderEntity.orderDate), "
				+"SUM(d.quantity), "
				+"SUM(d.unitPrice*d.quantity), "
				+"MIN(d.unitPrice), "
				+"MAX(d.unitPrice), "
				+"AVG(d.unitPrice) "
				+ "FROM OrderDetail d "
				+ "GROUP BY YEAR(d.orderEntity.orderDate) "
				+ "ORDER BY YEAR(d.orderEntity.orderDate) DESC";
		
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	public List<Object[]> revenueByQuarter() {
		String hql = "SELECT CEILING(MONTH(d.orderEntity.orderDate) / 3.0), "
				+"SUM(d.quantity), "
				+"SUM(d.unitPrice*d.quantity), "
				+"MIN(d.unitPrice), "
				+"MAX(d.unitPrice), "
				+"AVG(d.unitPrice) "
				+ "FROM OrderDetail d "
				+ "GROUP BY CEILING(MONTH(d.orderEntity.orderDate) / 3.0) "
				+ "ORDER BY CEILING(MONTH(d.orderEntity.orderDate) / 3.0) ";
		
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	public List<Object[]> revenueByMonth() {
		String hql = "SELECT MONTH(d.orderEntity.orderDate), "
				+"SUM(d.quantity), "
				+"SUM(d.unitPrice*d.quantity), "
				+"MIN(d.unitPrice), "
				+"MAX(d.unitPrice), "
				+"AVG(d.unitPrice) "
				+ "FROM OrderDetail d "
				+ "GROUP BY MONTH(d.orderEntity.orderDate) "
				+ "ORDER BY MONTH(d.orderEntity.orderDate) ";
		
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}
}
