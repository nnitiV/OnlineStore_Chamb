package com.itbulls.cunha.entities.impl;

import java.util.Arrays;

import com.itbulls.cunha.entities.Cart;
import com.itbulls.cunha.entities.Product;

public class DefaultCart implements Cart {

	private static final int DEFAULT_CART_SIZE = 10;

	private Product[] productsInTheCart;
	private int lastPositionOfProductAddedToTheCart = 0;

	{
		productsInTheCart = new Product[DEFAULT_CART_SIZE];
	}

	@Override
	public boolean isEmpty() {
		return productsInTheCart[0] == null;
	}

	@Override
	public void addProduct(Product product) {
		if (product != null) {
			if (lastPositionOfProductAddedToTheCart < 10) {
				productsInTheCart[lastPositionOfProductAddedToTheCart++] = product;
			} else {
				productsInTheCart = Arrays.copyOf(productsInTheCart, productsInTheCart.length + 1);
				productsInTheCart[lastPositionOfProductAddedToTheCart++] = product;
			}
		}
	}

	@Override
	public Product[] getProducts() {
	    Product[] productsThaExistsInTheCart = new DefaultProduct[0];
		int lastPositionInArray = 0;
		if (productsInTheCart[0] == null) {
			return new DefaultProduct[0];
		} else {
			for (Product product : productsInTheCart) {
				if (product == null) {
					break;
				}
				productsThaExistsInTheCart = Arrays.copyOf(productsThaExistsInTheCart, productsThaExistsInTheCart.length + 1);
				productsThaExistsInTheCart[lastPositionInArray++] = product;
			}
		}
		return productsThaExistsInTheCart;
	}

	@Override
	public void clear() {
		productsInTheCart = new Product[DEFAULT_CART_SIZE];
		lastPositionOfProductAddedToTheCart = 0;
	}

}