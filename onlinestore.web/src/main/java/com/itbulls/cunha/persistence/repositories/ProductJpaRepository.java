package com.itbulls.cunha.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itbulls.cunha.persistence.entities.Product;

@Repository
public interface ProductJpaRepository extends CrudRepository<Product, Long>{
	Product findByProductName(String productName);
	Product findByCategoryId(Long categoryId);
	Product findByGuid(String guid);
}
