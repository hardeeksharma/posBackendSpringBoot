package com.pawan.pos.dao;

import java.util.List;

import com.pawan.pos.model.Orders;

public interface OrderDAO {

	int createOrder(Orders orders);

	void updateOrder(Orders order);

	Orders getOrderById(int orderId);

	Orders getOrderByOrderId(String orderId);

	Orders getSavedOrderByOrderId(String orderId, int empId);

	List<Orders> getOrderList(int empId, String complete);

	List<Orders> getOrderListByOrderID(int empId, String orderId, String status);

	List<Orders> getOrderByOrderId(int empId, String orderId);

	List<Orders> getOrderList(int empId);
	
	

}
