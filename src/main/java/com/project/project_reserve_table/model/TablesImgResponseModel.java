package com.project.project_reserve_table.model;

public class TablesImgResponseModel {

	private Integer tableImgId;
	private Integer tableId;
	private String tableImgPath;
	private String tableImgName;
	private byte[] tableImgData;
	private String status;
	
	public Integer getTableImgId() {
		return tableImgId;
	}
	public void setTableImgId(Integer tableImgId) {
		this.tableImgId = tableImgId;
	}
	public Integer getTableId() {
		return tableId;
	}
	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}
	public String getTableImgPath() {
		return tableImgPath;
	}
	public void setTableImgPath(String tableImgPath) {
		this.tableImgPath = tableImgPath;
	}
	public String getTableImgName() {
		return tableImgName;
	}
	public void setTableImgName(String tableImgName) {
		this.tableImgName = tableImgName;
	}
	public byte[] getTableImgData() {
		return tableImgData;
	}
	public void setTableImgData(byte[] tableImgData) {
		this.tableImgData = tableImgData;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
