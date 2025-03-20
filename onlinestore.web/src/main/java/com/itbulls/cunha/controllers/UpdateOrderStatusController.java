package com.itbulls.cunha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itbulls.cunha.facades.OrderFacade;

@Controller
public class UpdateOrderStatusController {

	@Autowired
	private OrderFacade orderFacade;
	
	@RequestMapping("/updateOrderStatus")
	public String doGet(@RequestParam int order_id) {
		orderFacade.updateOrderStatus(orderFacade.getOrderById(order_id));
		return "redirect:management-orders";
	}
}
