package com.wamk.deliveryService.services;

import java.time.OffsetDateTime;
import java.util.List;

import com.wamk.deliveryService.api.assembler.EntregaAssembler;
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

	@Autowired
	private EntregaAssembler entregaAssembler;
	
	@Transactional
	public Order save(Order order) {
		return orderRepository.save(order);
	}
	
	public List<Order> findAll(){
		return orderRepository.findAll();
	}

	public Order saveOrder(Order order) {
		order.setDataEntrega(OffsetDateTime.now());
		return orderRepository.save(order);
	}

	public Order findById(Long id) {
		return orderRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException());
	}

	public Order update(Order order, Long id) {
		Order orderUpdated = findById(id);
		orderUpdated.setNameOrder(order.getNameOrder());
		orderUpdated.setQuantity(order.getQuantity());
		orderUpdated.setPrice(order.getPrice());
		orderUpdated.setId(id);
		return orderRepository.save(orderUpdated);
	}

//	@Transactional
//	public void delete(Long id) {
//		orderRepository.delete(findById(id));
//	}

	public void finish(Long id) {
		Order order = findById(id);
		order.finish();
		orderRepository.save(order);
	}
}
