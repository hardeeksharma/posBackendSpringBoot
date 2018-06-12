package com.pawan.pos.dto;

import com.pawan.pos.model.Product_Order;

public class Product_OrderDto {

	private int id;
	private String pcode;
	private String name;
	private int quantity;
	private String description;
	private float price;

	public Product_OrderDto() {

	}

	public Product_OrderDto(Product_Order product_Order) {
		this.id = product_Order.getProducts().getId();
		this.pcode = product_Order.getProducts().getPcode();
		this.name = product_Order.getProducts().getName();
		this.quantity = product_Order.getQuantity();
		this.description = product_Order.getProducts().getDescription();
		this.price = product_Order.getProducts().getPrice();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
