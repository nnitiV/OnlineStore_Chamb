package com.itbulls.cunha.entities.impl;

import com.itbulls.cunha.entities.Category;

public class DefaultCategory implements Category {
	private int id;
	private String categoryName;

	public DefaultCategory(int id, String categoryName) {
		this.id = id;
		this.categoryName = categoryName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
