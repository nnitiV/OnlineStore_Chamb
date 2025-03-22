package com.itbulls.cunha.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itbulls.cunha.entities.Category;

@Repository
public interface CategoryJpaRepository extends CrudRepository<Category, Long> {
	Category findByCategoryName(String categoryName);
}
