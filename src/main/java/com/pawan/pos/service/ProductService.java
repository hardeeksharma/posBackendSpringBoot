package com.pawan.pos.service;

import java.util.List;

import com.pawan.pos.dto.ProductDto;
import com.pawan.pos.model.Product;

public interface ProductService {

	List<Product> getProducts();
	
	List<Product> getProduct(String search);

	ProductDto getProductDto(Product res);

	List<ProductDto> getProductDtoList(List<Product> res);	

}
