package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.impl;

import java.util.ResourceBundle;
import java.util.Scanner;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.config.ApplicationContext;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.Cart;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.Product;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.menu.Menu;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services.ProductManagementService;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services.impl.DefaultProductManagementService;

public class ProductCatalogMenu implements Menu {

	private static String CHECKOUT_COMMAND;
	private ApplicationContext context;
	private ProductManagementService productManagementService;
	private ResourceBundle bundle;

	{
		context = ApplicationContext.getInstance();
		bundle = context.getBundle();
		productManagementService = DefaultProductManagementService.getInstance();
		CHECKOUT_COMMAND = bundle.getString("checkout_command");
	}

	@Override
	public void start() {
		Menu menuToNavigate = null;
		while (true) {
			printMenuHeader();
			printProductsToConsole();

			String userInput = readUserInput();

			if (context.getLoggedInUser() == null) {
				menuToNavigate = new MainMenu();
				System.out.println(bundle.getString("not_logged_in_product_catalog_error_message"));
				break;
			}

			if (userInput.equalsIgnoreCase(MainMenu.MENU_COMMAND)) {
				menuToNavigate = new MainMenu();
				break;
			}

			if (userInput.equalsIgnoreCase(CHECKOUT_COMMAND)) {
				Cart sessionCart = context.getSessionCart();
				if (sessionCart == null || sessionCart.isEmpty()) {
					System.out.println(bundle.getString("empty_cart_error_message"));
				} else {
					menuToNavigate = new CheckoutMenu();
					break;
				}
			} else {
				Product productToAddToCart = fetchProduct(userInput);

				if (productToAddToCart == null) {
					System.out.println(bundle.getString("invalid_input_in_product_catalog_error_message"));
					continue;
				}

				processAddToCart(productToAddToCart);
			}
		}

		menuToNavigate.start();
	}

	private String readUserInput() {
		System.out.print(bundle.getString("product_catalog_instructions_2"));
		Scanner sc = new Scanner(System.in);
		String userInput = sc.next();
		return userInput;
	}

	private void printProductsToConsole() {
		productManagementService.getProducts().stream().forEach(System.out::println);
		;
	}

	private Product fetchProduct(String userInput) {
		int productIdToAddToCart = Integer.parseInt(userInput);
		Product productToAddToCart = productManagementService.getProductById(productIdToAddToCart);
		return productToAddToCart;
	}

	private void processAddToCart(Product productToAddToCart) {
		context.getSessionCart().addProduct(productToAddToCart);
		System.out.printf(bundle.getString("added_product_to_cart"), productToAddToCart.getProductName());
	}

	@Override
	public void printMenuHeader() {
		System.out.println(bundle.getString("product_catalog_header"));
		System.out.println(bundle.getString("product_catalog_instructions"));
	}

}