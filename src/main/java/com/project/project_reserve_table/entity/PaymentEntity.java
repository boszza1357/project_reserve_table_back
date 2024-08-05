package com.project.project_reserve_table.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="payment")
public class PaymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Column(name = "reservation_id")
	private Integer reservationId;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "status_payment")
	private String statusPayment;
	
	@Column(name = "price_payment")
	private BigDecimal pricePayment;
	
	@Column(name = "create_date")
	private Timestamp  createDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
