package com.itbulls.cunha.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itbulls.cunha.entities.Category;
import com.itbulls.cunha.facades.CategoryFacade;

@Controller
public class HomepageController {

	@Autowired
	private CategoryFacade categoryFacade;
	
	@RequestMapping({"/homepage", "/"})
	public String homePage(HttpSession session) {
		List<Category> categories = categoryFacade.getAllCategories();
		session.setAttribute("categories", categories);
		return "homepage";
	}
}
