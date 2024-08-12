package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.impl;

import java.util.ResourceBundle;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.config.ApplicationContext;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.Product;

public class ComparableProduct implements Product, Comparable<Product> {

	private int id;
	private String productName;
	private String categoryName;
	private double price;

	private ApplicationContext context;
	private ResourceBundle bundle;

	{
		context = ApplicationContext.getInstance();
		bundle = context.getBundle();
	}

	public ComparableProduct() {
	}

	public ComparableProduct(int id, String productName, String categoryName, double price) {
		this.id = id;
		this.productName = productName;
		this.categoryName = categoryName;
		this.price = price;
	}

	@Override
	public String toString() {
		return bundle.getString("product_id") + "= " + id + bundle.getString("product_name") + productName
				+ bundle.getString("category_name") + categoryName + bundle.getString("price") + price;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public String getProductName() {
		return this.productName;
	}

	@Override
	public String getCategoryName() {
		return this.categoryName;
	}

	@Override
	public double getPrice() {
		return this.price;
	}

	@Override
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int compareTo(Product otherProduct) {
		return this.id - otherProduct.getId();
	}

}