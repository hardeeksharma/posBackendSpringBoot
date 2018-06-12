package com.pawan.pos.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pawan.pos.model.Product_Order;

@Repository
@Transactional
public class Product_OrderDAOImpl implements Product_OrderDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void createProductOrder(Product_Order product_Order) {
		getCurrentSession().save(product_Order);
	}

	@Override
	public void removeProduct_Order(int id) {
		Query query = getCurrentSession().createQuery("delete from Product_Order where orderId=:ID").setParameter("ID",
				id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product_Order> getProduct_OrderById(int orderId) {
		Query query = getCurrentSession().createQuery("from Product_Order where orderId=:ID").setParameter("ID",
				orderId);
		List<Product_Order> list = query.list();
		return list;
	}

}
