package com.wamk.deliveryService.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wamk.deliveryService.dtos.OrderDTO;
import com.wamk.deliveryService.entities.Order;
import com.wamk.deliveryService.services.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> list = orderService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id){
		Order order = orderService.findById(id);
		return ResponseEntity.ok().body(order);
	}
	
	@PostMapping
	public ResponseEntity<Order> saveCleint(@Valid @RequestBody OrderDTO orderDTO){
		var order = new Order();
		BeanUtils.copyProperties(orderDTO, order);
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(order));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Order> updateOrder(@Valid @RequestBody OrderDTO orderDTO, 
			@PathVariable Long id){
		Order obj = orderService.findById(id);
		var order = new Order();
		BeanUtils.copyProperties(orderDTO, order);
		order.setId(obj.getId());
		return ResponseEntity.ok(orderService.save(order));
	}
}
