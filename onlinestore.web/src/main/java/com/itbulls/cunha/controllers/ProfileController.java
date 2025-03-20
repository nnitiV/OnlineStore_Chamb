package com.itbulls.cunha.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itbulls.cunha.entities.User;
import com.itbulls.cunha.facades.UserFacade;

@Controller
public class ProfileController {
	
	@Autowired
	private UserFacade userFacade;
	
	@RequestMapping("/profile")
	public String profile(HttpSession session) { 
		session.setAttribute("referralUsers", userFacade.getAllUsersWithReferralCode((User) session.getAttribute("user")));
		return "profile";
	}
}
