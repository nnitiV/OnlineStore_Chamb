package com.itbulls.cunha.facades;

import java.util.List;

import com.itbulls.cunha.entities.Product;

public interface ProductFacade {
	List<Product> getAllProducts();

	Product getProductById(int id);

	List<Product> getProductsByName(String product_name);

	List<Product> getProductsbyCategoryId(int categoryId);

	Product getProductByGuid(String productGuid);
}
