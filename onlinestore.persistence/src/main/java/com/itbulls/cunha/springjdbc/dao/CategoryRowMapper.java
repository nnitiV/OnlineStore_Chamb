package com.itbulls.cunha.springjdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.itbulls.cunha.entities.Category;

public class CategoryRowMapper implements RowMapper<Category>  {

	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		Category category = new Category();
		
		category.setId(rs.getLong("id"));
		category.setCategoryName(rs.getString("category_name"));
		
		return category;
	}

}
