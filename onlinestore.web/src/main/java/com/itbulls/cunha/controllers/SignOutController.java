package com.itbulls.cunha.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signout")
public class SignOutController {

	@GetMapping
	public String doGet(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:homepage";
	}
}
