package com.itbulls.cunha.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itbulls.cunha.entities.Product;

@Repository
public interface ProductJpaRepository extends CrudRepository<Product, Long>{
	Product findByProductName(String productName);
	List<Product> findByProductNameContaining(String productsName);
	List<Product> findByCategoryId(Long categoryId);
	Product findByGuid(String guid);
}
