<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop/"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Chamb - Responsive E-commerce HTML5 Template</title>
<shop:main_header_css_import />
<link rel="stylesheet" href="css/profile.css" />
</head>
<body>
	<shop:header />
	<!-- Profile page -->
	<div class="profile-box banner-p">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="profile-b">
						<img src="images/lag-63.png" alt="#" />
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="product-profile-box">
		<div class="container">
			<div class="row">
				<div class="col-md-2 col-sm-4 pr">
					<div class="profile-pro-left">
						<div class="left-profile-box-m">
							<div class="pro-img">
								<img src="images/150x150.png" alt="#" />
							</div>
							<div class="pof-text">
								<h3>${user.firstName}${user.lastName}</h3>
								<!-- <div class="check-box"></div> -->
							</div>
							<a href="edit-profile">Edit</a>
						</div>
					</div>
				</div>
				<div class="col-md-10 col-sm-8">
					<div class="profile-pro-right">
						<div class="panel with-nav-tabs panel-default">
							<div class="panel-heading clearfix">
								<ul class="nav nav-tabs pull-left">
									<li class="active"><a href="#tab1default"
										data-toggle="tab">Personal Info</a></li>
									<li><a href="#tab2default" data-toggle="tab">Referrals</a></li>
								</ul>
							</div>
							<div class="panel-body">
								<div class="tab-content">
									<div class="tab-pane fade in active" id="tab1default">
										<table>
											<thead>
												<tr>
													<th>First Name:</th>
													<td>${user.firstName}</td>
												</tr>
												<tr>
													<th>Last Name:</th>
													<td>${user.lastName}</td>
												</tr>
												<tr>
													<th>Email:</th>
													<td>${user.email}</td>
												</tr>
											</thead>
											<tbody>
												<tr>
													<th>Money:</th>
													<td>${user.credit}</td>
												</tr>
												<tr>
													<th>Roles:</th>
													<td>${user.roles}</td>
												</tr>
												<tr>
													<th>Partner Code:</th>
													<td>${user.partnerCode}</td>
												</tr>
												<tr>
													<th>Partner Link (Use this link to invite referrals):</th>
													<td><a
														href="localhost:8080/online-store.web/homepage?partner_code=${user.partnerCode}">localhost:8080/online-store.web/homepage?partner_code=${user.partnerCode}</a></td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="tab-pane fade" id="tab2default">
										<table>
											<c:choose>
												<c:when test="${referralUsers != null}">
													<c:choose>
														<c:when test="${referralUsers.size() > 0}">
															<c:forEach items="${referralUsers}" var="referralUser">
																<thead>
																	<tr>
																		<th>First Name</th>
																		<th>Last Name</th>
																		<th>Email</th>
																	</tr>
																</thead>
																<tbody>
																	<tr>
																		<td>${referralUser.firstName}</td>
																		<td>${referralUser.lastName}</td>
																		<td>${referralUser.email}</td>
																	</tr>
																</tbody>
															</c:forEach>
														</c:when>
														<c:otherwise>
															<tbody>
																<tr>
																	<td colspan="3" style="text-align: center;">
																		<h3>NO REFERRAL USERS YET!</h3>
																	</td>
																</tr>
															</tbody>
														</c:otherwise>
													</c:choose>
										</table>
										<!-- If all orders are completed, show "No orders at the moment!" -->
										<c:if test="${allCompleted}">
											<p>No orders at the moment!</p>
										</c:if>
										</c:when>
										<c:otherwise>
											<p>No orders at the moment!</p>
										</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<shop:footer />
	<shop:js_main_import />
</body>
</html>