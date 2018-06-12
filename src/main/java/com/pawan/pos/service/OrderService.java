package com.pawan.pos.service;

import java.util.List;
import java.util.Map;

import com.pawan.pos.dto.OrderDto;
import com.pawan.pos.dto.Product_OrderDto;
import com.pawan.pos.exception.CustomException;
import com.pawan.pos.model.Orders;
import com.pawan.pos.model.Product_Order;

public interface OrderService {

	Orders createOrder(int custId, int empId, String status, String paymode, String orderId);
	
	Orders reloadSavedOrder(String orderId, int empId);

	Map<String, List<OrderDto>> getCompleteOrderList(int empId) throws CustomException;

	Map<String, List<OrderDto>> getSavedOrderList(int empId) throws CustomException;

	List<Orders> getCompleteOrderListByOrderID(int empId, String orderId);

	List<Orders> getSavedOrderListByOrderID(int empId, String orderId);

	List<Orders> getOrderById(int empId, String orderId);

	OrderDto getOrderDto(Orders order);

	List<OrderDto> getOrderDtoList(List<Orders> order);

	List<Product_Order> getOrderProduct(int orderId);

	List<Product_OrderDto> getProductOrderListDto(List<Product_Order> product_Orders);

	

}
