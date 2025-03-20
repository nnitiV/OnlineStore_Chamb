<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product</title>
<shop:main_header_css_import />
<link rel="stylesheet" href="css/product.css" />
</head>
<body>
	<shop:header />
	<main>
		<div class="product-card">
			<div id="product-desc">
				<h2>${product.productName}</h2>
				<img style="width: 250px; height: 250px;"
					src="images/product/${product.productCategoryName}.jpg" alt="" />
			</div>
			<div id="product-action">
				<div class="description">
					<p>This is a good laptop.</p>
				</div>
				<div class="price">$${product.price}</div>
				<form action="./product" method="POST">
					<input type="hidden" name="product_guid" value="${product.guid}">
					<button type="submit" class="buy-button">Buy</button>
				</form>
				<c:if test="${succesfully_added_order_text != null}">
					<p class="saved_order_text">${succesfully_added_order_text}</p>
				</c:if>
			</div>
		</div>
	</main>
	<shop:footer />
	<shop:js_main_import />
</body>
</html>