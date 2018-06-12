package com.pawan.pos.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawan.pos.dto.ErrorMessageResponseDto;
import com.pawan.pos.dto.OrderDto;
import com.pawan.pos.exception.CustomException;
import com.pawan.pos.model.Employee;
import com.pawan.pos.model.Orders;
import com.pawan.pos.model.Product_Order;
import com.pawan.pos.service.OrderService;
import com.pawan.pos.utils.Constants;
import com.pawan.pos.utils.Validation;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<Object> createOrder(HttpSession session, @RequestParam("cid") int custId,
			@RequestParam("status") String status, @RequestParam("paymode") String paymode,
			@RequestParam("orderId") String orderId) {

		if (!Validation.validateSession(session))
			return ResponseEntity.ok(Constants.INVALID_CREDENTIALS);

		int empId = ((Employee) session.getAttribute(Constants.EMPLOYEE)).getId();
		Orders order = orderService.createOrder(custId, empId, status, paymode, orderId);

		return ResponseEntity.ok(order);
	}

	@GetMapping(value = "/{oid}")
	public ResponseEntity<Object> getOrderById(HttpSession session, @PathVariable("oid") String orderId) {
		if (!Validation.validateSession(session))
			return ResponseEntity.ok(Constants.INVALID_CREDENTIALS);

		int empId = ((Employee) session.getAttribute(Constants.EMPLOYEE)).getId();

		List<Orders> order = orderService.getOrderById(empId, orderId);
		return ResponseEntity.ok(orderService.getOrderDtoList(order));
	}

	@GetMapping(value = "/completeorder")
	public ResponseEntity<Object> getCompleteOrder(HttpSession session) {
		if (!Validation.validateSession(session))
			return ResponseEntity.ok(Constants.INVALID_CREDENTIALS);

		int empId = ((Employee) session.getAttribute(Constants.EMPLOYEE)).getId();
		Map<String, List<OrderDto>> completeorder = null;

		try {
			completeorder = orderService.getCompleteOrderList(empId);
		} catch (CustomException e) {
			return ErrorMessageResponseDto.errorMessage(e.getMessage());
		}
		return ResponseEntity.ok(completeorder);
	}

	@GetMapping(value = "/savedorder")
	public ResponseEntity<Object> getSavedOrder(HttpSession session) {
		if (!Validation.validateSession(session))
			return ResponseEntity.ok(Constants.INVALID_CREDENTIALS);

		int empId = ((Employee) session.getAttribute(Constants.EMPLOYEE)).getId();
		Map<String, List<OrderDto>> savedOrder = null;

		try {
			savedOrder = orderService.getSavedOrderList(empId);
		} catch (CustomException e) {
			return ErrorMessageResponseDto.errorMessage(e.getMessage());
		}
		return ResponseEntity.ok(savedOrder);
	}

	@GetMapping(value = "/completeorder/{oid}")
	public ResponseEntity<Object> getCompleteOrderByOrderID(HttpSession session, @PathVariable("oid") String orderId) {
		if (!Validation.validateSession(session))
			return ResponseEntity.ok(Constants.INVALID_CREDENTIALS);

		int empId = ((Employee) session.getAttribute(Constants.EMPLOYEE)).getId();

		List<Orders> completeorder = orderService.getCompleteOrderListByOrderID(empId, orderId);
		return ResponseEntity.ok(orderService.getOrderDtoList(completeorder));
	}

	@GetMapping(value = "/savedorder/{oid}")
	public ResponseEntity<Object> getSavedOrderByOrderID(HttpSession session, @PathVariable("oid") String orderId) {
		if (!Validation.validateSession(session))
			return ResponseEntity.ok(Constants.INVALID_CREDENTIALS);

		int empId = ((Employee) session.getAttribute(Constants.EMPLOYEE)).getId();

		List<Orders> savedOrder = orderService.getSavedOrderListByOrderID(empId, orderId);
		return ResponseEntity.ok(orderService.getOrderDtoList(savedOrder));
	}

	@GetMapping(value = "/savedorder/{oid}/reload")
	public ResponseEntity<Object> reloadSavedOrder(HttpSession session, @PathVariable("oid") String orderId) {
		if (!Validation.validateSession(session))
			return ResponseEntity.ok(Constants.INVALID_CREDENTIALS);

		int empId = ((Employee) session.getAttribute(Constants.EMPLOYEE)).getId();

		Orders savedOrder = orderService.reloadSavedOrder(orderId, empId);
		return ResponseEntity.ok(orderService.getOrderDto(savedOrder));
	}

	@GetMapping(value = "/products/{oid}")
	public ResponseEntity<Object> getOrderProduct(HttpSession session, @PathVariable("oid") int orderId) {
		if (!Validation.validateSession(session))
			return ResponseEntity.ok(Constants.INVALID_CREDENTIALS);

		List<Product_Order> product_Orders = orderService.getOrderProduct(orderId);
		return ResponseEntity.ok(orderService.getProductOrderListDto(product_Orders));
	}

}
