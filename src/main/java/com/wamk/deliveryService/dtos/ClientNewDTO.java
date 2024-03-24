package com.wamk.deliveryService.dtos;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ClientNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "name is mandatory")
	private String name;
	
	@NotBlank(message = "phone is mandatory")
	private String phone;
	
	@NotBlank(message = "CEP is mandatory")
	private String  cep;
	
	@NotBlank(message = "neighborhood is mandatory")
	private String neighborhood;
	
	@NotBlank(message = "street is mandatory")
	private String street;
	
	@NotNull(message = "houseNumber cannot be null")
	private Integer houseNumber;
	
	public ClientNewDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCep() {
		return cep;
	}

	public ClientNewDTO(Long id, String name, String phone, String cep, String neighborhood, String street, Integer houseNumber) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.cep = cep;
		this.neighborhood = neighborhood;
		this.street = street;
		this.houseNumber = houseNumber;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}
}
