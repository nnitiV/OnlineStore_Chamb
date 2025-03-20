package com.itbulls.cunha.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itbulls.cunha.entities.User;
import com.itbulls.cunha.entities.impl.DefaultUser;
import com.itbulls.cunha.facades.UserFacade;
import com.itbulls.cunha.utils.PasswordEncryptionService;

@Controller
@RequestMapping("/login")
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	private static final String THREE_FAILED_ATEMPS = "There were 3 unsuccessful attempts to login into the account. Please, try to sign in later";
	private static final String WRONG_CREDENTIALS = "Wrong credentials! Check it and try again!";
	private static final String ATTEMPS_FAILED_LOGIN_COOKIE_NAME = "attemptsFailedToLogin";

	@Autowired
	private UserFacade userFacade;

	@Autowired
	private PasswordEncryptionService passwordEncryptionService;

	@GetMapping
	public String doGet(Model model, HttpSession session) {
		if (session.getAttribute("error") != null) {
			model.addAttribute("error", session.getAttribute("error"));
			session.removeAttribute("error");
		}
		return "login";
	}

	@PostMapping
	public String doPost(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password,
			HttpSession session, HttpServletResponse response,
			@CookieValue(value = ATTEMPS_FAILED_LOGIN_COOKIE_NAME, defaultValue = "0") String attemptsFailedToLogin) {
		LOGGER.info("Login is requested.");
		User userFoundFromDatabase = userFacade.getUserByEmail(email);

		if (userFoundFromDatabase != null
				&& passwordEncryptionService.validatePassword(password, userFoundFromDatabase.getPassword())) {
			session.setAttribute("user", userFoundFromDatabase);
			LOGGER.info("User with id {} is added to the session", userFoundFromDatabase.getId());

			if (userFoundFromDatabase.getRole().getRoleName().equals("ROLE_ADMIN")) {
				return "redirect:/admin/panel";
			} else {
				return "redirect:/homepage";
			}
		} else {
			if (attemptsFailedToLogin.equals("2")) {
				session.setAttribute("error", THREE_FAILED_ATEMPS);
				return "redirect:login";
			}

			increaseLoginAttempts(response, attemptsFailedToLogin);
			session.setAttribute("error", WRONG_CREDENTIALS);
			return "redirect:login";
		}
	}

	private void increaseLoginAttempts(HttpServletResponse response, String attemptsFailedToLogin) {
		Integer attemptsFailedToLoginInt = Integer.valueOf(attemptsFailedToLogin);
		Cookie attemptsCookie = new Cookie(ATTEMPS_FAILED_LOGIN_COOKIE_NAME,
				String.valueOf(attemptsFailedToLoginInt + 1));
		attemptsCookie.setMaxAge(60);
		response.addCookie(attemptsCookie);
	}
}
