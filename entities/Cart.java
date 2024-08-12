package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities;

import java.util.ArrayList;

public interface Cart {

	boolean isEmpty();

	void addProduct(Product productById);

	ArrayList<Product> getProducts();

	void clear();

}