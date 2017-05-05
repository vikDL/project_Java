package backend.FTW.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



import backend.FTW.models.product;

@Repository("ProductDao")
public class ProductDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public ProductDao(SessionFactory sessionFactory)
	{
		System.out.println("ProductDAO Object Created");
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional
	public void insertProduct(product product)
	{
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
	}
	@Transactional 
	public void deleteProduct(int prodid)
	{
		Session session=sessionFactory.getCurrentSession();
		product product=(product)session.get(product.class,prodid);
		session.delete(product);
	}
	
	public List<product> retrieveProduct()
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from product");
		@SuppressWarnings("unchecked")
		List<product> list=(List<product>)query.list();
		session.close();
		return list;
	}
	
	public product getProduct(int prodid)
	{
		Session session=sessionFactory.openSession();
		product product=(product)session.get(product.class,prodid);
		session.close();
		return product;
	}
	
	@Transactional
	public void updateProduct(product product)
	{
		Session session=sessionFactory.getCurrentSession();
		session.update(product);
	}

}
