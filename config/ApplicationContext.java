package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.config;

import java.util.Locale;
import java.util.Locale.Builder;
import java.util.ResourceBundle;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.Cart;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.User;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.impl.DefaultCart;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.Menu;

public class ApplicationContext {

	private static ApplicationContext instance;

	private User loggedInUser;
	private Menu mainMenu;
	private Cart sessionCart;
	private String language;
	private String country;
	private ResourceBundle bundle;

	{
		language = "en";
		country = "US";
		Locale locale = new Locale.Builder().setLanguage(language).setRegion(country).build();
		Locale.setDefault(locale);
		bundle = ResourceBundle.getBundle("exam43.MyLabels");
	}
	
	private ApplicationContext() {
	}

	public void setLoggedInUser(User user) {
		if (this.sessionCart != null)
			this.sessionCart.clear(); // we have to clear session cart when new user is logged in
		this.loggedInUser = user;
	}

	public User getLoggedInUser() {
		return this.loggedInUser;
	}

	public void setMainMenu(Menu menu) {
		this.mainMenu = menu;
	}

	public Menu getMainMenu() {
		return this.mainMenu;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language.split("_")[0];
		this.country = language.split("_")[1];
	}
	
	public void updateLanguage() {
		Locale locale = new Locale.Builder().setLanguage(language).setRegion(country).build();
		Locale.setDefault(locale);
		bundle = ResourceBundle.getBundle("exam43.MyLabels");
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public static ApplicationContext getInstance() {
		if (instance == null)
			instance = new ApplicationContext();
		return instance;
	}

	public Cart getSessionCart() {
		if (this.sessionCart == null)
			this.sessionCart = new DefaultCart();
		return this.sessionCart;
	}

}