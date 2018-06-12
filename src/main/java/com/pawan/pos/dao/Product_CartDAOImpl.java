package com.pawan.pos.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pawan.pos.model.Product_Cart;

@Repository
@Transactional
public class Product_CartDAOImpl implements Product_CartDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void createProduct_Cart(Product_Cart product_Cart) {
		getCurrentSession().save(product_Cart);

	}

	@Override
	public Product_Cart getProduct_CartById(int cid, int pid) {

		Query query = getCurrentSession().createQuery("from Product_Cart where cartId=:cid and productId=:pid")
				.setParameter("cid", cid).setParameter("pid", pid);
		Product_Cart product_Cart = (Product_Cart) query.uniqueResult();

		if (product_Cart == null)
			return null;
		return product_Cart;
	}

	@Override
	public void updateProduct_Cart(Product_Cart product_Cart) {
		getCurrentSession().update(product_Cart);
	}

	@Override
	public void deleteProductCartById(int product_cartId) {
		Query query = getCurrentSession().createQuery("delete from Product_Cart where id=:Id").setParameter("Id",
				product_cartId);
		query.executeUpdate();
	}

	@Override
	public void deleteProductCartByCartId(int cartId) {
		Query query = getCurrentSession().createQuery("delete from Product_Cart where cartId=:Id").setParameter("Id",
				cartId);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product_Cart> getCartItems(int id) {
		Query query = getCurrentSession().createQuery("from Product_Cart where cartId=:Id").setParameter("Id", id);
		return query.list();
	}

}
