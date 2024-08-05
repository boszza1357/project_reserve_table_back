package com.project.project_reserve_table.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class ReservationsResponseModel {

	private Integer reservationsId; 
	private Integer userId; 
	private Integer tableId; 
	private Date bookingDate ; // วันที่ลูกค้าจอง
	private String status; 
	private Time startTime;  // เวลาที่ลูกค้าต้องการใช้
	private Time endTime ;  // เวลาที่ลูกค้าต้องการใช้
	private Timestamp createDate;  
	private Timestamp updateDate;
	
	public Integer getReservationsId() {
		return reservationsId;
	}
	public void setReservationsId(Integer reservationsId) {
		this.reservationsId = reservationsId;
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
