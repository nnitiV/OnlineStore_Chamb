package com.itbulls.cunha.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

import com.itbulls.cunha.dao.OrderDAO;
import com.itbulls.cunha.dto.OrderDTO;
import com.itbulls.cunha.dto.ProductDTO;

@Repository
public class JpaOrderDao implements OrderDAO {

	@Override
	public List<OrderDTO> getOrders() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			List<OrderDTO> ordersDto = em.createQuery("SELECT o FROM orders o", OrderDTO.class).getResultList();
			ordersDto.stream().forEach(order -> order.getProducts().stream().forEach(ProductDTO::getId));
			em.getTransaction().commit();
			return ordersDto;
		} finally {
			if (emf != null) {
				emf.close();
			}
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public OrderDTO getOrderById(int id) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			OrderDTO orderDto = em.find(OrderDTO.class, id);
			em.getTransaction().commit();
			return orderDto;
		} finally {
			if (emf != null) {
				emf.close();
			}
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public void saveOrder(OrderDTO order) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(order);
			em.getTransaction().commit();
		} finally {
			if (emf != null) {
				emf.close();
			}
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public void updateOrder(OrderDTO order) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(order);
			em.getTransaction().commit();
		} finally {
			if (emf != null) {
				emf.close();
			}
			if (em != null) {
				em.close();
			}
		}
	}

}
