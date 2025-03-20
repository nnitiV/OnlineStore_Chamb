package com.itbulls.cunha.facades.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbulls.cunha.dao.ProductDAO;
import com.itbulls.cunha.dto.converters.ProductDtoToProductConverter;
import com.itbulls.cunha.entities.Product;
import com.itbulls.cunha.facades.ProductFacade;

@Service
public class DefaultProductFacade implements ProductFacade {

	@Autowired
	private ProductDAO productDao;
	@Autowired
	private ProductDtoToProductConverter productConverter;

	@Override
	public List<Product> getAllProducts() {
		return productConverter.convertListOfProductDtoToListOfProduct(productDao.getAllProducts());
	}

	@Override
	public Product getProductById(int id) {
		return productConverter.convertProductDtoToProduct(productDao.getProductById(id));
	}

	@Override
	public List<Product> getProductsByName(String product_name) {
		return productConverter.convertListOfProductDtoToListOfProduct(productDao.getProductsByName(product_name));
	}

	@Override
	public List<Product> getProductsbyCategoryId(int categoryId) {
		return productConverter.convertListOfProductDtoToListOfProduct(productDao.getProductsByCategoryId(categoryId));
	}

	@Override
	public Product getProductByGuid(String productGuid) {
		return productConverter.convertProductDtoToProduct(productDao.getProductByGuid(productGuid));
	}

}
