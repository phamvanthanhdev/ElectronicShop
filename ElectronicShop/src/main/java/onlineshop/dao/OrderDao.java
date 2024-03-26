package onlineshop.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import onlineshop.entity.OrderDetail;
import onlineshop.entity.OrderEntity;
import onlineshop.entity.ProductEntity;

@Transactional
@Repository
public class OrderDao {
	@Autowired
	private SessionFactory factory;
	
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
	
	public void  createOrderAndOrderDetail(OrderEntity order, List<OrderDetail> orderDetails) {
		Session session = factory.openSession();
		session.save(order);
		for (OrderDetail orderDetail : orderDetails) {
			session.save(orderDetail);
		}
	}
	
	public List<ProductEntity> getItemsByUser(String cid) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT DISTINCT d.productEntity FROM OrderDetail d where d.orderEntity.customer.id=:cid";
		Query query = session.createQuery(hql);
		query.setParameter("cid", cid);
		List<ProductEntity> list = query.list();
		return list;
	}
}
