package com.itbulls.cunha.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itbulls.cunha.entities.Order;

@Repository
public interface OrderJpaRepository extends CrudRepository<Order, Long> {
}
