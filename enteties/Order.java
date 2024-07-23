package com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.enteties;

import java.util.ArrayList;

public interface Order {

	boolean isCreditCardNumberValid(String userInput);

	void setCreditCardNumber(String userInput);

	void setProducts(ArrayList<Product> products);

	void setCustomerId(int customerId);
	
	int getCustomerId();

}