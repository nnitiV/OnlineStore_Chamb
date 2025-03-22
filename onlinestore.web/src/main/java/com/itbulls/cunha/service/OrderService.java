package com.itbulls.cunha.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbulls.cunha.persistence.entities.Order;
import com.itbulls.cunha.persistence.repositories.OrderJpaRepository;

@Service
public class OrderService {

	@Autowired
	private OrderJpaRepository orderJpaRepository;
	
	@Transactional
	public List<Order> getAllOrders() {
		return (List<Order>) orderJpaRepository.findAll();
	}
	
	@Transactional
	public Order getOrderById(long orderId) { 
		return orderJpaRepository.findById(orderId).orElse(null);
	}
	
	@Transactional
	public boolean updateOrder(Order order) {
		return orderJpaRepository.save(order) != null;
	}
	
	@Transactional
	public boolean addOrder(Order order) {
		return orderJpaRepository.save(order) != null;
	}
	
	@Transactional
	public void deleteOrderById(long orderId) {
		orderJpaRepository.deleteById(orderId);
	}
}
