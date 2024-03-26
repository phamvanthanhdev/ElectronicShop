package onlineshop.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import onlineshop.entity.CartEntity;
import onlineshop.entity.CartItemsEntity;
import onlineshop.entity.OrderDetail;
import onlineshop.entity.OrderEntity;

@Transactional
@Repository
public class CartDao {
	@Autowired
	private SessionFactory factory;
	
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
	
	public float totalPriceCart(String customerID) {
		CartEntity cart = this.getCartByCustomerId(customerID);
		List<CartItemsEntity> listItems = this.getListCartItems(cart.getId());
		float total = 0;
		for (CartItemsEntity item : listItems) { //(e.products.price * e.quantity) *((100 - e.products.sale)/100)
			total += (item.getQuantity() * item.getProducts().getPrice());
		}
		return total;
	}
	
	public void clearCartItems(List<CartItemsEntity> cartItems) {
		for (CartItemsEntity item : cartItems) {
			this.deleteCartItem(item);
		}
	}
}
