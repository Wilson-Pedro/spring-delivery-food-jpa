package com.wamk.deliveryService.dtos;

import java.io.Serializable;

import com.wamk.deliveryService.entities.Client;
import jakarta.validation.constraints.NotBlank;

public class ClientDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "name is mandatory")
	private String name;
	
	@NotBlank(message = "phone is mandatory")
	private String phone;
	
	public ClientDTO() {
	}

	public ClientDTO(String name, String phone, Double total) {
		this.name = name;
		this.phone = phone;
	}

	public ClientDTO(Client client) {
		name = client.getName();
		phone = client.getPhone();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setTelefone(String telefone) {
		this.phone = telefone;
	}
}
