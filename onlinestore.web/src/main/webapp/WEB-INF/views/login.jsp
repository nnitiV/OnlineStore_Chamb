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
<title>Login</title>
<link rel="icon" type="image/png" href="images/sofa.png">
<shop:main_header_css_import />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">
</head>
<style>
.remember-me-group {
	display: flex;
	align-items: center;
	margin: 1vw 0;
}

.remember-me-group label {
	cursor: pointer;
}

.remember-me-group input[type="checkbox"] {
	opacity: 0; /* Esconde o checkbox padrão */
	position: absolute;
}

.remember-me-group input[type="checkbox"]+label::before {
	content: "";
	display: inline-block;
	width: 16px; /* Tamanho do checkbox personalizado */
	height: 16px;
	margin-right: 0.5vw;
	border: 2px solid #ccc; /* Cor da borda */
	border-radius: 4px; /* Bordas arredondadas */
	background-color: white;
	vertical-align: middle;
}

.remember-me-group input[type="checkbox"]:checked+label::before {
	background-color: black; /* Cor de fundo quando marcado */
	border-color: black; /* Cor da borda quando marcado */
}

.remember-me-group input[type="checkbox"]:checked+label::after {
	content: "✔"; /* Ícone de check */
	position: absolute;
	left: 4px; /* Ajuste a posição do ícone */
	top: 2px;
	color: white; /* Cor do ícone */
	font-size: 12px;
}
</style>
<body>
	<shop:header />
	<div class="background">
		<div class="container_login">
			<h1>
				<fmt:message key="welcome" bundle="${resourceBundle}" />
			</h1>
			<form action="perform_login" method="POST">
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

				<div class="remember-me-group">
					<input type="checkbox" id="remember" name="remember"> <label
						for="remember">Remember me</label>
				</div>

				<c:if test="${error != null }">
					<p style="color: red">${error}</p>
				</c:if>
				<button class="button_login">
					<fmt:message key="login" bundle="${resourceBundle}" />
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
