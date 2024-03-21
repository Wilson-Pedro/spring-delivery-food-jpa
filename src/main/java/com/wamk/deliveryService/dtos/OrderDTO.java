package com.wamk.deliveryService.dtos;

import java.io.Serializable;

import com.wamk.deliveryService.entities.Order;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrderDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "nameOrder is mandatory")
	private String nameOrder;
	
	@NotNull(message = "quantity cannot be null")
	private Integer quantity;
	
	@NotNull(message = "price cannot be null")
	private Double price;
	
	private ClientDTO clientDto;
	
	public OrderDTO() {
	}

	public OrderDTO(String nameOrder, Integer quantity, Double price, ClientDTO clientDto) {
		this.nameOrder = nameOrder;
		this.quantity = quantity;
		this.price = price;
		this.clientDto = clientDto;
	}

	public OrderDTO(Order order) {
		nameOrder = order.getNameOrder();
		quantity = order.getQuantity();
		price = order.getPrice();
	}

	public String getNameOrder() {
		return nameOrder;
	}

	public void setNameOrder(String nameOrder) {
		this.nameOrder = nameOrder;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ClientDTO getClientDto() {
		return clientDto;
	}

	public void setClientDto(ClientDTO clientDto) {
		this.clientDto = clientDto;
	}
}
