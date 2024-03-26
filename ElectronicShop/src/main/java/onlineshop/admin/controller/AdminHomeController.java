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

import onlineshop.entity.CategoryEntity;


@Transactional
@Controller
public class AdminHomeController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping(value="admin/home")
	public String index(ModelMap model) {
		CategoryEntity category = new CategoryEntity();
		List<CategoryEntity> list = this.getCategories();
		
		model.addAttribute("list", list);
		return "admin/home";
	}
	
	@RequestMapping(value="admin/index")
	public String index1(ModelMap model) {
		CategoryEntity category = new CategoryEntity();
		List<CategoryEntity> list = this.getCategories();
		
		model.addAttribute("list", list);
		return "admin/index";
	}
	
	public List<CategoryEntity> getCategories() {
		Session session = factory.getCurrentSession();
		String hql = "FROM CategoryEntity";
		Query query = session.createQuery(hql);
		List<CategoryEntity> list = query.list();
		return list;
	}
	
}
