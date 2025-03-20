package com.itbulls.cunha.dao;

import java.util.List;

import com.itbulls.cunha.dto.ProductDTO;

public interface ProductDAO {
	List<ProductDTO> getAllProducts();
	List<ProductDTO> getProductsByName(String product_name);
	
	ProductDTO getProductById(int id);
	ProductDTO getProductByGuid(String guid);
	
	void saveProduct(ProductDTO product);
	List<ProductDTO> getProductsByCategoryId(int categoryId);
}
