package com.itbulls.cunha.entities.impl;

import java.util.Arrays;
import java.util.List;

import com.itbulls.cunha.entities.Order;
import com.itbulls.cunha.entities.OrderStatus;
import com.itbulls.cunha.entities.Product;
import com.itbulls.cunha.entities.User;

public class DefaultOrder implements Order {


	private static final int AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER = 16;

	private int orderId;
	private List<Product> products;
	private User orderUser;
	private OrderStatus orderStatus = OrderStatus.valueOf("RECEIVE_REQUEST");

	public DefaultOrder() {
	}

	public DefaultOrder(List<Product> products, User orderUser) {
		this.products = products;
		this.orderUser = orderUser;
	}

	public DefaultOrder(int orderId, List<Product> products, User orderUser) {
		this.orderId = orderId;
		this.products = products;
		this.orderUser = orderUser;	
	}
	
	public DefaultOrder(int orderId, List<Product> products, User orderUser, OrderStatus orderStatus) {
		this.orderId = orderId;
		this.products = products;
		this.orderUser = orderUser;
		this.orderStatus = orderStatus;
	}

	@Override
	public boolean isCreditCardNumberValid(String creditCardNumber) {
		if (creditCardNumber.matches("\\s+")) {
			return false;
		}
		if (creditCardNumber.matches("[A-Za-z]+")) {
			throw new NumberFormatException();
		}
		if (creditCardNumber.length() == AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER) {
			return true;
		}
		return false;
	}

	public void setProducts(Product[] products) {
		this.products = Arrays.asList(products);
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void setOrderUser(User user) {
		this.orderUser = user;
	}

	@Override
	public User getOrderUser() {
		return this.orderUser;
	}

	public int getOrderId() {
		return orderId;
	}
	
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "DefaultOrder [orderId=" + orderId + ", products=" + products + ", customerId=" + orderUser
				+ ", orderStatus=" + orderStatus + "]";
	}

// private void writeObject(ObjectOutputStream oos) throws IOException {
//		oos.defaultWriteObject();
//	}
//
//	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
//		ois.defaultReadObject();
//	}
}