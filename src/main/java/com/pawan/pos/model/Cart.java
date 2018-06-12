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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Cart extends AbstractTimestampEntity implements Serializable {

	private static final long serialVersionUID = 2685156429824750494L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	private Set<Product_Cart> product_Carts = new HashSet<Product_Cart>();

	@OneToOne
	private Customer customer;

	public Cart() {
	}

	public Cart(Customer cust) {
		this.customer = cust;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<Product_Cart> getProduct_Carts() {
		return product_Carts;
	}

	public void setProduct_Carts(Set<Product_Cart> product_Carts) {
		this.product_Carts = product_Carts;
	}

}
