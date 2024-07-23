package com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.services;

import java.util.ArrayList;

import com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.enteties.Product;

public interface ProductManagementService {

	ArrayList<Product> getProducts();

	Product getProductById(int productIdToAddToCart);

}