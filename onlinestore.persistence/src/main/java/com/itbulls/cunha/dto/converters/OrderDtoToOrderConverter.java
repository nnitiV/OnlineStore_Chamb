package com.itbulls.cunha.dto.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbulls.cunha.dto.OrderDTO;
import com.itbulls.cunha.entities.Order;
import com.itbulls.cunha.entities.impl.DefaultOrder;

@Service
public class OrderDtoToOrderConverter {

	@Autowired
	private ProductDtoToProductConverter productConverter;
	@Autowired
	private UserDtoToUserConverter userConverter;
	@Autowired
	private OrderStatusDtoToOrderStatusConverter orderStatusConverter;

	public Order convertOrderDtoToOrder(OrderDTO orderDTO) {
		Order order = new DefaultOrder(orderDTO.getId(),
				productConverter.convertListOfProductDtoToListOfProduct(orderDTO.getProducts()),
				userConverter.convertUserDtoToUser(orderDTO.getUser()), orderStatusConverter.convertOrderStatusDtoToOrderStatus(orderDTO.getOrderStatus()));
		return order;
	}
	
	public OrderDTO convertOrderToOrderDto(Order order) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(order.getOrderId());
		orderDTO.setUser(userConverter.convertUserToUserDto(order.getOrderUser()));
		orderDTO.setProducts(productConverter.convertListOfProductToProductDTO(order.getProducts()));
		orderDTO.setOrderStatus(orderStatusConverter.convertOrderStatusToOrderStatusDto(order.getOrderStatus()));
		return orderDTO;
	}
	
	public List<Order> convertListOfOrdersDtoToListOfOrder(List<OrderDTO> listOfOrdersDTO) {
		List<Order> listOfOrders = new ArrayList<>();
		listOfOrdersDTO.stream().forEach(orderDTO -> {
			listOfOrders.add(convertOrderDtoToOrder(orderDTO));
		});
		return listOfOrders;
	}
}
