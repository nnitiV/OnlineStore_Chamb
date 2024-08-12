package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface Order  {

	boolean isCreditCardNumberValid(String userInput);

	void setCreditCardNumber(String userInput);

	void setProducts(ArrayList<Product> products);

	void setCustomerId(int customerId);
	
	int getCustomerId();
	
	String getCreditCardNumber();
	
	List<Product> getProducts();


}