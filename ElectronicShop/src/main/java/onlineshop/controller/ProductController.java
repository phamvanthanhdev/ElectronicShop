package onlineshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import onlineshop.dao.ProductDao;
import onlineshop.entity.CategoryEntity;
import onlineshop.entity.ProductEntity;

@Controller
@Transactional
public class ProductController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	ProductDao productDao;
	
	@RequestMapping(value = "/product/list-by-category/{id}", method = RequestMethod.GET)
	public String listByCategory(@PathVariable("id") int id, ModelMap model,
			HttpServletRequest request) {
		List<CategoryEntity> listCatelogies = productDao.getCategories();
		model.addAttribute("listCate", listCatelogies);

		List<ProductEntity> listProducts = productDao.getProductsByCategory(id);
		
		PagedListHolder pagedListHolder = new PagedListHolder(listProducts);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		 pagedListHolder.setPage(page);
		 pagedListHolder.setMaxLinkedPages(5);
		 pagedListHolder.setPageSize(9);
		 
		 model.addAttribute("pagedListHolder", pagedListHolder);
		//model.addAttribute("products", listProducts);
		 model.addAttribute("value", "/product/list-by-category/"+id);
		 
		
		return "user/product/list";
	}
	
	@RequestMapping(value = "/product/list-by-keyword")
	public String listByKeywords(@RequestParam("keywords") String keywords, 
			HttpServletRequest request,ModelMap model) {
		List<CategoryEntity> listCatelogies = productDao.getCategories();
		model.addAttribute("listCate", listCatelogies);

		List<ProductEntity> listProducts = productDao.getProductsByKeywords(keywords);
		
		PagedListHolder pagedListHolder = new PagedListHolder(listProducts);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		 pagedListHolder.setPage(page);
		 pagedListHolder.setMaxLinkedPages(5);
		 pagedListHolder.setPageSize(9);
		 
		 model.addAttribute("pagedListHolder", pagedListHolder);
		 model.addAttribute("value", "/product/list-by-keyword?keywords="+keywords);
		
		return "user/product/list";
	}
	
	@RequestMapping(value = "/product/detail/{id}")
	public String detail(@PathVariable("id") int id, 
			ModelMap model) {
		List<CategoryEntity> listCatelogies = productDao.getCategories();
		model.addAttribute("listCate", listCatelogies);

		ProductEntity product = productDao.getProductById(id);
		model.addAttribute("prod", product);
		
		List<ProductEntity> listProducts = productDao.getProductsByCategory(product.getCategoryEntity().getId());
		model.addAttribute("listProduct", listProducts);
		
		// Gia su customer la admin
		String cusID = "admin";

		List<ProductEntity> listFavo = productDao.getProductsFavoriteByCutomer(cusID);
		model.addAttribute("favorites", listFavo);
		
		return "user/product/detail";
	}
	
	@RequestMapping(value = "/product/new-product")
	public String newProduct(ModelMap model, HttpServletRequest request) {
		List<CategoryEntity> listCatelogies = productDao.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		List<ProductEntity> listProducts = productDao.getNewProducts();
		
		PagedListHolder pagedListHolder = new PagedListHolder(listProducts);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		 pagedListHolder.setPage(page);
		 pagedListHolder.setMaxLinkedPages(5);
		 pagedListHolder.setPageSize(9);
		 
		 model.addAttribute("pagedListHolder", pagedListHolder);
		 model.addAttribute("value", "/product/new-product");
		
		model.addAttribute("message", "Sản phẩm mới nhất");
		
		return "user/product/list";
	}
	
	@RequestMapping(value = "/product/best-sales")
	public String bestSalesProduct(ModelMap model, HttpServletRequest request) {
		List<CategoryEntity> listCatelogies = productDao.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		List<ProductEntity> listProducts = productDao.getSalesProducts();
		PagedListHolder pagedListHolder = new PagedListHolder(listProducts);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		 pagedListHolder.setPage(page);
		 pagedListHolder.setMaxLinkedPages(5);
		 pagedListHolder.setPageSize(9);
		 
		 model.addAttribute("pagedListHolder", pagedListHolder);
		 model.addAttribute("value", "/product/best-sales");
		
		model.addAttribute("message", "Sản phẩm bán chạy nhất");
		
		return "user/product/list";
	}
	
	@RequestMapping(value = "/product/discount-product")
	public String discountProduct(ModelMap model, HttpServletRequest request) {
		List<CategoryEntity> listCatelogies = productDao.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		List<ProductEntity> listProducts = productDao.getDiscountProducts();
		PagedListHolder pagedListHolder = new PagedListHolder(listProducts);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		 pagedListHolder.setPage(page);
		 pagedListHolder.setMaxLinkedPages(5);
		 pagedListHolder.setPageSize(9);
		 
		 model.addAttribute("pagedListHolder", pagedListHolder);
		 model.addAttribute("value", "/product/discount-product");
		
		model.addAttribute("message", "Sản phẩm giảm giá nhiều nhất");
		
		return "user/product/list";
	}
	
}




















