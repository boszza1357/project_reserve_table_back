package com.project.project_reserve_table.model;


import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;


public class ReservationsRequestModel {

	private Integer userId; 
	private Integer tableId; 
	private Date bookingDate ;  // หน้าบ้านส่งมา
	private String status;  // เซตไว้
	
	private Time startTime; // หน้าบ้านส่งมา
	private Time endTime ; // หน้าบ้านส่งมา

	
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
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
	
	
}
