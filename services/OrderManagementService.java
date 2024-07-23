package com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.services;

import java.util.ArrayList;

import com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.enteties.Order;

public interface OrderManagementService {

	void addOrder(Order order);

	ArrayList<Order> getOrdersByUserId(int userId);
	
	ArrayList<Order> getOrders();

}