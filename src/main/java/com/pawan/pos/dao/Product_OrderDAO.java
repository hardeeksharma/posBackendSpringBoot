package com.pawan.pos.dao;

import java.util.List;

import com.pawan.pos.model.Product_Order;

public interface Product_OrderDAO {

	void createProductOrder(Product_Order product_Order);

	void removeProduct_Order(int i);

	List<Product_Order> getProduct_OrderById(int orderId);

}
