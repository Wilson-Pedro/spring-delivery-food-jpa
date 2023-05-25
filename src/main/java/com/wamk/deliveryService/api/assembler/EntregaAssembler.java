package com.wamk.deliveryService.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.wamk.deliveryService.dtos.OrderDTO;
import com.wamk.deliveryService.entities.Order;

@Component
public class EntregaAssembler {

	private ModelMapper modelMapper;
	
	public EntregaAssembler(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public Order toEntity(OrderDTO orderDTO) {
		return modelMapper.map(orderDTO, Order.class);
	}
	
	public OrderDTO toDTO(Order order) {
		return modelMapper.map(order, OrderDTO.class);
	}
	
	public List<OrderDTO> toCollectionDTO(List<Order> orders){
		return orders.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}
}
