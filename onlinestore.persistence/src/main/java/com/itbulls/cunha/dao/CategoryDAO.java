package com.itbulls.cunha.dao;

import java.util.List;

import com.itbulls.cunha.dto.CategoryDTO;

public interface CategoryDAO {
	CategoryDTO getCategoryById(int id);

	CategoryDTO getCategoryByName(String category_name);
	
	List<CategoryDTO> getAllCategoriesName();

	void saveCategory(CategoryDTO category);
}
