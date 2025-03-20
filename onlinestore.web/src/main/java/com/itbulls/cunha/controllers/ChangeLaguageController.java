package com.itbulls.cunha.controllers;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/change_language")
public class ChangeLaguageController {

	@GetMapping
	public String doGet(@RequestParam String language, HttpSession session) {
		Locale.setDefault(new Locale(language));

		Locale locale = new Locale(language);

		session.setAttribute("userLocale", locale);
		return "homepage";
	}
}
