package com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.enteties;

import java.util.ArrayList;

public interface Cart {

	boolean isEmpty();

	void addProduct(Product productById);

	ArrayList<Product> getProducts();

	void clear();

}