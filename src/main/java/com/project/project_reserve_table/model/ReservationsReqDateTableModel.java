package com.project.project_reserve_table.model;

import java.util.Date;

public class ReservationsReqDateTableModel {

	
	private Integer tableId; 
	private Date bookingDate;
	
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
	
	
}
