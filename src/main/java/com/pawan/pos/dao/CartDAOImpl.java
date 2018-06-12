package com.pawan.pos.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pawan.pos.model.Cart;

@Repository
@Transactional
public class CartDAOImpl implements CartDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int addtoCart(Cart cart) {
		getCurrentSession().save(cart);
		return cart.getId();
	}

	@Override
	public Cart getCartById(int cartID) {
		return getCurrentSession().get(Cart.class, cartID);
	}

	@Override
	public Cart getCartByCustomerId(int cid) {
		Query query = getCurrentSession().createQuery("from Cart where customer_id=:ID").setParameter("ID", cid);
		Cart cart = (Cart) query.uniqueResult();
		return cart;
	}

	@Override
	public void deleteCart(int cid) {
		Query query = getCurrentSession().createQuery("delete from Cart where id=:Id").setParameter("Id", cid);
		query.executeUpdate();
	}

	@Override
	public void updateCart(Cart cart) {
		getCurrentSession().update(cart);
	}

	@Override
	public boolean checkCart(int cid) {
		Query query = getCurrentSession().createQuery("from Cart where customer_id=:Id").setParameter("Id", cid);
		Cart cart = (Cart) query.uniqueResult();
		if (cart == null)
			return false;
		return true;
	}

}
