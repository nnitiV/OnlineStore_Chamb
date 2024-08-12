package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.impl;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.ResourceBundle;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.config.ApplicationContext;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.Order;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.Menu;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services.OrderManagementService;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services.impl.DefaultOrderManagementService;

public class MyOrdersMenu implements Menu {

	private ApplicationContext context;
	private OrderManagementService orderManagementService;
	private ResourceBundle bundle;

	{
		context = ApplicationContext.getInstance();
		bundle = context.getBundle();
		context = ApplicationContext.getInstance();
		orderManagementService = DefaultOrderManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		if (context.getLoggedInUser() == null) {
			System.out.println(bundle.getString("not_logged_in_my_orders_error_message"));
			new MainMenu().start();
			return;
		} else {
			printUserOrdersToConsole();
		}
		context.getMainMenu().start();
	}

	private void printUserOrdersToConsole() {
		ArrayList<Order> loggedInUserOrders = orderManagementService
				.getOrdersByUserId(context.getLoggedInUser().getId());

		if (loggedInUserOrders == null || loggedInUserOrders.size() == 0) {
			System.out.println(bundle.getString("no_orders_error_message"));
		} else {
			for (Order order : loggedInUserOrders) {
				System.out.println(order);
			}
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println(bundle.getString("my_orders_header"));
	}

}