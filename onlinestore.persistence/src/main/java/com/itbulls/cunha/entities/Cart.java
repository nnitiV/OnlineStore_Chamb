package com.itbulls.cunha.entities;

public interface Cart {

	boolean isEmpty();

	void addProduct(Product productById);

	Product[] getProducts();

	void clear();

}