package com.pawan.pos.dto;

import com.pawan.pos.model.Product;

public class ProductDto {

	private int id;
	private String pcode;
	private String name;
	private int stock;
	private String description;
	private float price;

	public ProductDto(Product product) {
		this.id = product.getId();
		this.pcode = product.getPcode();
		this.name = product.getName();
		this.stock = product.getStock();
		this.description = product.getDescription();
		this.price = product.getPrice();
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
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
