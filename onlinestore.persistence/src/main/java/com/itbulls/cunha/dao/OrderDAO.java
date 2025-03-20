package com.itbulls.cunha.dao;

import java.util.List;

import com.itbulls.cunha.dto.OrderDTO;

public interface OrderDAO {
	List<OrderDTO> getOrders();

	OrderDTO getOrderById(int id);

	void saveOrder(OrderDTO order);

	void updateOrder(OrderDTO order);
}
