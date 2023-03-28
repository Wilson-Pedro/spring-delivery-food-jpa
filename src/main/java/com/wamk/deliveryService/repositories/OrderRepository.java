package com.wamk.deliveryService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wamk.deliveryService.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
