package com.itbulls.cunha.controllers;

import java.util.Arrays;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itbulls.cunha.entities.Order;
import com.itbulls.cunha.entities.OrderStatus;
import com.itbulls.cunha.entities.Product;
import com.itbulls.cunha.entities.User;
import com.itbulls.cunha.services.OrderService;
import com.itbulls.cunha.services.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	private static final String SUCCESFULLY_SAVED_ORDER_TEXT = "Order is created and a manager will contact you soon!";

	@GetMapping
	public String doGet(HttpSession session, @RequestParam String product_guid) {
		session.setAttribute("product", productService.getProductByGuid(product_guid));
		return "product";
	}

	@PostMapping
	public String doPost(HttpSession session, @RequestParam String product_guid) {
		User user = (User) session.getAttribute("user");
		Product productToSave = productService.getProductByGuid(product_guid);
		Order order = new Order();
		if (user == null) {
			return "login";
		}
		order.setUser(user);
		order.setProducts(Arrays.asList(productToSave));
		order.setOrderStatus(OrderStatus.RECEIVE_REQUEST);
		orderService.addOrder(order);
		session.setAttribute("succesfully_added_order_text", SUCCESFULLY_SAVED_ORDER_TEXT);
		return "redirect:product?product_guid="+product_guid;
	}

}
