package com.pawan.pos.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawan.pos.dto.ErrorMessageResponseDto;
import com.pawan.pos.model.Product;
import com.pawan.pos.model.Product_Cart;
import com.pawan.pos.service.CartService;
import com.pawan.pos.service.ProductService;
import com.pawan.pos.utils.Constants;
import com.pawan.pos.utils.Validation;

@RestController
@RequestMapping(value = "/carts")
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<Object> getCart(HttpSession session, @RequestParam("cid") int cid) {
		if (!Validation.validateSession(session))
			return ResponseEntity.ok(Constants.INVALID_CREDENTIALS);
		List<Product_Cart> product = null;
		try {
			product = cartService.getCart(cid);
		} catch (Exception e) {
			return ErrorMessageResponseDto.errorMessage(e.getMessage());
		}

		return ResponseEntity.ok(cartService.getProduct_CartDto(product));
	}

	@PostMapping
	public ResponseEntity<Object> addToCart(HttpSession session, @RequestParam("cid") int cid,
			@RequestParam("pid") int pid) {
		if (!Validation.validateSession(session))
			return ResponseEntity.ok(Constants.INVALID_CREDENTIALS);

		Product product = cartService.addProductToCart(cid, pid, 1);
		return ResponseEntity.ok(productService.getProductDto(product));
	}

	@PutMapping(value = "/{cid}/prod/{pid}/inc")
	public ResponseEntity<Object> incrementProduct(HttpSession session, @PathVariable("cid") int cid,
			@PathVariable("pid") int pid) {

		boolean inc = cartService.incrementProductQuantity(cid, pid);
		return ResponseEntity.ok(inc);

	}

	@PutMapping(value = "/{cid}/prod/{pid}/desc")
	public ResponseEntity<Object> decrementProduct(HttpSession session, @PathVariable("cid") int cid,
			@PathVariable("pid") int pid) {

		boolean inc = cartService.decrementProductQuantity(cid, pid);
		return ResponseEntity.ok(inc);

	}

	@DeleteMapping(value = "/{cid}/prod/{pid}")
	public ResponseEntity<Object> deleteProduct(HttpSession session, @PathVariable("cid") int cid,
			@PathVariable("pid") int pid) {
		if (!Validation.validateSession(session))
			return ResponseEntity.ok(Constants.INVALID_CREDENTIALS);

		boolean inc = cartService.deleteProductFromCart(cid, pid);
		return ResponseEntity.ok(inc);

	}

	@DeleteMapping(value = "/{cid}")
	public ResponseEntity<Object> deleteCart(HttpSession session, @PathVariable("cid") int cid) {
		if (!Validation.validateSession(session))
			return ResponseEntity.ok(Constants.INVALID_CREDENTIALS);

		boolean inc = cartService.deleteCart(cid);
		return ResponseEntity.ok(inc);

	}
}
