package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services;

import java.util.ArrayList;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.Order;


public interface OrderManagementService {

	void addOrder(Order order);

	ArrayList<Order> getOrdersByUserId(int userId);
	
	ArrayList<Order> getOrders();

}