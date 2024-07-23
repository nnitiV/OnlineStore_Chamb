package com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.enteties;

public interface Product {

	int getId();

	String getProductName();
	
	String getCategoryName();
	
	double getPrice();

	void setPrice(double price);
}