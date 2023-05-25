package com.wamk.deliveryService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.deliveryService.entities.Order;
import com.wamk.deliveryService.repositories.OrderRepository;

@Service
public class FinalizeOrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderService orderService;
	
	public void finalizar(Long id) {
		Order order = orderService.findById(id);
		order.finalizar(order);
		
		orderRepository.save(order);
	}
}
