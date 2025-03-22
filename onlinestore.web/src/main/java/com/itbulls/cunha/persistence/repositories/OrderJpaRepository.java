package com.itbulls.cunha.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itbulls.cunha.persistence.entities.Order;

@Repository
public interface OrderJpaRepository extends CrudRepository<Order, Long> {
}
