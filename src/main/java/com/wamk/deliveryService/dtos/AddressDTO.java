package com.wamk.deliveryService.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddressDTO {

	@NotBlank(message = "cep is mandatory")
	private String  cep;
	
	@NotBlank(message = "bairro is mandatory")
	private String bairro;
	
	@NotBlank(message = "rua is mandatory")
	private String rua;
	
	@NotNull(message = "numeroCasa is mandatory")
	private Integer numeroCasa;
	
	public AddressDTO() {
	}

	public AddressDTO(String cep, String bairro, String rua, Integer numeroCasa) {
		this.cep = cep;
		this.bairro = bairro;
		this.rua = rua;
		this.numeroCasa = numeroCasa;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumeroCasa() {
		return numeroCasa;
	}

	public void setNumeroCasa(Integer numeroCasa) {
		this.numeroCasa = numeroCasa;
	}
}
