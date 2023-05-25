package com.wamk.deliveryService.entities.enums;

public enum OrderStatus {

	PREPARING(1),
	READY(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5),
	FINALIZADA(6);
	
	private int code;
	
	private OrderStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static OrderStatus valueOf(int code) {
		for(OrderStatus value : OrderStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code.");
	}
}
