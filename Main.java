package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.impl.MainMenu;

public class Main {
	public static final String EXIT_COMMAND = "exit";

	public static void main(String[] args) {
		MainMenu mainMenu = new MainMenu();
		mainMenu.start();
	}
	
}
