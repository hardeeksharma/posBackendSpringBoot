package com.pawan.pos.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Orders extends AbstractTimestampEntity implements Serializable {

	private static final long serialVersionUID = 6611807106650832000L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String orderId;
	private String status;

	private float tax;
	private float amount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	private String paymentType;

	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
	private Set<Product_Order> order = new HashSet<Product_Order>();

	@JsonIgnore
	@ManyToOne
	private Employee employee;

	@JsonIgnore
	@ManyToOne
	private Customer customer;

	public Orders() {
	}

	public Orders(String orderID, String stat, Customer customer2, Employee employee2, String paymode) {
		this.orderId = orderID;
		this.status = stat;
		this.customer = customer2;
		this.employee = employee2;
		this.orderDate = new Date();
		this.paymentType = paymode;
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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Set<Product_Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Product_Order> order) {
		this.order = order;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String toString(Orders orders) {
		StringBuilder builder = new StringBuilder();
		builder.append(orders.getEmployee().getId() + "||");
		builder.append(orders.getEmployee().getFirstName() + orders.getEmployee().getLastName() + "||");
		builder.append(orders.getEmployee().getEmail() + "||");
		builder.append(orders.getCustomer().getEmail() + "||");
		builder.append(orders.getCustomer().getFirstName() + orders.getCustomer().getLastName() + "||");
		builder.append(orders.getOrderId() + "||");
		builder.append(orders.getStatus() + "||");
		builder.append(orders.getPaymentType() + "||");
		builder.append(orders.getAmount() + "\n");
		
		return builder.toString();
	}

}
