package com.itbulls.cunha.springjdbc.rowmappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itbulls.cunha.entities.Category;
import com.itbulls.cunha.springjdbc.dao.CategoryRowMapper;

@Repository
public class CategoryDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Category getCategoryByCategoryName(String categoryName) {
		return jdbcTemplate.queryForObject("SELECT * FROM category c WHERE c.category_name = ?",
				new Object[] { categoryName }, new CategoryRowMapper());
	}

	public void addCategory(Category category) {
		jdbcTemplate.update("INSER INTO category (category_name) VALUES (?)", category.getCategoryName());
	}
}
