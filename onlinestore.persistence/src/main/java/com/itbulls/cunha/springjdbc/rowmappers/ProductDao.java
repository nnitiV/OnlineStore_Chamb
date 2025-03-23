package com.itbulls.cunha.springjdbc.rowmappers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itbulls.cunha.entities.Product;
import com.itbulls.cunha.springjdbc.dao.ProductRowMapper;

@Repository
public class ProductDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Product> getAllProductsWithOrderId(Long orderId) {
		return jdbcTemplate.query("SELECT p.* FROM product p JOIN orders_product op ON p.id = op.product_id WHERE op.order_id = ?", new Object[] { orderId }, new int[] { java.sql.Types.INTEGER }, new ProductRowMapper());
	}
	
	public List<Product> getProductsByNameContaining(String productName) {
		String searchParameter = "%" + productName + "%";
		return jdbcTemplate.query("SELECT * FROM product p WHERE p.productName LIKE ?", new Object[] { searchParameter }, new ProductRowMapper());
	}
	
	public List<Product> getProductsByCategoryId(Long categoryId){
		return jdbcTemplate.query("SELECT * FROM product p WHERE p.category_id = ?", new Object[] { categoryId }, new ProductRowMapper());
	}
	
	public Product getProductByGuid(String guid) {
		return jdbcTemplate.queryForObject("SELECT * FROM product p WHERE p.guid = ?", new Object[] { guid }, new ProductRowMapper());
	}
}
