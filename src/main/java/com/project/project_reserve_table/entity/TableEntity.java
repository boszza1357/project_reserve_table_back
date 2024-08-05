package com.project.project_reserve_table.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="tables")
public class TableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name ="tables_type_id")
	private Integer tablesTypeId;
	
	@Column(name ="table_name")
	private String tableName;
	
	@Column(name ="table_number")
	private String tableNumber;
	
	@Column(name ="table_desc")
	private String tableDesc;
	
	@Column(name ="capacity")
	private String capacity;
	
	@Column(name ="status")
	private String status;
	
	@Column(name ="price")
	private BigDecimal price;
	
	@Column(name ="nots")
	private String nots;
	
	@Column(name ="create_by")
	private String createBy;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name ="update_by")
	private String updateBy;
	
	@Column(name = "update_date")
	private Date updateDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
