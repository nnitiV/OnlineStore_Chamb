package com.itbulls.cunha.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itbulls.cunha.entities.User;
import com.itbulls.cunha.facades.UserFacade;
import com.itbulls.cunha.utils.PasswordEncryptionService;

@Controller
@RequestMapping("/edit-profile")
public class EditProfileController {

	private final String USER_UPDATED_WITH_SUCCESS = "User updated with success!";
	private final String PASSWORD_MUST_MATCH = "Password must match the actual password!";
	private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+=\\[\\]{};':\"\\\\|,.<>\\/?-]).{8,}$";;
	@Autowired
	private UserFacade userFacade;
	@Autowired
	private PasswordEncryptionService passwordEncryptionService;
	
	@GetMapping
	public String doGet() {
		return "editProfile";
	}
	
	@PostMapping
	public String doPost(HttpSession session, @RequestParam String oldPassword, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {
		User user = (User) session.getAttribute("user");
		if (!passwordEncryptionService.validatePassword(oldPassword,
				userFacade.getUserById(user.getId()).getPassword())) {
			session.setAttribute("messageToShow", PASSWORD_MUST_MATCH);
			return "editProfile";
		}
		if (!password.matches(PASSWORD_REGEX)) {
			session.setAttribute("messageToShow",
					"Password should contain at least 8 characters, at least one special character and one uppercase letter!");
			return "editProfile";
		}
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(passwordEncryptionService.generatePasswordWithSaltAndHash(password));
		userFacade.updateUser(user);
		session.setAttribute("messageToShow", USER_UPDATED_WITH_SUCCESS);
		return "editProfile";
	}
}
