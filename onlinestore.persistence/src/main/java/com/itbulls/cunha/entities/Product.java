package com.itbulls.cunha.entities;

import java.io.Serializable;
import java.math.BigDecimal;

public interface Product extends Serializable {

	int getId();

	String getProductName();
	String getProductCategoryName();
	String getProductGuid();
	BigDecimal getPrice();
	String getGuid();

	void setPrice(BigDecimal d);

}