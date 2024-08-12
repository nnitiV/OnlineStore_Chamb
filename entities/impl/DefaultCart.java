package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.Cart;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.Product;

public class DefaultCart implements Cart {

	private List<Product> products;

	{
		products = new ArrayList<>();
	}

	@Override
	public boolean isEmpty() {
		return (products == null || products.size() == 0) ? true : false;
	}

	@Override
	public void addProduct(Product product) {
		if (product == null) return;
		products.add(product);
	}

	@Override
	public ArrayList<Product> getProducts() {
		return products.stream().filter(Objects::nonNull).collect(Collectors.toCollection(ArrayList<Product>::new));
	}

	@Override
	public void clear() {
		products = new ArrayList<Product>();
	}

}