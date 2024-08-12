package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.impl;

import java.util.ResourceBundle;
import java.util.Scanner;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.config.ApplicationContext;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.User;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.impl.DefaultUser;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.Menu;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services.UserManagementService;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services.impl.DefaultUserManagementService;

public class SignUpMenu implements Menu {

	private UserManagementService userManagementService;
	private ApplicationContext context;
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
		System.out.print(bundle.getString("ask_first_name"));
		String firstName = sc.next();
		System.out.print(bundle.getString("ask_last_name"));
		String lastName = sc.next();
		System.out.print(bundle.getString("ask_password"));
		String password = sc.next();
		System.out.print(bundle.getString("ask_email"));

		sc = new Scanner(System.in);
		String email = sc.nextLine();

		User user = new DefaultUser(firstName, lastName, password, email);

		String errorMessage = userManagementService.registerUser(user);
		if (errorMessage == null || errorMessage.isEmpty()) {
			context.setLoggedInUser(user);
			System.out.println(bundle.getString("new_user_is_created"));
		} else {
			System.out.println(errorMessage);
		}

		context.getMainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println(bundle.getString("sign_up_header"));
	}

}