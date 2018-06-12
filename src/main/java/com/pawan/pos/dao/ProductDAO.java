package com.pawan.pos.dao;

import java.util.List;

import com.pawan.pos.model.Product;

public interface ProductDAO {

	List<Product> getProducts();
	
	List<Product> getProducts(String search);

	Product getProductById(int id);

	void updateProduct(Product product);

}
