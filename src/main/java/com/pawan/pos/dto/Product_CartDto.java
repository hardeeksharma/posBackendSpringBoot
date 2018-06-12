package com.pawan.pos.dto;

import com.pawan.pos.model.Product_Cart;

public class Product_CartDto {

	private int id;
	private int pId;
	private String pcode;
	private String name;
	private float price;
	private int quantity;

	public Product_CartDto() {
	}

	public Product_CartDto(Product_Cart p) {
		this.id=p.getId();
		this.pId=p.getProduct().getId();
		this.pcode=p.getProduct().getPcode();
		this.name=p.getProduct().getName();
		this.price=p.getProduct().getPrice();
		this.quantity=p.getQuantity();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
