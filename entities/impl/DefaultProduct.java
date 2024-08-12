package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.impl;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ResourceBundle;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.config.ApplicationContext;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.Product;

public class DefaultProduct implements Product, Externalizable {

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

	public DefaultProduct() {
	}

	public DefaultProduct(int id, String productName, String categoryName, double price) {
		this.id = id;
		this.productName = productName;
		this.categoryName = categoryName;
		this.price = price;
	}

	@Override
	public String toString() {
		return bundle.getString("product_id") + "=" + id + ", " + bundle.getString("product_name") + "=" + productName
				+ ", " + bundle.getString("category_name") + "=" + categoryName + ", " + bundle.getString("price") + "="
				+ price;
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
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(this.id);
		out.writeObject(this.productName);
		out.writeObject(this.categoryName);
		out.writeObject(this.price);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.id = (int) in.readObject();
		this.productName = (String) in.readObject();
		this.categoryName = (String) in.readObject();
		this.price = (double) in.readObject();
	}

}