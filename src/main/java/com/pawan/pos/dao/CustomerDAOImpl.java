package com.pawan.pos.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pawan.pos.model.Customer;

@Repository
@Transactional
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Customer> getCustomers(final String search) {
		Query query = getCurrentSession().createQuery(
				"from Customer where firstName like :Search or lastName like :Search or email like :Search or mobile like :Search")
				.setParameter("Search", "%" + search + "%");

		@SuppressWarnings("unchecked")
		List<Customer> customers = query.list();

		if (customers.size() == 0)
			return null;
		return customers;
	}

	@Override
	public Customer getCustomersById(final int id) {
		Query query = getCurrentSession().createQuery("from Customer where id=:Id").setParameter("Id", id);
		Customer customers = (Customer) query.uniqueResult();

		if (customers == null)
			return null;
		return customers;
	}

	@Override
	public void updateCustomer(Customer customer) {
		getCurrentSession().update(customer);
	}
}