package com.pawan.pos.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Product extends AbstractTimestampEntity implements Serializable {

	private static final long serialVersionUID = -5440313337542157481L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String pcode;
	private String name;
	private int stock;
	private String description;
	private float price;

	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private Set<Product_Cart> product_Carts = new HashSet<Product_Cart>();

	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "products")
	private Set<Product_Order> order = new HashSet<>();

	public Product() {
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

	public Set<Product_Cart> getProduct_Carts() {
		return product_Carts;
	}

	public void setProduct_Carts(Set<Product_Cart> product_Carts) {
		this.product_Carts = product_Carts;
	}

	public Set<Product_Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Product_Order> order) {
		this.order = order;
	}

}
