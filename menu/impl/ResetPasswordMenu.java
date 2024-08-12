package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.impl;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.config.ApplicationContext;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.User;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.Menu;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services.ResetPasswordService;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services.UserManagementService;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services.impl.DefaultResetPasswordService;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services.impl.DefaultUserManagementService;

public class ResetPasswordMenu implements Menu {

	private DefaultResetPasswordService defaultResetPasswordService;
	private UserManagementService userManagementService;
	private ApplicationContext context;
	private ResourceBundle bundle;

	{
		context = ApplicationContext.getInstance();
		bundle = context.getBundle();
		userManagementService = new DefaultUserManagementService();
		defaultResetPasswordService = new DefaultResetPasswordService();
	}

	@Override
	public void start() {
		printMenuHeader();
		System.out.print("Please, provide your email: ");
		Scanner scan = new Scanner(System.in);
		String email = scan.nextLine();
		System.out.println(bundle.getString("email_sent"));
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		ResetPasswordService resetPasswordService = new DefaultResetPasswordService();
		executor.submit(() -> {
			User user = userManagementService.getUserByEmail(email);
			defaultResetPasswordService.resetPasswordForUser(user);
		});
		context.getMainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println(bundle.getString("reset_password_header"));
	}
}
