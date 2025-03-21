package com.itbulls.cunha.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itbulls.cunha.entities.Order;
import com.itbulls.cunha.facades.OrderFacade;

@Controller
public class OrdersManagementController {

	@Autowired
	private OrderFacade orderFacade;

	@RequestMapping("/orders-management")
	public String ordersManagement(HttpSession session) {
		List<Order> orders = orderFacade.getAllOrders();
		session.setAttribute("orders", orders);
		return "ordersManagement";
	}
}
