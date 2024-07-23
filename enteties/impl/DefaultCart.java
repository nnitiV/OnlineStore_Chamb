package com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.enteties.impl;

import java.util.ArrayList;

import com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.enteties.Cart;
import com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.enteties.Product;

public class DefaultCart implements Cart {

//	private static final int DEFAULT_PRODUCT_CAPACITY = 10;
	private ArrayList<Product> products;
//	private int lastIndex;
	
	{
		products = new ArrayList<>();
	}
	
	@Override
	public boolean isEmpty() {
		if (products == null || products.size() == 0) {
			return true;
		}
		
		for (Product product : products) {
			if (product != null) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public void addProduct(Product product) {
		if (product == null) {
			return;
		}
		products.add(product);
	}

	@Override
	public ArrayList<Product> getProducts() {
		ArrayList<Product> nonNullProducts = new ArrayList<>();
//		int index = 0;
		for (Product product : products) {
			if (product != null) {
				nonNullProducts.add(product);
			}
		}
		
		return nonNullProducts;
	}

	@Override
	public void clear() {
		products = new ArrayList<Product>();
	}

}