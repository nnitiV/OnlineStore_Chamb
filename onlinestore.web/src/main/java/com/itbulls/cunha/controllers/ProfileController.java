package com.itbulls.cunha.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itbulls.cunha.entities.User;
import com.itbulls.cunha.services.UserService;

@Controller
public class ProfileController {

	@Autowired
	private UserService userService;

	@RequestMapping("/profile")
	public String profile(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			session.setAttribute("referralUsers", userService.getUsersByReferralUserId(user.getId()));
		}
		return "profile";
	}
}
