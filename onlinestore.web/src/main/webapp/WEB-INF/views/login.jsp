<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop/"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- Retrieve the locale from the session -->
<c:set var="userLocale" value="${sessionScope.userLocale}" />
<c:if test="${empty userLocale}">
	<c:set var="userLocale" value="${pageContext.request.locale}" />
</c:if>

<!-- Set the ResourceBundle using the user's locale -->
<fmt:setLocale value="${userLocale}" />
<fmt:setBundle basename="MyLabels" var="resourceBundle" />
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<link rel="icon" type="image/png" href="images/sofa.png">
<shop:main_header_css_import />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
	<shop:header />
	<div class="background">
		<div class="container_login">
			<h1>
				<fmt:message key="welcome" bundle="${resourceBundle}" />
			</h1>
			<form action="login" method="POST">
				<div class="form-group">
					<label for="email"><fmt:message key="email"
							bundle="${resourceBundle}" /></label> <input type="email" id="email"
						name="email" required>
				</div>
				<div class="form-group">
					<label for="password"><fmt:message key="password"
							bundle="${resourceBundle}" /></label> <input type="password"
						id="password" name="password" required>
				</div>
				<c:if test="${error != null }">
					<p style="color: red">${error}</p>
				</c:if>
				<button class="button_login" type="submit">
					<fmt:message key="sign.up" bundle="${resourceBundle}" />
				</button>
			</form>
			<p class="signup-link">
				<fmt:message key="doesnt.account" bundle="${resourceBundle}" />
				<a href="./signup"><fmt:message key="sign.up.login"
						bundle="${resourceBundle}" /></a>
			</p>
		</div>
	</div>
	<shop:footer />
	<shop:js_main_import />
</body>
</html>


Can you transform this jsp tags into spring form tags?