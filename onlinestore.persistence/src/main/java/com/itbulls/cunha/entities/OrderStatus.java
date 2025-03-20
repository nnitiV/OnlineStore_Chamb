package com.itbulls.cunha.entities;

public enum OrderStatus {
	RECEIVE_REQUEST, WAITING_FOR_PAYEMENT, PAYED, SHIPPING, SHIPPED, COMPLETED;

	public OrderStatus next() {
		switch (this) {
		case RECEIVE_REQUEST:
			return WAITING_FOR_PAYEMENT;
		case WAITING_FOR_PAYEMENT:
			return PAYED;
		case PAYED:
			return SHIPPING;
		case SHIPPING:
			return SHIPPED;
		case SHIPPED:
			return COMPLETED;
		case COMPLETED:
			return COMPLETED; // No next status after COMPLETED
		default:
			throw new IllegalStateException("Unknown status: " + this);
		}
	}
}