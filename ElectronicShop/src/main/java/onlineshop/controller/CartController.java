package onlineshop.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import onlineshop.entity.CartEntity;
import onlineshop.entity.CartItemsEntity;
import onlineshop.entity.CategoryEntity;
import onlineshop.entity.CustomerEntity;
import onlineshop.entity.OrderDetail;
import onlineshop.entity.OrderEntity;
import onlineshop.entity.ProductEntity;

@Controller
@Transactional
public class CartController {
	@Autowired
	SessionFactory factory;
	/// cart/list-cart-item

	@RequestMapping(value = "/cart/list-cart-item", method = RequestMethod.GET)
	public String listCartItem(ModelMap model, HttpServletRequest request) {
		List<CategoryEntity> listCatelogies = this.getCategories();
		model.addAttribute("listCate", listCatelogies);

		HttpSession session = request.getSession();
		CustomerEntity acc = (CustomerEntity) session.getAttribute("account");
		String cusId = acc.getId();
		CartEntity cart = this.getCartById(cusId);

		if (cart != null) {
			List<CartItemsEntity> listItems = this.getListCartItems(cart.getId());
			model.addAttribute("list", listItems);
		}else {
			cart = new CartEntity();
			cart.setCustomerEntity(acc);
			cart.setDescription("Mo ta");
			Date date = java.util.Calendar.getInstance().getTime();
			cart.setCreateAt(date);
			cart.setUpdateAt(date);
			cart = this.createCart(cart);
			
		}

		return "user/cart/list";
	}

	@RequestMapping(value = "/cart/add-cart-item/{id}", method = RequestMethod.GET)
	public String addCartItems(ModelMap model, HttpServletRequest request
			,@PathVariable("id") int id) {

		HttpSession session = request.getSession();
		CustomerEntity acc = (CustomerEntity) session.getAttribute("account");
		String cusId = acc.getId();
		CartEntity cart = this.getCartById(cusId);
		
		if(cart == null) {
			cart = new CartEntity();
			cart.setCustomerEntity(acc);
			cart.setDescription("Mo ta");
			Date date = java.util.Calendar.getInstance().getTime();
			cart.setCreateAt(date);
			cart.setUpdateAt(date);
			cart = this.createCart(cart);
		}
		
		ProductEntity product = this.getProductById(id);

		List<CartItemsEntity> listItems = this.getListCartItems(cart.getId());

		boolean check = false;
		for (CartItemsEntity item : listItems) {
			if (item.getProducts().getId() == product.getId()) {
				check = true;
				item.setQuantity(1 + item.getQuantity());
			}
		}
		if (check == false) {
			CartItemsEntity item = new CartItemsEntity();
			item.setCartEntity(cart);
			item.setQuantity(1);
			item.setProducts(product);
			this.createCartItem(item);
		}

		listItems = this.getListCartItems(cart.getId());

		model.addAttribute("list", listItems);
		return "user/cart/list";
	}
	
	@RequestMapping(value="/cart/cart-item/delete/{id}")
	public String deleteCartItems(ModelMap model, HttpServletRequest request
			,@PathVariable("id") int id) {
		CartItemsEntity item = this.getCartItemById(id);
		this.deleteCartItem(item);

		return "redirect:/cart/list-cart-item";
	}
	
	@RequestMapping(value="/cart/cart-item/update")
	public String updateCartItems(ModelMap model, HttpServletRequest request, 
			@RequestParam("quantity") int quantity,
			@RequestParam("id") int id) {
		
		CartItemsEntity item = this.getCartItemById(id);
		item.setQuantity(quantity);
		this.updateCartItem(item);

		return "redirect:/cart/list-cart-item";
	}
	
	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String showFormCheckout(HttpServletRequest request,ModelMap model, @ModelAttribute("OrderEntity") OrderEntity order) {
		List<CategoryEntity> listCatelogies = this.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		HttpSession session = request.getSession();
		CustomerEntity customer = (CustomerEntity) session.getAttribute("account");
		
		CartEntity cart = this.getCartByCustomerId(customer.getId());
		List<CartItemsEntity> listItems = this.getListCartItems(cart.getId());
		model.addAttribute("listItems", listItems);
		
		order.setCustomer(customer);
		order.setOrderDate(new Date());
		order.setAmount(this.totalPriceCart(customer.getId()));
		order.setPaid(false);
		
		return "user/order/checkout";
	}
	

	// ---- DAO ------

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

	public CartEntity createCart(CartEntity cart) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(cart);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		return cart;
	}
	
	public CartEntity getCartById(String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CartEntity c where c.cusEntity.id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<CartEntity> carts = query.list();
		CartEntity cart = new CartEntity();
		if(carts.size() > 0) {
			cart = (CartEntity) query.list().get(0);
			return cart;
		}
		return null;
	}
	
	public List<CartItemsEntity> getListCartItems(int cid) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CartItemsEntity o where o.cartEntity.id=:cid";
		Query query = session.createQuery(hql);
		query.setParameter("cid", cid);
		List<CartItemsEntity> list = query.list();
		return list;
	}
	
	public CartItemsEntity createCartItem(CartItemsEntity item) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(item);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		return item;
	}
	
	public CartItemsEntity deleteCartItem(CartItemsEntity item) {
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
	
	public CartItemsEntity updateCartItem(CartItemsEntity item) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(item);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		return item;
	}
	
	public CartItemsEntity getCartItemById(int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CartItemsEntity c where c.id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		CartItemsEntity items = (CartItemsEntity) query.list().get(0);
		return items;
	}
	
	public List<ProductEntity> getItemsByUser(String cid) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT DISTINCT d.productEntity FROM OrderDetail d where d.orderEntity.customer.id=:cid";
		Query query = session.createQuery(hql);
		query.setParameter("cid", cid);
		List<ProductEntity> list = query.list();
		return list;
	}
	

	public OrderEntity getOrderById(int oid) {
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderEntity o where o.id=:oid";
		Query query = session.createQuery(hql);
		query.setParameter("oid", oid);
		OrderEntity order = (OrderEntity)query.list().get(0);
		return order;
	}
	
	public List<OrderDetail> getOrderDetailByOrderId(int oid) {
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderDetail o where o.orderEntity.id=:oid";
		Query query = session.createQuery(hql);
		query.setParameter("oid", oid);
		List<OrderDetail> list = query.list();
		return list;
	}
	
	
	public List<OrderEntity> getListOrderByCustomerId(String cid) {
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderEntity o where o.customer.id=:cid ORDER BY o.orderDate DESC";
		Query query = session.createQuery(hql);
		query.setParameter("cid", cid);
		List<OrderEntity> list = query.list();
		return list;
	}
	
	
	public CartEntity getCartByCustomerId(String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CartEntity c where c.cusEntity.id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<CartEntity> carts = query.list();
		CartEntity cart = new CartEntity();
		if(carts.size() > 0) {
			cart = (CartEntity) query.list().get(0);
			return cart;
		}
		return null;
	}
	
	public float totalPriceCart(String customerID) {
		CartEntity cart = this.getCartByCustomerId(customerID);
		List<CartItemsEntity> listItems = this.getListCartItems(cart.getId());
		float total = 0;
		for (CartItemsEntity item : listItems) {
			total += item.getQuantity() * item.getProducts().getPrice();
		}
		return total;
	}
}
