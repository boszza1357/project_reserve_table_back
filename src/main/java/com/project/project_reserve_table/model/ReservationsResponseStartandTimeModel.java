package com.project.project_reserve_table.model;

import java.sql.Time;

public class ReservationsResponseStartandTimeModel {


	private Time startTime;
	private Time endTime ;
	
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
