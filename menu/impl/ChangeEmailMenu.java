package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.impl;

import java.util.ResourceBundle;
import java.util.Scanner;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.config.ApplicationContext;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.Menu;

public class ChangeEmailMenu implements Menu {

	private ApplicationContext context;
	private ResourceBundle bundle;

	{
		context = ApplicationContext.getInstance();
		bundle = context.getBundle();
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		Scanner sc = new Scanner(System.in);
		String userInput = sc.next();
		context.getLoggedInUser().setEmail(userInput);
		System.out.println(bundle.getString("updated_email"));
		context.getMainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println(bundle.getString("change_email_header"));
		System.out.print(bundle.getString("ask_new_email"));
	}

}