package com.wamk.deliveryService.dtos;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

public class ClientDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "name is mandatory")
	private String name;
	
	@NotBlank(message = "contato is mandatory")
	private String contato;
	
	public ClientDTO() {
	}

	public ClientDTO(String name, String contato, Double total) {
		this.name = name;
		this.contato = contato;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}
}
