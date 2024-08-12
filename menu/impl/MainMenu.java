package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.impl;

import java.util.ResourceBundle;
import java.util.Scanner;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.Main;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.config.ApplicationContext;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.Menu;


public class MainMenu implements Menu {

	public static String MENU_COMMAND;
	
	private ApplicationContext context;
	private ResourceBundle bundle;
	
	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		context.updateLanguage();
		bundle = context.getBundle();
		MENU_COMMAND = bundle.getString("exit_command");
		if (context.getMainMenu() == null) {
			context.setMainMenu(this);
		}

		Menu menuToNavigate = null;
		mainLoop: while (true) {
			printMenuHeader();

			Scanner sc = new Scanner(System.in);
			
			System.out.print(bundle.getString("user_input"));
			String userInput = sc.next();
			
			if (userInput.equalsIgnoreCase(Main.EXIT_COMMAND)) System.exit(0);
			else {
				int commandNumber = Integer.parseInt(userInput);
				switch (commandNumber) {
				case 1:
					menuToNavigate = new SignUpMenu();
					break mainLoop;
				case 2:
					if (context.getLoggedInUser() == null) {
						menuToNavigate = new SignInMenu();
					} else {
						menuToNavigate = new SignOutMenu();
					}
					break mainLoop;
				case 3:
					menuToNavigate = new ProductCatalogMenu();
					break mainLoop;
				case 4:
					menuToNavigate = new MyOrdersMenu();
					break mainLoop;
				case 5:
					menuToNavigate = new SettingsMenu();
					break mainLoop;
				case 6:
					menuToNavigate = new CustomerListMenu();
					break mainLoop;
				case 7:
					menuToNavigate = new ResetPasswordMenu();
					break mainLoop;
				case 8:
					menuToNavigate = new ChangeLanguageMenu();
					break mainLoop;
				default:
					System.out.println(bundle.getString("wrong_main_menu_input_message"));
					continue; // continue endless loop
				}
			}
		}

		menuToNavigate.start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println(bundle.getString("main_menu_header"));
		if (context.getLoggedInUser() == null) {
			System.out.println(bundle.getString("main_menu_text_for_logged_out_user"));
		} else {
			System.out.println(bundle.getString("main_menu_text_for_logged_in_user"));
		}
	}

}