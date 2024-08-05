package com.project.project_reserve_table.model;

import java.math.BigDecimal;

public class TablesRequsetModel {

	
	private Integer tablesTypeId ;
	private String tableName ;
	private String tableNumber;
	private String tableDesc;
	private String capacity;
	private String status;
	private BigDecimal price;
	private String nots;
	
	public Integer getTablesTypeId() {
		return tablesTypeId;
	}
	public void setTablesTypeId(Integer tablesTypeId) {
		this.tablesTypeId = tablesTypeId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableNumber() {
		return tableNumber;
	}
	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}
	public String getTableDesc() {
		return tableDesc;
	}
	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getNots() {
		return nots;
	}
	public void setNots(String nots) {
		this.nots = nots;
	}
	
	
	
}
