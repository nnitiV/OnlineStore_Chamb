<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit profile</title>
<shop:main_header_css_import />
<link rel="stylesheet" href="css/editProfile.css" />
</head>
<body>
	<shop:header />
	<div class="form-container">
		<h2>Update Profile</h2>
		<form action="./edit-profile" method="POST">
			<div class="form-group">
				<label for="firstName">First Name:</label> <input type="text"
					id="firstName" name="firstName" value="${user.firstName}" required>
			</div>
			<div class="form-group">
				<label for="lastName">Last Name:</label> <input type="text"
					id="lastName" name="lastName" value="${user.lastName}" required>
			</div>
			<div class="form-group">
				<label for="email">Email:</label> <input type="email" id="email"
					name="email" value="${user.email}" required>
			</div>
			<div class="form-group">
				<label for="oldPassword">Old Password:</label> <input
					type="password" id="oldPassword" name="oldPassword"
					placeholder="Enter the old password" required>
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password"
					id="password" name="password" placeholder="Enter new password"
					required>
			</div>
			<c:if test="${messageToShow != null}">
				<p style="color: red; font-weight: bold">${messageToShow}</p>
			</c:if>
			<div class="form-actions">
				<a href="./profile"><button type="button">Cancel</button></a>
				<button type="submit">Update</button>
			</div>
		</form>
	</div>
	<shop:footer />
	<shop:js_main_import />
</body>
</html>