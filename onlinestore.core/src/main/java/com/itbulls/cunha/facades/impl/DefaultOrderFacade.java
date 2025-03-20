package com.itbulls.cunha.facades.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbulls.cunha.dao.OrderDAO;
import com.itbulls.cunha.dao.UserDAO;
import com.itbulls.cunha.dto.converters.OrderDtoToOrderConverter;
import com.itbulls.cunha.dto.converters.UserDtoToUserConverter;
import com.itbulls.cunha.entities.Order;
import com.itbulls.cunha.entities.OrderStatus;
import com.itbulls.cunha.entities.Product;
import com.itbulls.cunha.entities.User;
import com.itbulls.cunha.entities.impl.DefaultOrder;
import com.itbulls.cunha.facades.OrderFacade;

@Service
public class DefaultOrderFacade implements OrderFacade {

	private static final int RANDOM_ORDER_ID = 1;
	@Autowired
	private OrderDAO orderDao;
	@Autowired
	private UserDAO userDao;
	@Autowired
	private UserDtoToUserConverter userConverter;
	@Autowired
	private OrderDtoToOrderConverter orderConverter;


	@Override
	public void saveOrder(Product product, User user) {
		Order order = new DefaultOrder(RANDOM_ORDER_ID, Arrays.asList(product), user);
		orderDao.saveOrder(orderConverter.convertOrderToOrderDto(order));
	}

	@Override
	public List<Order> getAllOrders() {
		return orderConverter.convertListOfOrdersDtoToListOfOrder(orderDao.getOrders());
	}

	@Override
	public Order getOrderById(int orderId) {
		return orderConverter.convertOrderDtoToOrder(orderDao.getOrderById(orderId));
	}

	@Override
	public void updateOrderStatus(Order order) {
		System.out.println("=======================================================");
		System.out.println("Order: " + order);
		User user = order.getOrderUser().getReferralUser();
		if (user != null) {
			System.out.println("=======================================================");
			System.out.println("Referral user: " + user);
			System.out.println("=======================================================");
			System.out.println("Credit: " + user.getCredit());
		}
		System.out.println("=======================================================");
		OrderStatus orderStatus = order.getOrderStatus();
		orderStatus = orderStatus.next();
		order.setOrderStatus(orderStatus);
		orderDao.updateOrder(orderConverter.convertOrderToOrderDto(order));
		if (orderStatus.equals(OrderStatus.COMPLETED)) {
			if (user != null) {
				BigDecimal totalPurhcase = order.getProducts().stream().map(Product::getPrice).reduce(BigDecimal.ZERO,
						BigDecimal::add);
				user.setCredit(user.getCredit() + (totalPurhcase.doubleValue() * 0.02));
				userDao.updateUser(userConverter.convertUserToUserDto(user));
			}
		}
	}

}
