package com.pawan.pos.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pawan.pos.model.Orders;

@Repository
@Transactional
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int createOrder(Orders orders) {
		getCurrentSession().save(orders);
		return orders.getId();
	}

	@Override
	public Orders getOrderById(int orderId) {
		Query query = getCurrentSession().createQuery("from Orders where id=:ID").setParameter("ID", orderId);
		return (Orders) query.uniqueResult();
	}

	@Override
	public void updateOrder(Orders order) {
		getCurrentSession().update(order);
	}

	@Override
	public Orders getOrderByOrderId(String orderId) {
		Query query = getCurrentSession().createQuery("from Orders where orderId=:ID").setParameter("ID", orderId);
		return (Orders) query.uniqueResult();
	}

	@Override
	public Orders getSavedOrderByOrderId(String orderId, int empId) {
		Query query = getCurrentSession().createQuery("from Orders where orderId=:ID and employee_id=:EmpID ")
				.setParameter("ID", orderId).setParameter("EmpID", empId);
		return (Orders) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> getOrderList(int empId, String complete) {
		Query query = getCurrentSession().createQuery("from Orders where employee_id=:EmpID and status=:Status ")
				.setParameter("EmpID", empId).setParameter("Status", complete);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> getOrderListByOrderID(int empId, String orderId, String status) {
		Query query = getCurrentSession()
				.createQuery("from Orders where employee_id=:EmpID and orderId=:OrderId and status=:Status  ")
				.setParameter("EmpID", empId).setParameter("OrderId", orderId).setParameter("Status", status);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> getOrderByOrderId(int empId, String orderId) {
		Query query = getCurrentSession().createQuery("from Orders where employee_id=:EmpID and orderId=:OrderId ")
				.setParameter("EmpID", empId).setParameter("OrderId", orderId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> getOrderList(int empId) {
		Query query = getCurrentSession().createQuery(" from Orders where employee_id=:EmpID ").setParameter("EmpID",
				empId);
		return query.list();
	}

}
