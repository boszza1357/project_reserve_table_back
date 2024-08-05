package com.project.project_reserve_table.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name ="tables_image")
public class TableImgEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name ="table_id")
	private Integer tableId;
	
	@Column(name ="table_img_path")
	private String tableImgPath;
	
	@Column(name ="table_img_name")
	private String tableImgName;
	
	@Column(name ="status")
	private String status;
	
	@Lob
	@Column(name ="tables_img_data")
	private byte[] tablesImgData;
	
	@Column(name ="create_by")
	private String createBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name ="create_date")
	private Date createDate;
	
	@Column(name ="update_by")
	private String updateBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name ="update_date")
	private Date updateDate;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public byte[] getTablesImgData() {
		return tablesImgData;
	}

	public void setTablesImgData(byte[] tablesImgData) {
		this.tablesImgData = tablesImgData;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
	
	
}
