package com.project.project_reserve_table.model;

import java.math.BigDecimal;

public class PaymentRequestModel {

	private Integer reservationId;
	private Integer userId;
	private String statusPayment;
	private BigDecimal pricePayment ;
	public Integer getReservationId() {
		return reservationId;
	}
	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getStatusPayment() {
		return statusPayment;
	}
	public void setStatusPayment(String statusPayment) {
		this.statusPayment = statusPayment;
	}
	public BigDecimal getPricePayment() {
		return pricePayment;
	}
	public void setPricePayment(BigDecimal pricePayment) {
		this.pricePayment = pricePayment;
	}
	
	
	
}
