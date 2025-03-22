package com.itbulls.cunha.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itbulls.cunha.entities.Category;
import com.itbulls.cunha.services.CategoryService;

@Controller
public class HomepageController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping({"/homepage", "/"})
	public String homePage(HttpSession session) {
		List<Category> categories = categoryService.getAllCategories();
		session.setAttribute("categories", categories);
		return "homepage";
	}
}
