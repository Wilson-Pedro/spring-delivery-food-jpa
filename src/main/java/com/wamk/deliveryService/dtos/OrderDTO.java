package com.wamk.deliveryService.dtos;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrderDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "nameOrder is mandatory")
	private String nameOrder;
	
	@NotNull(message = "price is mandatory")
	private Double price;
	
	private ClientDTO cliente;
	
	public OrderDTO() {
	}

	public OrderDTO(String nameOrder, Double price, ClientDTO cliente) {
		this.nameOrder = nameOrder;
		this.price = price;
		this.cliente = cliente;
	}

	public String getNameOrder() {
		return nameOrder;
	}

	public void setNameOrder(String nameOrder) {
		this.nameOrder = nameOrder;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ClientDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClientDTO cliente) {
		this.cliente = cliente;
	}
}
