package com.itbulls.cunha.dto.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itbulls.cunha.dao.impl.MySqlJdbcCategoryDao;
import com.itbulls.cunha.dto.ProductDTO;
import com.itbulls.cunha.entities.Product;
import com.itbulls.cunha.entities.impl.DefaultProduct;

@Service
public class ProductDtoToProductConverter {
	public Product convertProductDtoToProduct(ProductDTO productDTO) {
		Product product = new DefaultProduct(productDTO.getId(), productDTO.getProductName(),
				productDTO.getCategory().getCategoryName(), (productDTO.getPrice()), productDTO.getGuid());
		return product;
	}
	
	public ProductDTO convertProductToProductDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setProductName(product.getProductName());
		productDTO.setCategory(new MySqlJdbcCategoryDao().getCategoryByName(product.getProductCategoryName()));
		productDTO.setPrice(product.getPrice());
		productDTO.setGuid(product.getProductGuid());
		return productDTO;
	}
	
	public List<Product> convertListOfProductDtoToListOfProduct(List<ProductDTO> productsDTO) {
		List<Product> products = new ArrayList<>();
		productsDTO.stream().forEach(productDTO -> {
			products.add(convertProductDtoToProduct(productDTO));
		});
		return products;
	}
	
	public List<ProductDTO> convertListOfProductToProductDTO(List<Product> products) {
		List<ProductDTO> productsDTO = new ArrayList<>();
		products.stream().forEach(product -> {
			productsDTO.add(convertProductToProductDTO(product));
		});
		return productsDTO;
	}
}
