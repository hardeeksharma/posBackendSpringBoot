package com.pawan.pos.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pawan.pos.model.Product;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Product> getProducts() {
		Query query = getCurrentSession().createQuery("from Product");
		@SuppressWarnings("unchecked")
		List<Product> products = query.list();
		return products;
	}

	@Override
	public List<Product> getProducts(String search) {
		Query query = getCurrentSession()
				.createQuery("from Product where pcode like :Search or name like :Search or description like :Search")
				.setParameter("Search", "%" + search + "%");

		@SuppressWarnings("unchecked")
		List<Product> products = query.list();

		if (products.size() == 0)
			return null;
		return products;
	}

	@Override
	public Product getProductById(final int id) {

		Query query = getCurrentSession().createQuery("from Product where id=:ID");
		query.setParameter("ID", id);

		Product product = (Product) query.uniqueResult();

		if (product == null)
			return null;
		return product;
	}

	@Override
	public void updateProduct(Product product) {
		getCurrentSession().update(product);
	}
}
