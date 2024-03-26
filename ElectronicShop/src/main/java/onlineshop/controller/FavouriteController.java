package onlineshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import onlineshop.entity.CategoryEntity;
import onlineshop.entity.CustomerEntity;
import onlineshop.entity.FavouriteEntity;
import onlineshop.entity.ProductEntity;

@Transactional
@Controller
public class FavouriteController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping(value = "/product/favourite-product", method = RequestMethod.GET)
	public String listFavorites( ModelMap model,
			HttpServletRequest request) {
		List<CategoryEntity> listCatelogies = this.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		HttpSession session = request.getSession();
		CustomerEntity customer = (CustomerEntity) session.getAttribute("account");
		String cusId = customer.getId();
		List<ProductEntity> listProducts = this.getProductsFavoriteByCutomer(cusId);
		
		PagedListHolder pagedListHolder = new PagedListHolder(listProducts);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		 pagedListHolder.setPage(page);
		 pagedListHolder.setMaxLinkedPages(5);
		 pagedListHolder.setPageSize(9);
		 
		 model.addAttribute("pagedListHolder", pagedListHolder);
		 model.addAttribute("value", "/product/favourite-product");
		 
		 model.addAttribute("message", "Sản phẩm yêu thích của bạn");
		 
		return "user/product/list";
	}
	
	@RequestMapping(value = "/product/favourite-product/{id}", method = RequestMethod.GET)
	public String addFavorite( ModelMap model,@PathVariable("id") int id,
			HttpServletRequest request) {
		List<CategoryEntity> listCatelogies = this.getCategories();
		model.addAttribute("listCate", listCatelogies);

		HttpSession session = request.getSession();
		CustomerEntity customer = (CustomerEntity) session.getAttribute("account");
		String cusId = customer.getId();
		
		ProductEntity p = getProductById(id);
		
		FavouriteEntity favo = new FavouriteEntity();
		favo.setCustomer(customer);
		favo.setProduct(p);
		this.createFavorite(favo);
		
		List<ProductEntity> listProducts = this.getProductsFavoriteByCutomer(cusId);
		
		PagedListHolder pagedListHolder = new PagedListHolder(listProducts);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		 pagedListHolder.setPage(page);
		 pagedListHolder.setMaxLinkedPages(5);
		 pagedListHolder.setPageSize(9);
		 
		 model.addAttribute("pagedListHolder", pagedListHolder);
		 model.addAttribute("value", "/product/favourite-product");
		 
		 model.addAttribute("message", "Sản phẩm yêu thích của bạn");
		 
		return "user/product/list";
	}
	
	
	//     ----- DAO -----
	
	public List<ProductEntity> getProductsFavoriteByCutomer(String id) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT DISTINCT f.product FROM FavouriteEntity f where f.customer.id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<ProductEntity> list = query.list();
		return list;
	}

	public ProductEntity getProductById(int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity p where p.id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		ProductEntity product = (ProductEntity) query.list().get(0);
		return product;
	}
	
	public List<CategoryEntity> getCategories() {
		Session session = factory.getCurrentSession();
		String hql = "FROM CategoryEntity";
		Query query = session.createQuery(hql);
		List<CategoryEntity> list = query.list();
		return list;
	}
	
	public FavouriteEntity createFavorite(FavouriteEntity favo) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(favo);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		return favo;
	}
	
	public CustomerEntity getCustomerById(String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CustomerEntity p where p.id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<CustomerEntity> listCustomer = query.list();
		CustomerEntity customerEntity = new CustomerEntity();
		if (listCustomer.size() > 0) {
			customerEntity = (CustomerEntity) query.list().get(0);
			return customerEntity;
		} else {
			return null;
		}
	}
}
