package onlineshop.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import onlineshop.entity.CartItemsEntity;
import onlineshop.entity.CustomerEntity;

@Transactional
@Repository
public class AccountDao {
	@Autowired
	private SessionFactory factory;
	

	public CustomerEntity updateCustomer(CustomerEntity customer) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(customer);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		return customer;
	}
	
	
	public CustomerEntity getCustomerLogin(String id, String pw) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CustomerEntity p where p.id=:id AND p.password=:pw";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		query.setParameter("pw", pw);
		List<CustomerEntity> listCustomer = query.list();
		CustomerEntity customerEntity = new CustomerEntity();
		if (listCustomer.size() > 0) {
			customerEntity = (CustomerEntity) query.list().get(0);
			return customerEntity;
		} else {
			return null;
		}
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
	
	public CustomerEntity createCustomer(CustomerEntity cate) {
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
}
