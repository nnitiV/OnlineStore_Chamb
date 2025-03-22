package com.itbulls.cunha.services;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbulls.cunha.entities.Order;
import com.itbulls.cunha.entities.OrderStatus;
import com.itbulls.cunha.entities.Product;
import com.itbulls.cunha.entities.User;
import com.itbulls.cunha.repositories.OrderJpaRepository;
import com.itbulls.cunha.repositories.UserJpaRepository;

@Service
public class OrderService {

	@Autowired
	private OrderJpaRepository orderJpaRepository;
	@Autowired
	private UserJpaRepository userJpaRepository;
	
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
		System.out.println("Tentando salvar o pedido: " + order);
	    Order savedOrder = orderJpaRepository.save(order);
	    System.out.println("Pedido salvo com ID: " + savedOrder.getId());
	    return savedOrder != null;
	}
	
	@Transactional
	public void deleteOrderById(long orderId) {
		orderJpaRepository.deleteById(orderId);
	}
	
	@Transactional 
	public void updateOrderStatus(Order order) {
		order.setOrderStatus(order.getOrderStatus().next());
		orderJpaRepository.save(order);
		if(order.getOrderStatus().equals(OrderStatus.COMPLETED)) {
			User referralUser = order.getUser().getReferralUser();
			if(referralUser != null) {
				BigDecimal totalPurhcase = order.getProducts().stream().map(Product::getPrice).reduce(BigDecimal.ZERO,
						BigDecimal::add);
				referralUser.setCredit(referralUser.getCredit() + (totalPurhcase.doubleValue() * 0.02));
				userJpaRepository.save(referralUser);
			}
		}
	}
}
