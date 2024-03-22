package com.wamk.deliveryService.services;

import com.wamk.deliveryService.entities.Order;
import com.wamk.deliveryService.repositories.OrderRepository;
import com.wamk.deliveryService.services.exception.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Transactional
	public Order save(Order order) {
		order.setOrderDate(OffsetDateTime.now());
		return orderRepository.save(order);
	}
	
	public List<Order> findAll(){
		return orderRepository.findAll();
	}


	public Order findById(Long id) {
		return orderRepository.findById(id).orElseThrow(
				EntityNotFoundException::new);
	}

	public Order update(Order order, Long id) {
		Order orderUpdated = findById(id);
		orderUpdated.setNameOrder(order.getNameOrder());
		orderUpdated.setQuantity(order.getQuantity());
		orderUpdated.setPrice(order.getPrice());
		orderUpdated.setId(id);
		return orderRepository.save(orderUpdated);
	}

	@Transactional
	public void delete(Long id) {
		orderRepository.delete(findById(id));
	}

	public void finish(Long id) {
		Order order = findById(id);
		order.finish();
		orderRepository.save(order);
	}
}
