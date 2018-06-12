package com.pawan.pos.service;

import java.util.List;

import com.pawan.pos.dto.Product_CartDto;
import com.pawan.pos.model.Product;
import com.pawan.pos.model.Product_Cart;

public interface CartService {

	Product addProductToCart(int cid, int pid, int quantity);

	boolean incrementProductQuantity(int cid, int pid);

	boolean decrementProductQuantity(int cid, int pid);

	boolean deleteProductFromCart(int cid, int pid);

	boolean deleteCart(int cid);

	List<Product_Cart> getCart(int cid);

	List<Product_CartDto> getProduct_CartDto(List<Product_Cart> product);

}
