package com.itbulls.cunha.services;

import java.util.List;

import com.itbulls.cunha.entities.Product;

public interface ProductManagementService {

	List<Product> getProducts();

	Product getProductById(int productIdToAddToCart);

	void retrieveProducts();
}