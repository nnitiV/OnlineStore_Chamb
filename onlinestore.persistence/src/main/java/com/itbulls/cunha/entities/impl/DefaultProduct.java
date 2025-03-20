package com.itbulls.cunha.entities.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;

import com.itbulls.cunha.entities.Product;

public class DefaultProduct implements Product {

	private int id;
	private String productName;
	private String categoryName;
	private BigDecimal price;
	private String guid;

	public DefaultProduct() {
	}

	public DefaultProduct(int id, String productName, String categoryName, BigDecimal price) {
		this.id = id;
		this.productName = productName;
		this.categoryName = categoryName;
		this.price = price;
	}
	
	public DefaultProduct(int id, String productName, String categoryName, BigDecimal price, String guid) {
		this.id = id;
		this.productName = productName;
		this.categoryName = categoryName;
		this.price = price;
		this.guid = guid;
	}

	public String getProductCategoryName() {
		return categoryName;
	}

	@Override
	public String toString() {
		return "DefaultProduct [id=" + id + ", productName=" + productName + ", categoryName=" + categoryName
				+ ", price=" + price + "]";
	}

	@Override
	public int getId() {
		return this.id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	@Override
	public String getProductName() {
		return this.productName;
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
	}

	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		ois.defaultReadObject();
	}

	@Override
	public String getProductGuid() {
		return this.guid;
	}
}