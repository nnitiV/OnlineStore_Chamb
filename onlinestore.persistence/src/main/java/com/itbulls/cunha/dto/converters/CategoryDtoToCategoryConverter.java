package com.itbulls.cunha.dto.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itbulls.cunha.dao.impl.JpaCategoryDao;
import com.itbulls.cunha.dto.CategoryDTO;
import com.itbulls.cunha.entities.Category;
import com.itbulls.cunha.entities.impl.DefaultCategory;

@Service
public class CategoryDtoToCategoryConverter {

	public CategoryDTO convertCategoryToCategoryDto(String categoryName) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(new JpaCategoryDao().getCategoryByName(categoryName).getId());
		categoryDTO.setCategoryName(categoryName);
		return categoryDTO;
	}
	
	public Category convertCategoryDtoToCategory(CategoryDTO categoryDTO) {
		Category category = new DefaultCategory(categoryDTO.getId(), categoryDTO.getCategoryName());
		return category;
	}
	
	public List<Category> convertListOfCategoryDtoToCategory(List<CategoryDTO> categoriesDTO) {
		List<Category> categories = new ArrayList<>();
		categoriesDTO.stream().forEach(category -> categories.add(convertCategoryDtoToCategory(category)));
		return categories;
	}
}
