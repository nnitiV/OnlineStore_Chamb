package com.itbulls.cunha.dto.converters;

import org.springframework.stereotype.Service;

import com.itbulls.cunha.dto.OrderStatusDTO;
import com.itbulls.cunha.entities.OrderStatus;

@Service
public class OrderStatusDtoToOrderStatusConverter {
	
	public OrderStatus convertOrderStatusDtoToOrderStatus(OrderStatusDTO orderStatusDto) {
		return OrderStatus.valueOf(orderStatusDto.name());
	}
	
	public OrderStatusDTO convertOrderStatusToOrderStatusDto(OrderStatus orderStatus) {
		return OrderStatusDTO.valueOf(orderStatus.name());
	}
}
