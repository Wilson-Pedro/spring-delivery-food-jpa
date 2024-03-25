package com.wamk.deliveryService.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wamk.deliveryService.api.assembler.EntregaAssembler;
import com.wamk.deliveryService.dtos.OrderDTO;
import com.wamk.deliveryService.entities.Order;
import com.wamk.deliveryService.services.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private EntregaAssembler entregaAssembler;

	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> list = orderService.findAll();
		if(!list.isEmpty()) {
			for(Order order : list) {
				Long id = order.getId();
				order.add(linkTo(methodOn(OrderController.class).findById(id)).withSelfRel());
			}
		}
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id){
		Order order = orderService.findById(id);
		order.add(linkTo(methodOn(OrderController.class).findAll()).withSelfRel());
		return ResponseEntity.ok().body(order);
	}
	
	@PostMapping
	public ResponseEntity<OrderDTO> saveOrder(@Valid @RequestBody OrderDTO orderDTO){
		Order orderSave = orderService.save(entregaAssembler.toEntity(orderDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(entregaAssembler.toDTO(orderSave));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Order> updateOrder(@Valid @RequestBody OrderDTO orderDTO, 
			@PathVariable Long id){
		Order orderUpdated = orderService.update(entregaAssembler.toEntity(orderDTO), id);
		return ResponseEntity.ok(orderUpdated);
	}
	
	@PutMapping(value = "/{id}/finalizacao")
	public ResponseEntity<Void> finalizeOrder(@PathVariable Long id){
		orderService.finish(id);
		return ResponseEntity.noContent().build();
	}

//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> delete(@PathVariable Long id) {
//		orderService.delete(id);
//		return ResponseEntity.noContent().build();
//	}
}
