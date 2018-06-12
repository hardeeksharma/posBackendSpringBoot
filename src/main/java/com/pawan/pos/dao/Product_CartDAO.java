package com.pawan.pos.dao;

import java.util.List;

import com.pawan.pos.model.Product_Cart;

public interface Product_CartDAO {

	Product_Cart getProduct_CartById(int cid, int pid);

	void updateProduct_Cart(Product_Cart product_Cart);

	void deleteProductCartById(int product_cartId);

	void deleteProductCartByCartId(int cartId);

	List<Product_Cart> getCartItems(int id);

	void createProduct_Cart(Product_Cart product_Cart);

}
