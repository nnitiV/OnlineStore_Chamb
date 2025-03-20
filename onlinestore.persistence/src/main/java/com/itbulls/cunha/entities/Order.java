package com.itbulls.cunha.entities;

import java.io.Serializable;
import java.util.List;

public interface Order extends Serializable {
	
	int getOrderId();
	
	List<Product> getProducts();
	void setProducts(Product[] products);

	boolean isCreditCardNumberValid(String userInput);

	void setOrderUser(User orderUser);
	User getOrderUser();

	
	OrderStatus getOrderStatus();
	void setOrderStatus(OrderStatus orderStatus);
}