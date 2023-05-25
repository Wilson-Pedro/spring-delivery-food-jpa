package com.wamk.deliveryService.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.deliveryService.entities.Order;
import com.wamk.deliveryService.entities.enums.OrderStatus;
import com.wamk.deliveryService.repositories.OrderRepository;
import com.wamk.deliveryService.services.exception.EntityNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Transactional
	public Order save(Order order) {
		return orderRepository.save(order);
	}
	
	public List<Order> findAll(){
		return orderRepository.findAll();
	}
	
	public Order findById(Long id) {
		return orderRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Id not found: " + id));	
	}

	@Transactional
	public void delete(Order order) {
		orderRepository.delete(order);
	}
}
