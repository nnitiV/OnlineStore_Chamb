package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.impl;

import java.util.ResourceBundle;
import java.util.Scanner;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.config.ApplicationContext;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.Menu;

public class SettingsMenu implements Menu {

	private static final String SETTINGS = "1. Change Password" + System.lineSeparator() + "2. Change Email";

	private ApplicationContext context;
	private ResourceBundle bundle;

	{
		context = ApplicationContext.getInstance();
		bundle = context.getBundle();
	}

	@Override
	public void start() {
		Menu menuToNavigate = null;
		mainLoop: while (true) {
			printMenuHeader();
			if (context.getLoggedInUser() == null) {
				System.out.println(bundle.getString("not_logged_in_my_settings_error_message"));
				new MainMenu().start();
				return;
			} else {
				System.out.println(SETTINGS);
				System.out.print(bundle.getString("my_settings_instructions"));
				Scanner sc = new Scanner(System.in);
				String userInput = sc.next();

				if (userInput.equalsIgnoreCase(MainMenu.MENU_COMMAND)) {
					menuToNavigate = new MainMenu();
					break mainLoop;
				}

				int userOption = Integer.parseInt(userInput);
				switch (userOption) {
				case 1:
					menuToNavigate = new ChangePasswordMenu();
					break mainLoop;
				case 2:
					menuToNavigate = new ChangeEmailMenu();
					break mainLoop;
				default:
					System.out.println(bundle.getString("invalid_input_my_settings_error_message"));
					continue;
				}
			}
		}

		menuToNavigate.start();

	}

	@Override
	public void printMenuHeader() {
		System.out.println(bundle.getString("my_settings_header"));
	}

}