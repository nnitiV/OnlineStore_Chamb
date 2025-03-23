package com.itbulls.cunha.springjdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.itbulls.cunha.entities.Product;

public class ProductRowMapper implements RowMapper<Product>  {

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product product = new Product();
		product.setId(rs.getLong("id"));
		product.setGuid(rs.getString("guid"));
		product.setProductName(rs.getString("product_name"));
		product.setPrice(rs.getBigDecimal("price"));
		return null;
	}

}
