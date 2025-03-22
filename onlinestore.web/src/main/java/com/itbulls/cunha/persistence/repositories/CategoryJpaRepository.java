package com.itbulls.cunha.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itbulls.cunha.persistence.entities.Category;

@Repository
public interface CategoryJpaRepository extends CrudRepository<Category, Long> {
	Category findByCategoryName(String categoryName);
}
