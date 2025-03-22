<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>
<shop:main_header_css_import />
<link rel="stylesheet" href="css/products.css">
</head>
<body>
	<shop:header />
	<main>
		<div class="filter-container">
			<h2>FILTER PRODUCTS</h2>
			<div class="filter-section">
				<label for="price-filter">By price</label>
				<div class="price-range">
					<span>$158</span> <span>-</span> <span>$1920</span>
				</div>
			</div>
		</div>
		<div class="search_products">
			<div class="products-container">
				<c:forEach items="${products}" var="product">
					<div id="product">
						<img src="images/product/${product.category.categoryName}.jpg" />
						<div class="product">
							<h3>${product.productName}}</h3>
							<p class="price">$${product.price}</p>
							<a href="./product?product_guid=${product.guid}" class="view-button">View</a>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="nav-numbers">
				<c:forEach var="numberOfPage" begin="1" end="${size_of_navbar}">
					<c:choose>
						<c:when test="${category_name != null}">
							<c:choose>
								<c:when test="${numberOfPage == active_page}">
									<a style="text-decoration: none; color: white;"
										href="./products?category_name=${category_name}&page_number=${numberOfPage}"><button
											class="active">${numberOfPage}</button></a>
								</c:when>
								<c:otherwise>
									<a style="text-decoration: none; color: black;"
										href="./products?category_name=${category_name}&page_number=${numberOfPage}"><button>${numberOfPage}</button></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${numberOfPage == active_page}">
									<a style="text-decoration: none; color: white;"
										href="./products?product_name=${product_name_to_search}&page_number=${numberOfPage}"><button
											class="active">${numberOfPage}</button></a>
								</c:when>
								<c:otherwise>
									<a style="text-decoration: none; color: black;"
										href="./products?product_name=${product_name_to_search}&page_number=${numberOfPage}"><button>${numberOfPage}</button></a>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>
	</main>

	<shop:footer />
	<shop:js_main_import />
</body>
</html>