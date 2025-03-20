package com.itbulls.cunha.facades;

import java.util.List;

import com.itbulls.cunha.entities.Order;
import com.itbulls.cunha.entities.Product;
import com.itbulls.cunha.entities.User;

public interface OrderFacade {

	List<Order> getAllOrders();
	
	Order getOrderById(int orderId);
	
	void saveOrder(Product product, User user);

	void  updateOrderStatus(Order order);
}
