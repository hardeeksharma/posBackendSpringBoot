package com.pawan.pos.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawan.pos.model.Product;
import com.pawan.pos.service.ProductService;
import com.pawan.pos.utils.Constants;
import com.pawan.pos.utils.Validation;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<Object> getProduct(HttpSession session) {
		if (!Validation.validateSession(session))
			return ResponseEntity.ok(Constants.INVALID_CREDENTIALS);

		List<Product> res = productService.getProducts();
		return ResponseEntity.ok(productService.getProductDtoList(res));
	}
	
	@GetMapping("/{search}")
	public ResponseEntity<Object> getProductSearch(HttpSession session, @PathVariable("search") String search) {
		if (!Validation.validateSession(session))
			return ResponseEntity.ok(Constants.INVALID_CREDENTIALS);

		List<Product> res = productService.getProduct(search);
		return ResponseEntity.ok(productService.getProductDtoList(res));
	}
}
