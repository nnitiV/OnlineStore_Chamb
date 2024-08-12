package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services;

import java.util.ArrayList;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.Product;


public interface ProductManagementService {

	ArrayList<Product> getProducts();

	Product getProductById(int productIdToAddToCart);

}