package com.itbulls.cunha.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itbulls.cunha.entities.impl.DefaultUser;
import com.itbulls.cunha.facades.UserFacade;

@Controller
@RequestMapping("/signup")
public class SignUpController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SignUpController.class);
	@Autowired
	private UserFacade userFacade;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public String doGet(Model model, HttpSession session) {
		if(session.getAttribute("error") != null) {
			model.addAttribute("error", session.getAttribute("error"));
			session.removeAttribute("error");
		}
		model.addAttribute("userSignUp", new DefaultUser());
		return "signup";
	}

	@PostMapping
	public String doPost(@Valid @ModelAttribute("userSignUp") DefaultUser user, BindingResult bindingResult,
			HttpSession session, @CookieValue(value = "partner_code", defaultValue = "") String referrerUserCode) {
		LOGGER.info("Request to sign up user.");
		if (bindingResult.hasErrors()) {
			return "redirect:signUp";
		} else {
			try (InputStream is = SignUpController.class.getClassLoader().getResourceAsStream("10k-most-common.txt");
					BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
				String line;
				while ((line = br.readLine()) != null) {
					if (user.getPassword().equals(line)) {
						ObjectError error = new ObjectError("error", "Your password is too weak! Please, try again!");
						bindingResult.addError(error);
						return "redirect:signup";
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userFacade.saveUser(user, referrerUserCode);
			LOGGER.info("User with email {} is registered succesfully", user.getEmail());
			return "redirect:login";
		}
	}
}
