package com.wamk.deliveryService.dtos;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

public class ClientDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "name is mandatory")
	private String name;
	
	@NotBlank(message = "contato is mandatory")
	private String telefone;
	
	public ClientDTO() {
	}

	public ClientDTO(String name, String telefone, Double total) {
		this.name = name;
		this.telefone = telefone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
