package com.itbulls.cunha.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbulls.cunha.persistence.entities.Category;
import com.itbulls.cunha.persistence.repositories.CategoryJpaRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryJpaRepository categoryJpaRepository;
	
	@Transactional
	public List<Category> getAllCategories() {
		return (List<Category>) categoryJpaRepository.findAll();
	}
	
	@Transactional
	public Category getCategoryById(long categoryId) {
		return categoryJpaRepository.findById(categoryId).orElse(null);
	}
	
	@Transactional
	public Category getCategoryByName(String categoryName) {
		return categoryJpaRepository.findByCategoryName(categoryName);
	}
	
	@Transactional
	public void deleteCategoryById(long categoryId) {
		categoryJpaRepository.deleteById(categoryId);
	}
	
	@Transactional
	public boolean addCategory(Category category) {
		return categoryJpaRepository.save(category) != null;
	}
	
	@Transactional
	public boolean updateCategory(Category category) {
		return categoryJpaRepository.save(category) != null;
	}
}
