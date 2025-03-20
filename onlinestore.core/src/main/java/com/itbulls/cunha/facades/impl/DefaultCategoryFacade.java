package com.itbulls.cunha.facades.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbulls.cunha.dao.CategoryDAO;
import com.itbulls.cunha.dto.converters.CategoryDtoToCategoryConverter;
import com.itbulls.cunha.entities.Category;
import com.itbulls.cunha.facades.CategoryFacade;

@Service
public class DefaultCategoryFacade implements CategoryFacade {

	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private CategoryDtoToCategoryConverter categoryConverter;
	
	@Override
	public List<Category> getAllCategories() {
		return categoryConverter.convertListOfCategoryDtoToCategory(categoryDAO.getAllCategoriesName());
	}

	@Override
	public Category getCategoryByName(String categoryName) {
		return categoryConverter.convertCategoryDtoToCategory(categoryDAO.getCategoryByName(categoryName));
	}

}
