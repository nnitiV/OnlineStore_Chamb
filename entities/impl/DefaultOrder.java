package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.impl;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.config.ApplicationContext;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.Order;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.Product;

public class DefaultOrder implements Order, Externalizable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER = 16;

	private int customerId;
	private String creditCardNumber;
	private List<Product> products;

	private ApplicationContext context;
	private ResourceBundle bundle;

	{
		context = ApplicationContext.getInstance();
		bundle = context.getBundle();
	}

	public DefaultOrder() {
	}

	public DefaultOrder(int customerId, String creditCardNumber, List<Product> products) {
		this.customerId = customerId;
		this.creditCardNumber = creditCardNumber;
		this.products = products;
	}

	@Override
	public boolean isCreditCardNumberValid(String creditCardNumber) {
		return creditCardNumber.length() == AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER && !creditCardNumber.contains(" ")
				&& Long.parseLong(creditCardNumber) > 0;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public List<Product> getProducts() {
		return products;
	}

	@Override
	public void setCreditCardNumber(String creditCardNumber) {
		if (creditCardNumber == null)
			return;
		this.creditCardNumber = creditCardNumber;
	}

	@Override
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	@Override
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public int getCustomerId() {
		return this.customerId;
	}

	@Override
	public String toString() {
		return bundle.getString("order") + ": " + bundle.getString("cusstomer_id") + this.customerId + "\t"
				+ bundle.getString("credit_card_number") + " - " + this.creditCardNumber + "\t"
				+ bundle.getString("products") + " - " + this.products.toString();
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(this.customerId);
		out.writeObject(this.creditCardNumber);
		out.writeObject(this.products);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.customerId = (int) in.readObject();
		this.creditCardNumber = (String) in.readObject();
		this.products = (List<Product>) in.readObject();
	}

}