package com.pawan.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawan.pos.dao.ProductDAO;
import com.pawan.pos.dto.ProductDto;
import com.pawan.pos.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;

	@Override
	public List<Product> getProducts() {
		return productDAO.getProducts();
	}

	@Override
	public List<Product> getProduct(String search) {
		return productDAO.getProducts(search);
	}

	@Override
	public ProductDto getProductDto(Product product) {
		return new ProductDto(product);
	}

	@Override
	public List<ProductDto> getProductDtoList(List<Product> products) {
		List<ProductDto> productDtos = new ArrayList<>();

		for (Product product : products) {
			productDtos.add(new ProductDto(product));
		}
		return productDtos;
	}
}
