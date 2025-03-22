package com.itbulls.cunha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itbulls.cunha.services.OrderService;

@Controller
public class UpdateOrderStatusController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/updateOrderStatus")
	public String doGet(@RequestParam int order_id) {
		orderService.updateOrderStatus(orderService.getOrderById(order_id));
		return "redirect:management-orders";
	}
}
