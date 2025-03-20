package com.itbulls.cunha.facades;

import java.util.List;

import com.itbulls.cunha.entities.Category;

public interface CategoryFacade {
	List<Category> getAllCategories();

	Category getCategoryByName(String parameter);
}
