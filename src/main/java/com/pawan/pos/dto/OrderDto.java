package com.pawan.pos.dto;

import com.pawan.pos.model.Orders;
import com.pawan.pos.utils.DateParse;

public class OrderDto {

	private int id;
	private String orderId;
	private String status;
	private float tax;
	private float amount;
	private String orderDate;
	private String orderTime;
	private String paymentType;
	private int custId;
	private int empId;
	private String custName;

	public OrderDto(Orders order) {
		this.id = order.getId();
		this.orderId = order.getOrderId();
		this.status = order.getStatus();
		this.tax = order.getTax();
		this.amount = order.getAmount();
		System.out.println(order.getOrderDate().toString());
		String[] temp = order.getOrderDate().toString().split(" ");
		this.orderDate = DateParse.parseDate(temp[0]);
		System.out.println(this.orderDate);
		this.orderTime = temp[1];
		this.paymentType = order.getPaymentType();
		this.empId = order.getEmployee().getId();
		this.custId = order.getCustomer().getId();
		this.custName = order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

}
