package onlineshop.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import onlineshop.entity.CategoryEntity;
import onlineshop.entity.ProductEntity;

@Transactional
@Repository
public class ProductDao {
	@Autowired
	private SessionFactory factory;
	
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
	
	public List<ProductEntity> getProductsByCategory(int cid) {
		Session session = factory.getCurrentSession();
		//String hql = "select top 5 * from ProductEntity";
		String hql = "FROM ProductEntity p where p.categoryEntity.id=:cid";
		Query query = session.createQuery(hql);
		query.setParameter("cid", cid);
		List<ProductEntity> list = query.list();
		return list;
	}
	
	public List<ProductEntity> getProductsByKeywords(String keywords) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity p where p.name LIKE:kw "
				+ "OR p.categoryEntity.name LIKE :kw";
		Query query = session.createQuery(hql);
		query.setParameter("kw", "%" +keywords+ "%");
		List<ProductEntity> list = query.list();
		return list;
	}

	public List<ProductEntity> getNewProducts() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity p where p.new_product=true";
		Query query = session.createQuery(hql);
		List<ProductEntity> list = query.list();
		
		return list;
	}
	
	public List<ProductEntity> getDiscountProducts() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity p ORDER BY p.sale DESC";
		Query query = session.createQuery(hql);
		List<ProductEntity> list = query.list();
		
		return list;
	}
	
	public List<ProductEntity> getSalesProducts() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity p ORDER BY size(p.orderDs) DESC";
		Query query = session.createQuery(hql);
		List<ProductEntity> list = query.list();
		
		return list;
	}
}
