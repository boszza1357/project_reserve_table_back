package com.project.project_reserve_table.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PaymentResponseModel {
	
	
	private Integer paymentId;
	private Integer reservationId;
	private Integer userId;
	private String statusPayment;
	private BigDecimal pricePayment ;
	private Timestamp createDate;
	
	
	
	public Integer getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
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
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	
}
