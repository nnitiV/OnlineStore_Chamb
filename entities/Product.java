package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities;

public interface Product {

	int getId();

	String getProductName();
	
	String getCategoryName();
	
	double getPrice();

	void setPrice(double price);
}