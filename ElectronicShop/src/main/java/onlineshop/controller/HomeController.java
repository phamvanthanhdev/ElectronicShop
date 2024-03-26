package onlineshop.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import onlineshop.dao.CategoryDao;
import onlineshop.entity.CategoryEntity;
import onlineshop.entity.ProductEntity;


@Transactional
@Controller
public class HomeController {
	@Autowired
	private SessionFactory factory;

	@Autowired
	private CategoryDao categoryDao;
	
	@RequestMapping(value = {"index", ""})
	public String index(HttpServletRequest request, ModelMap model) {
		List<CategoryEntity> listCatelogies = categoryDao.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		ProductEntity prod = categoryDao.getProductActivate();
		model.addAttribute("p", prod);
		
		List<ProductEntity> randomProducts = categoryDao.getRandomProducts();
		model.addAttribute("prods", randomProducts);
		
		List<ProductEntity> specials = categoryDao.getSpecialProducts();
		model.addAttribute("products", specials);
		return "user/index";
	}
}
