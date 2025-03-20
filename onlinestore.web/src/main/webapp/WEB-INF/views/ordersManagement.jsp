<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Active Orders</title>
<link rel="stylesheet" href="css/ordersManagement.css">
</head>
<body>
	<div id="background">
		<div class="orders_container">
			<h1>Active Orders</h1>
			<c:choose>
				<c:when test="${orders != null}">
					<!-- Flag to check if all orders are completed -->
					<c:set var="allCompleted" value="true" />

					<table>
						<thead>
							<tr>
								<th>Order Id</th>
								<th>User Email</th>
								<th>Order Status</th>
								<th>Action Button</th>
							</tr>
						</thead>
						<c:forEach items="${orders}" var="order">
							<!-- If any order is not completed, set the flag to false -->
							<c:if test="${order.orderStatus != 'COMPLETED'}">
								<c:set var="allCompleted" value="false" />
							</c:if>

							<!-- Display order if not completed -->
							<c:if test="${order.orderStatus != 'COMPLETED'}">
								<tbody>
									<tr>
										<td>${order.orderId}</td>
										<td>${order.orderUser.email}</td>
										<td>${order.orderStatus}</td>
										<td><a
											href="./updateOrderStatus?order_id=${order.orderId}"
											class="action-button">Mark fulfillment Stage as Completed</a></td>
									</tr>
								</tbody>
							</c:if>
						</c:forEach>
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
</body>
</html>