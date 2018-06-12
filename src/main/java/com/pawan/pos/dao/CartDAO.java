package com.pawan.pos.dao;

import com.pawan.pos.model.Cart;

public interface CartDAO {

	int addtoCart(Cart cart);

	Cart getCartById(int cartID);

	Cart getCartByCustomerId(int cid);

	void deleteCart(int cid);

	void updateCart(Cart cart);

	boolean checkCart(int cid);

}
