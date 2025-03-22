package com.itbulls.cunha.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itbulls.cunha.entities.Product;
import com.itbulls.cunha.services.CategoryService;
import com.itbulls.cunha.services.ProductService;

@Controller
public class ProductsController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/products")
	public String doGet(HttpSession session, @RequestParam(defaultValue = "0") Integer page_number, @RequestParam(defaultValue = "") String product_name, @RequestParam(defaultValue = "") String category_name) {
		System.out.println("Page number: " + page_number + 1);
		session.setAttribute("active_page", page_number + 1);
		if(!product_name.isBlank() || !product_name.isEmpty()) {
			List<Product> products = productService.getProductsByName(product_name);
			List<Product> productsToShowOnThePage = products.subList(page_number, page_number + 5);
			session.setAttribute("size_of_navbar", products.size() / 5);
			session.setAttribute("products", productsToShowOnThePage);
			session.setAttribute("product_name_to_search", product_name);
		}else {
			List<Product> allProducts = productService.getProductByCategoryId((long) (categoryService.getCategoryByName(category_name).getId()));
			List<Product> productsToShowOnThePage = allProducts.subList(page_number, page_number + 5);
			session.setAttribute("size_of_navbar", allProducts.size() / 5);
			session.setAttribute("products", productsToShowOnThePage);
			session.setAttribute("category_name", category_name);
		}
		return "products";
	}
	
}
