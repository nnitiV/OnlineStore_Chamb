package com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.utils.comparators;

import java.util.Comparator;

import com.itbulls.learnit.cunha.javacore.jfc.collection.list.hw.backendonlineshop.enteties.Product;

public class CustomProductComparator implements Comparator<Product> {

	@Override
	public int compare(Product product1, Product product2) {
		int res = product1.getCategoryName().compareTo(product2.getCategoryName());
		if(res == 0) {
			res = Integer.compare(Integer.parseInt(String.valueOf(Math.round(product1.getPrice()))), Integer.parseInt(String.valueOf(Math.round(product2.getPrice()))));
			if(res == 0) {
				res = product1.getProductName().compareTo(product2.getProductName());
			}
		}
		return res;
	}
}
