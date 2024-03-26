package onlineshop.dao;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import onlineshop.entity.CategoryEntity;
import onlineshop.entity.ProductEntity;

@Transactional
@Repository
public class CategoryDao {
	@Autowired
	private SessionFactory factory;
	public List<CategoryEntity> getCategories() {
		Session session = factory.getCurrentSession();
		String hql = "FROM CategoryEntity";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<CategoryEntity> list = query.list();
		return list;
	}
	

	public List<ProductEntity> getRandomProducts(){
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity p WHERE p.highlight = true";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<ProductEntity> list = query.list();
		Collections.shuffle(list);
		list = list.subList(0, 4);
		return list;
	}
	
	public List<ProductEntity> getSpecialProducts(){
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity p WHERE p.highlight = true";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<ProductEntity> list = query.list();
		Collections.shuffle(list);
		list = list.subList(0, 6);
		return list;
	}
	
	public ProductEntity getProductActivate(){
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity p WHERE p.highlight = true";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<ProductEntity> list = query.list();
		Collections.shuffle(list);
		ProductEntity prod = list.get(0);
		return prod;
	}
	
	public CategoryEntity createCategory(CategoryEntity cate) {
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
}
