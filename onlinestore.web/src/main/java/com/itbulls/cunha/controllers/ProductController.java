package com.itbulls.cunha.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itbulls.cunha.entities.Product;
import com.itbulls.cunha.entities.User;
import com.itbulls.cunha.entities.impl.DefaultProduct;
import com.itbulls.cunha.entities.impl.DefaultUser;
import com.itbulls.cunha.facades.OrderFacade;
import com.itbulls.cunha.facades.ProductFacade;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductFacade productFacade;
	@Autowired
	private OrderFacade orderFacade;
	private static final String SUCCESFULLY_SAVED_ORDER_TEXT = "Order is created and a manager will contact you soon!";
	
	@GetMapping
	public String doGet(HttpSession session, @RequestParam String product_guid) {
		session.setAttribute("product",
				productFacade.getProductByGuid(product_guid));
		return "product";
	}
	
	@PostMapping
	public String doPost(HttpSession session, @RequestParam String product_guid) {
		User user = (User) session.getAttribute("user");
		Product productToSave = productFacade.getProductByGuid(product_guid);

		if (user == null) {
			return "login";
		}
		orderFacade.saveOrder(productToSave, user);
		session.setAttribute("succesfully_added_order_text", SUCCESFULLY_SAVED_ORDER_TEXT);
		return "product";
	}
	
}
