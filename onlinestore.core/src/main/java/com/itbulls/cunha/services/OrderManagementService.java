package com.itbulls.cunha.services;

import java.util.List;

import com.itbulls.cunha.entities.Order;

public interface OrderManagementService  {

	void addOrder(Order order);

	List<Order> getOrdersByUserId(int userId);

	List<Order> getOrders();
	
	void retrieveAllOrders();
}