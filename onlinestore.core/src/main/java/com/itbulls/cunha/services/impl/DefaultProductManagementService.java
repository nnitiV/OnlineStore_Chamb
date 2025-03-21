package com.itbulls.cunha.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itbulls.cunha.dao.ProductDAO;
import com.itbulls.cunha.dao.impl.JpaProductDao;
import com.itbulls.cunha.dto.converters.ProductDtoToProductConverter;
import com.itbulls.cunha.entities.Product;
import com.itbulls.cunha.entities.impl.DefaultProduct;
import com.itbulls.cunha.services.ProductManagementService;

@Service
public class DefaultProductManagementService implements ProductManagementService {

	private static DefaultProductManagementService instance;

	private static List<Product> products;
	
	private ProductDAO productDAO;
	private ProductDtoToProductConverter productConverter;

	{
		productDAO = new JpaProductDao();
		productConverter = new ProductDtoToProductConverter();
	}
	
	static {
		initProducts();
	}

	private static void initProducts() {
		products = new ArrayList<>();
		Product[] productsToAdd = new Product[] {
				new DefaultProduct(1, "Hardwood Oak Suffolk Internal Door", "Doors", new BigDecimal("109.99")),
				new DefaultProduct(2, "Oregon Cottage Interior Oak Door", "Doors", new BigDecimal("179.99")),
				new DefaultProduct(3, "Oregon Cottage Horizontal Interior White Oak Door", "Doors",
						new BigDecimal("189.99")),
				new DefaultProduct(4, "4 Panel Oak Deco Interior Door", "Doors", new BigDecimal("209.09")),
				new DefaultProduct(5, "Worcester 2000 30kW Ng Combi Boiler Includes Free Comfort+ II controller",
						"Boilers", new BigDecimal("989.99")),
				new DefaultProduct(6, "Glow-worm Betacom 4 30kW Combi Gas Boiler ERP", "Boilers",
						new BigDecimal("787.99")),
				new DefaultProduct(7, "Worcester 2000 25kW Ng Combi Boiler with Free Comfort+ II controller", "Boilers",
						new BigDecimal("859.99")),
				new DefaultProduct(8,
						"Wienerberger Terca Class B Engineering Brick Red 215mm x 102.5mm x 65mm (Pack of 504)",
						"Bricks", new BigDecimal("402.99")),
				new DefaultProduct(9, "Wienerberger Terca Engineering Brick Blue Perforated Class B 65mm (Pack of 400)",
						"Bricks", new BigDecimal("659.99")),
				new DefaultProduct(10, "Wienerberger Engineering Brick Red Smooth Class B 73mm - Pack of 368", "Bricks",
						new BigDecimal("523.99")) };
		for (Product product : productsToAdd) {
			products.add(product);
		}
	}

	private DefaultProductManagementService() {

	}

	public static ProductManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultProductManagementService();
		}
		return instance;
	}

	@Override
	public List<Product> getProducts() {
		return products;
	}
	
	@Override
	public void retrieveProducts() {
		products = productConverter.convertListOfProductDtoToListOfProduct(productDAO.getAllProducts());	
	}

	@Override
	public Product getProductById(int productIdToAddToCart) {
		return productConverter.convertProductDtoToProduct(productDAO.getProductById(productIdToAddToCart));
	}

}