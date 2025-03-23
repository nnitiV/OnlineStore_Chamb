package com.itbulls.cunha.springjdbc.rowmappers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itbulls.cunha.entities.Order;
import com.itbulls.cunha.entities.Product;
import com.itbulls.cunha.springjdbc.dao.OrderRowMapper;
import com.itbulls.cunha.springjdbc.dao.ProductRowMapper;

@Repository
public class OrderDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

//	Use this Stored Procedure on your database:
//	DELIMITER //
//
//	CREATE PROCEDURE CreateOrderWithProducts(
//	    IN p_order_status VARCHAR(50),
//	    IN p_user_id BIGINT,
//	    IN p_product_guid VARCHAR(255),
//	    IN p_product_name VARCHAR(255),
//	    IN p_product_price DECIMAL(10, 2),
//	    IN p_quantity INT
//	)
//	BEGIN
//	    DECLARE v_order_id BIGINT;
//	    DECLARE v_product_id BIGINT;
//
//	    -- Step 1: Insert into the `order` table
//	    INSERT INTO `order` (order_status, user_id) VALUES (p_order_status, p_user_id);
//	    SET v_order_id = LAST_INSERT_ID();
//
//	    -- Step 2: Insert into the `product` table (if not exists)
//	    INSERT INTO product (guid, product_name, price) 
//	    VALUES (p_product_guid, p_product_name, p_product_price)
//	    ON DUPLICATE KEY UPDATE id = id;
//
//	    -- Step 3: Get the product ID
//	    SELECT id INTO v_product_id FROM product WHERE guid = p_product_guid;
//
//	    -- Step 4: Insert into the `orders_products` table
//	    INSERT INTO orders_products (order_id, product_id, quantity) 
//	    VALUES (v_order_id, v_product_id, p_quantity);
//	END //
//
//	DELIMITER ;
	
	public void addOrder(Order order) {
		List<Product> products = order.getProducts();
		for (Product product : products) {
			jdbcTemplate.update("CALL CreateOrder(?, ?, ?, ?, ?)", order.getOrderStatus().toString(),
					order.getUser().getId(), product.getGuid(), product.getProductName(), product.getPrice());
		}
	}

	
	public Order getOrderById(Long orderId) {
		String sql = "SELECT " + "o.id AS order_id, " + "o.order_status, " + "o.user_id, " + "u.email AS user_email, "
				+ "u.first_name AS user_first_name, " + "u.last_name AS user_last_name, " + "p.id AS product_id, "
				+ "p.guid AS product_guid, " + "p.product_name, " + "p.price " + "FROM `order` o "
				+ "JOIN user u ON o.user_id = u.id " + "JOIN orders_products op ON o.id = op.order_id "
				+ "JOIN product p ON op.product_id = p.id " + "WHERE o.id = ?";

		Order order = jdbcTemplate.queryForObject(sql, new Object[] { orderId }, new OrderRowMapper());

		String productSql = "SELECT p.id AS product_id, p.guid AS product_guid, p.product_name, p.price "
				+ "FROM product p " + "JOIN orders_products op ON p.id = op.product_id " + "WHERE op.order_id = ?";
		List<Product> products = jdbcTemplate.query(productSql, new Object[] { orderId }, new ProductRowMapper());

		order.setProducts(products);

		return order;
	}

}
