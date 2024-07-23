package com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.services.impl;

import java.util.ArrayList;

import com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.enteties.Order;
import com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.services.OrderManagementService;

public class DefaultOrderManagementService implements OrderManagementService {

//	private static final int DEFAULT_ORDER_CAPACITY = 10;

	private static DefaultOrderManagementService instance;
//	private int lastIndex;
	private ArrayList<Order> orders;
	
	{
		orders = new ArrayList<>();
	}
	
	public static OrderManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultOrderManagementService();
		}
		return instance;
	}

	@Override
	public void addOrder(Order order) {
		if (order == null) {
			return;
		}
		
		orders.add(order);
	}

	@Override
	public ArrayList<Order> getOrdersByUserId(int userId) {
		ArrayList<Order> userOrders = new ArrayList<>();
		
		for (Order order : orders) {
			if (order != null && order.getCustomerId() == userId) {
				userOrders.add(order);
			}
		}
		
		return userOrders;
	}

	@Override
	public ArrayList<Order> getOrders() {
		ArrayList<Order> nonNullOrders = new ArrayList<>();
		
//		int index = 0;
		for (Order order : orders) {
			if (order != null) {
				nonNullOrders.add(order);
			}
		}
		
		return nonNullOrders;
	}
	
	void clearServiceState() {
		orders = new ArrayList<Order>();
	}

}