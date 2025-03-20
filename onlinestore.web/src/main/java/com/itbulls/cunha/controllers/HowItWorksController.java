package com.itbulls.cunha.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HowItWorksController {

	@RequestMapping("/howitworks")
	public String howItWorks() {
		return "howitworks";
	}
}
