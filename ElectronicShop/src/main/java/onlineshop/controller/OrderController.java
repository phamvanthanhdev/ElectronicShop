package onlineshop.controller;

import java.util.ArrayList;
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
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import onlineshop.dao.CartDao;
import onlineshop.dao.CategoryDao;
import onlineshop.dao.OrderDao;
import onlineshop.dao.ProductDao;
import onlineshop.entity.CartEntity;
import onlineshop.entity.CartItemsEntity;
import onlineshop.entity.CategoryEntity;
import onlineshop.entity.CustomerEntity;
import onlineshop.entity.OrderDetail;
import onlineshop.entity.OrderEntity;
import onlineshop.entity.ProductEntity;

@Controller
@Transactional
public class OrderController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	CartDao cartDao;
	
	@RequestMapping(value = "/order/checkout", method = RequestMethod.GET)
	public String showFormCheckout(HttpServletRequest request,ModelMap model, @ModelAttribute("order") OrderEntity order) {
		List<CategoryEntity> listCatelogies = categoryDao.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		HttpSession session = request.getSession();
		CustomerEntity customer = (CustomerEntity) session.getAttribute("account");
		
		CartEntity cart = cartDao.getCartByCustomerId(customer.getId());
		List<CartItemsEntity> listItems = cartDao.getListCartItems(cart.getId());
		model.addAttribute("list", listItems);
		
		order.setCustomer(customer);
		order.setOrderDate(new Date());
		order.setAmount(cartDao.totalPriceCart(customer.getId()));
		order.setPaid(false);
		
		model.addAttribute("order", order);
		
		return "user/order/checkout";
	}
	
	@RequestMapping(value = "/order/checkout", method = RequestMethod.POST)
	public String checkout(HttpServletRequest request,ModelMap model, @ModelAttribute("order") OrderEntity order,
			BindingResult errors) {
		List<CategoryEntity> listCatelogies = categoryDao.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		if(order.getAddress().trim().length() == 0) {
			errors.rejectValue("address","order", "Vui lòng nhập địa chỉ nhận hàng.");
		}
		if (errors.hasErrors()) {
			return "user/order/checkout";
		}
		
		HttpSession session = request.getSession();
		CustomerEntity customer = (CustomerEntity) session.getAttribute("account");
		
		CartEntity cart = cartDao.getCartByCustomerId(customer.getId());
		List<CartItemsEntity> listItems = cartDao.getListCartItems(cart.getId());
		
		List<OrderDetail> orderDetails = new ArrayList<>();
		for (CartItemsEntity item : listItems) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setProductEntity(item.getProducts());
			orderDetail.setOrderEntity(order);
			orderDetail.setQuantity(item.getQuantity());
			orderDetail.setDiscount(item.getProducts().getSale());
			orderDetail.setUnitPrice((item.getQuantity() * item.getProducts().getPrice()) * ((100-item.getProducts().getSale())/100)); //(e.products.price * e.quantity) *((100 - e.products.sale)/100)
			orderDetails.add(orderDetail);
		}
		
		orderDao.createOrderAndOrderDetail(order, orderDetails);
		cartDao.clearCartItems(listItems);
		model.addAttribute("message", "Đặt hàng thành công");
		
		return "user/order/checkout";
	}
	
	@RequestMapping(value = "/order/list-orders", method = RequestMethod.GET)
	public String showOrders(HttpServletRequest request,ModelMap model) {
		List<CategoryEntity> listCatelogies = categoryDao.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		HttpSession session = request.getSession();
		CustomerEntity customer = (CustomerEntity) session.getAttribute("account");
		
		List<OrderEntity> orders = orderDao.getListOrderByCustomerId(customer.getId());
		model.addAttribute("list", orders);
		
		return "user/order/list";
	}
	
	@RequestMapping(value = "/order/order-detail/{id}", method = RequestMethod.GET)
	public String showOrderDetails(HttpServletRequest request,ModelMap model, @PathVariable("id") int id) {
		List<CategoryEntity> listCatelogies = categoryDao.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		OrderEntity order = orderDao.getOrderById(id);
		model.addAttribute("order", order);
		
		List<OrderDetail> orderDetails = orderDao.getOrderDetailByOrderId(id);
		model.addAttribute("details", orderDetails);
		
		return "user/order/details";
	}
	
	@RequestMapping(value = "/order/items", method = RequestMethod.GET)
	public String items(HttpServletRequest request,ModelMap model) {
		List<CategoryEntity> listCatelogies = categoryDao.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		HttpSession session = request.getSession();
		CustomerEntity customer = (CustomerEntity) session.getAttribute("account");
		
		List<ProductEntity> listProducts = orderDao.getItemsByUser(customer.getId());
		
		PagedListHolder pagedListHolder = new PagedListHolder(listProducts);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		 pagedListHolder.setPage(page);
		 pagedListHolder.setMaxLinkedPages(5);
		 pagedListHolder.setPageSize(9);
		 
		 model.addAttribute("pagedListHolder", pagedListHolder);
		 model.addAttribute("value", "/order/items");
		
		
		 return "user/product/list";
	}
}
