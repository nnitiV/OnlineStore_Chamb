package com.itbulls.cunha.springjdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.itbulls.cunha.entities.Order;
import com.itbulls.cunha.entities.OrderStatus;
import com.itbulls.cunha.springjdbc.rowmappers.ProductDao;
import com.itbulls.cunha.springjdbc.rowmappers.UserDao;

public class OrderRowMapper implements RowMapper<Order>  {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private UserDao userDao;
	
	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order order = new Order();
		order.setId(rs.getLong("id"));
		order.setOrderStatus(OrderStatus.valueOf(rs.getString("order_status")));
		order.setProducts(productDao.getAllProductsWithOrderId(rs.getLong("id")));
		order.setUser(userDao.getUserById(rs.getLong("user_id")));
		return order;
	}


}
