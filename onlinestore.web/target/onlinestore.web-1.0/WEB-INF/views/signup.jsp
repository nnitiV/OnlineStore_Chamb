<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop/"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
<title>Sign Up</title>
<link rel="icon" type="image/png" href="images/sofa.png">
<shop:main_header_css_import />
<link rel="stylesheet" href="css/signup.css">
</head>
<body>
	<shop:header />
	<div class="background">
		<div class="container_signup">
			<h1>
				<fmt:message key="sign.up" bundle="${resourceBundle}" />
			</h1>
			<form:form action="${pageContext.request.contextPath}/signup" modelAttribute="userSignUp" method="POST">
				<div class="form-group">
					<label for="firstName"> <fmt:message key="first.name"
							bundle="${resourceBundle}" />
					</label>
					<form:input path="firstName" id="firstName"
						required="true" />
					<form:errors path="firstName" cssClass="error" />
				</div>

				<div class="form-group">
					<label for="lastName"> <fmt:message key="last.name"
							bundle="${resourceBundle}" />
					</label>
					<form:input path="lastName" id="lastName"
						required="true" />
					<form:errors path="lastName" cssClass="error" />
				</div>

				<div class="form-group">
					<label for="email"> <fmt:message key="email"
							bundle="${resourceBundle}" />
					</label>
					<form:input path="email" id="email" required="true" />
					<form:errors path="email" cssClass="error" />
				</div>

				<div class="form-group">
					<label for="password"> <fmt:message key="password"
							bundle="${resourceBundle}" />
					</label>
					<form:password path="password" id="password" required="true" />
					<form:errors path="password" cssClass="error" />
				</div>

				<div class="form-group">
					<label for="repeatPassword"> <fmt:message
							key="repeat.password" bundle="${resourceBundle}" />
					</label>
					<form:password path="repeatPassword" id="repeatPassword"
						required="true" />
					<form:errors path="repeatPassword" cssClass="error" />
				</div>

				<button class="button_signup" type="submit">
					<fmt:message key="sign.up" bundle="${resourceBundle}" />
				</button>

				<c:if test="${error != null}">
					<p style="color: red">${error}</p>
				</c:if>
			</form:form>
			<p class="login-link">
				<fmt:message key="have.account" bundle="${resourceBundle}" />
				<a href="login"><fmt:message key="sign.in"
						bundle="${resourceBundle}" /></a>
			</p>
		</div>
	</div>
	<shop:footer />
	<shop:js_main_import />
</body>
</html>