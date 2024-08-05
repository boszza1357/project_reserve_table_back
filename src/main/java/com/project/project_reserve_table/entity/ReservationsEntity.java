package com.project.project_reserve_table.entity;


import java.sql.Time;
import java.sql.Timestamp;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name= "reservations")
public class ReservationsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "table_id")
	private Integer tableId;
	
	@Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "booking_date")
	private Date  bookingDate;
	
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "start_time")
	private Time startTime;
	
	@Column(name = "end_time")
	private Time  endTime;
	
	@Column(name = "create_date")
	private Timestamp createDate;
	
	@Column(name = "update_date")
	private Timestamp updateDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Time  getStartTime() {
		return startTime;
	}

	public void setStartTime(Time  startTime) {
		this.startTime = startTime;
	}

	public Time  getEndTime() {
		return endTime;
	}

	public void setEndTime(Time  endTime) {
		this.endTime = endTime;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
