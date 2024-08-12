package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.impl;

import java.util.ResourceBundle;
import java.util.Scanner;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.config.ApplicationContext;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.User;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.Menu;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services.UserManagementService;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services.impl.DefaultUserManagementService;

public class SignInMenu implements Menu {

	private ApplicationContext context;
	private UserManagementService userManagementService;
	private ResourceBundle bundle;

	{
		context = ApplicationContext.getInstance();
		bundle = context.getBundle();
		userManagementService = DefaultUserManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		Scanner sc = new Scanner(System.in);

		System.out.print(bundle.getString("ask_email"));
		String userEmail = sc.next();

		System.out.print(bundle.getString("ask_password"));
		String userPassword = sc.next();

		User user = userManagementService.getUserByEmail(userEmail);
		if (user != null && user.getPassword().equals(userPassword)) {
			System.out.printf(bundle.getString("welcome_back"), user.getFirstName(),
					user.getLastName() + System.lineSeparator());
			context.setLoggedInUser(user);
		} else {
			System.out.println(bundle.getString("user_do_not_exist_message"));
		}
		context.getMainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println(bundle.getString("sign_in_header"));
	}

}