package com.pawan.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawan.pos.dao.CartDAO;
import com.pawan.pos.dao.CustomerDAO;
import com.pawan.pos.dao.ProductDAO;
import com.pawan.pos.dao.Product_CartDAO;
import com.pawan.pos.dto.Product_CartDto;
import com.pawan.pos.model.Cart;
import com.pawan.pos.model.Customer;
import com.pawan.pos.model.Product;
import com.pawan.pos.model.Product_Cart;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private Product_CartDAO product_CartDAO;

	@Override
	public Product addProductToCart(int cid, int pid, int quantity) {
		Product_Cart product_Cart = new Product_Cart(quantity);

		Customer customer = customerDAO.getCustomersById(cid);
		Product product = productDAO.getProductById(pid);
		Cart cart = cartDAO.getCartByCustomerId(cid);
		
		

		if (cart == null) {
			int cartID = cartDAO.addtoCart(new Cart(customer));
			cart = cartDAO.getCartById(cartID);
		} else {
			Product_Cart product_Cart2 = product_CartDAO.getProduct_CartById(cart.getId(), pid);
			if (product_Cart2 != null) {
				incrementProductQuantity(cid, pid);
				return product;
			}
		}

		product.getProduct_Carts().add(product_Cart);
		product_Cart.setProduct(product);

		cart.getProduct_Carts().add(product_Cart);
		product_Cart.setCart(cart);

		customer.setCart(cart);
		product_CartDAO.createProduct_Cart(product_Cart);
		cartDAO.updateCart(cart);
		customerDAO.updateCustomer(customer);
		return product;
	}

	@Override
	public boolean incrementProductQuantity(int cid, int pid) {
		Cart cart = cartDAO.getCartByCustomerId(cid);
		Product_Cart product_Cart = product_CartDAO.getProduct_CartById(cart.getId(), pid);
		Product product = productDAO.getProductById(product_Cart.getProduct().getId());

		if (product_Cart.getQuantity() + 1 > product.getStock())
			return false;

		product_Cart.setQuantity(product_Cart.getQuantity() + 1);
		product_CartDAO.updateProduct_Cart(product_Cart);
		return true;
	}

	@Override
	public boolean decrementProductQuantity(int cid, int pid) {

		Cart cart = cartDAO.getCartByCustomerId(cid);
		Product_Cart product_Cart = product_CartDAO.getProduct_CartById(cart.getId(), pid);

		if (product_Cart.getQuantity() - 1 <= 0) {
			return false;
		}

		product_Cart.setQuantity(product_Cart.getQuantity() - 1);
		product_CartDAO.updateProduct_Cart(product_Cart);
		return true;
	}

	@Override
	public boolean deleteProductFromCart(int cid, int pid) {
		Cart cart = cartDAO.getCartByCustomerId(cid);
		Product_Cart product_Cart = product_CartDAO.getProduct_CartById(cart.getId(), pid);

		try {
			product_CartDAO.deleteProductCartById(product_Cart.getId());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteCart(int cid) {
		Cart cart = cartDAO.getCartByCustomerId(cid);

		try {
			product_CartDAO.deleteProductCartByCartId(cart.getId());

			System.out.println("1");
			Customer customer = customerDAO.getCustomersById(cid);
			customer.setCart(null);
			customerDAO.updateCustomer(customer);
			cart.setCustomer(null);
			cartDAO.deleteCart(cart.getId());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public List<Product_Cart> getCart(int cid) {
		Cart cart = cartDAO.getCartByCustomerId(cid);
		return product_CartDAO.getCartItems(cart.getId());
	}

	@Override
	public List<Product_CartDto> getProduct_CartDto(List<Product_Cart> product) {
		List<Product_CartDto> cartDtos = new ArrayList<>();
		for (Product_Cart p : product) {
			cartDtos.add(new Product_CartDto(p));
		}
		return cartDtos;
	}

}
