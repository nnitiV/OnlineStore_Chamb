package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.impl;

import java.util.ResourceBundle;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.config.ApplicationContext;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.Menu;

public class SignOutMenu implements Menu {

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
		context.setLoggedInUser(null);
		context.getMainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println(bundle.getString("sign_out_header"));
		System.out.println(bundle.getString("see_you_later"));
	}

}