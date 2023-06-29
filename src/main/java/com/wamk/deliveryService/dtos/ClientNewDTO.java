package com.wamk.deliveryService.dtos;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ClientNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "name is mandatory")
	private String name;
	
	@NotBlank(message = "contato is mandatory")
	private String contato;
	
	@NotBlank(message = "CEP is mandatory")
	private String  CEP;
	
	@NotBlank(message = "bairro is mandatory")
	private String bairro;
	
	@NotBlank(message = "rua is mandatory")
	private String rua;
	
	@NotNull(message = "numeroCasa cannot be null")
	private Integer numeroCasa;
	
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

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
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
