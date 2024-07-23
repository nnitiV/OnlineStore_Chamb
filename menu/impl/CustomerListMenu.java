package com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.menu.impl;

import java.util.ArrayList;

import com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.config.ApplicationContext;
import com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.enteties.User;
import com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.menu.Menu;
import com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.services.UserManagementService;
import com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.services.impl.DefaultUserManagementService;

public class CustomerListMenu implements Menu {

	private ApplicationContext context;
	private UserManagementService userManagementService;
	
	{
		userManagementService = DefaultUserManagementService.getInstance();
		context = ApplicationContext.getInstance();
	}
	
	@Override
	public void start() {
		printMenuHeader();
		ArrayList<User> users = userManagementService.getUsers();
		
		if (users.size() == 0) {
			System.out.println("Unfortunately, there are no customers.");
		} else {
			for (User user : users) {
				System.out.println(user);
			}
		}
		context.getMainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** USERS *****");		
	}

}