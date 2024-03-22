package com.wamk.deliveryService.dtos;

import com.wamk.deliveryService.entities.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddressDTO {

	@NotBlank(message = "cep is mandatory")
	private String  cep;
	
	@NotBlank(message = "neighborhood is mandatory")
	private String neighborhood;
	
	@NotBlank(message = "street is mandatory")
	private String street;
	
	@NotNull(message = "houseNumber is mandatory")
	private Integer houseNumber;
	
	public AddressDTO() {
	}

	public AddressDTO(String cep, String neighborhood, String street, Integer houseNumber) {
		this.cep = cep;
		this.neighborhood = neighborhood;
		this.street = street;
		this.houseNumber = houseNumber;
	}

	public AddressDTO(Address address) {
		cep = address.getCep();
		neighborhood = address.getNeighborhood();
		street = address.getStreet();
		houseNumber = address.getHouseNumber();
	}

	public String getCep() {
		return cep;
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
