package com.itbulls.cunha.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbulls.cunha.persistence.entities.Product;
import com.itbulls.cunha.persistence.repositories.ProductJpaRepository;

@Service
public class ProductService {

	
	@Autowired
	private ProductJpaRepository productJpaRepository;
	
	@Transactional	
	public List<Product> getAllProducts() {
		return (List<Product>) productJpaRepository.findAll();
	}
	
	@Transactional
	public Product getProductById(long productId) {
		return productJpaRepository.findById(productId).orElse(null);
	}
	
	@Transactional
	public Product getProductByGuid(String guid) {
		return productJpaRepository.findByGuid(guid);
	}
	
	@Transactional
	public Product getProductByCategoryId(long categoryId) {
		return productJpaRepository.findByCategoryId(categoryId);
	}
	
	@Transactional
	public boolean addProduct(Product product) {
		return productJpaRepository.save(product) != null;
	}
	
	@Transactional
	public boolean updateUser(Product product) {
		return productJpaRepository.save(product) != null;
	}
	
	@Transactional
	public void deleteProductById(long productId) {
		productJpaRepository.deleteById(productId);
	}
}
