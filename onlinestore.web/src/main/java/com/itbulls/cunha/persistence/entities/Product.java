package com.itbulls.cunha.persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.itbulls.cunha.persistence.entities.Category;

@Entity
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_price")
	private BigDecimal productPrice;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;

	@Column(name = "guid")
	private String guid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return productPrice;
	}

	public void setPrice(BigDecimal price) {
		this.productPrice = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getProduct_name() {
		return productName;
	}

	public void setProduct_name(String product_name) {
		this.productName = product_name;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", product_name=" + productName + ", price=" + productPrice + ", category="
				+ category + "]";
	}
}
