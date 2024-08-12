package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.impl;

import java.util.ResourceBundle;
import java.util.Scanner;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.config.ApplicationContext;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.Menu;

public class ChangeLanguageMenu implements Menu {

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
		mainLoop: while (true) {
			String userInput = sc.next();
			switch (userInput.toLowerCase()) {
			case "1":
				context.setLanguage("en_US");
				break mainLoop;
			case "2":
				context.setLanguage("fr_FR");
				break mainLoop;
			case "3":
				context.setLanguage("pt_BR");
				break mainLoop;
			case "4":
				context.setLanguage("es_ES");
				break mainLoop;
			case "5":
				context.setLanguage("ru_RU");
				break mainLoop;
			case "6":
				context.setLanguage("it_IT");
				break mainLoop;
			case "7":
				context.setLanguage("ko_KR");
				break mainLoop;
			case "8":
				context.setLanguage("jp_JP");
				break mainLoop;
			case "9":
				context.setLanguage("zh_CN");
				break mainLoop;
			case "menu":
				context.getMainMenu().start();
			default:
				System.out.println();
				continue mainLoop;
			}
		}
		context.updateLanguage();
		System.out.println(bundle.getString("updated_language"));
		context.getMainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println(bundle.getString("change_language_header"));
		System.out.println(bundle.getString("change_language_menu"));
		System.out.print(bundle.getString("ask_new_password"));
	}

}