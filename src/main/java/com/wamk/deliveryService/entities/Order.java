package com.wamk.deliveryService.entities;

import java.io.Serializable;
import java.time.OffsetDateTime;

import com.wamk.deliveryService.dtos.OrderDTO;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wamk.deliveryService.entities.enums.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_ORDER")
public class Order extends RepresentationModel<Order> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nameOrder;
	private Integer quantity;
	private Double price;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private OffsetDateTime orderDate;
	private OffsetDateTime deliveryDate;
	private Integer status;
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	public Order() {
	}

	public Order(Long id, String nameOrder, Integer quantity, Double price, OffsetDateTime orderDate, OffsetDateTime deliveryDate, OrderStatus status, Client client) {
		this.id = id;
		this.nameOrder = nameOrder;
		this.quantity = quantity;
		this.price = price;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		setStatus(status);
		this.client = client;
	}

	public Order(OrderDTO orderDTO) {
		this.nameOrder = orderDTO.getNameOrder();
		this.quantity = orderDTO.getQuantity();
		this.price = orderDTO.getPrice();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public OffsetDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(OffsetDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public OffsetDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(OffsetDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public OrderStatus getStatus() {
		return OrderStatus.valueOf(status);
	}

	public void setStatus(OrderStatus status) {
		if(status != null) {
			this.status = status.getCode();
		}
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public Double getSubtotal() {
		return price * quantity;
	}

	public void finish() {
		setStatus(OrderStatus.FINISHED);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
