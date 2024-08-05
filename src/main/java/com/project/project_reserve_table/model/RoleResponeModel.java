package com.project.project_reserve_table.model;

import java.util.Date;

public class RoleResponeModel {

	private Integer id;
    private String roleName;
    private String roleDescription;
    private String status;
    private String CreateBy;
    private Date CreateDate;
    private String UpdateBy;
    private Date UpdateDate;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateBy() {
		return CreateBy;
	}
	public void setCreateBy(String createBy) {
		CreateBy = createBy;
	}
	public Date getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}
	public String getUpdateBy() {
		return UpdateBy;
	}
	public void setUpdateBy(String updateBy) {
		UpdateBy = updateBy;
	}
	public Date getUpdateDate() {
		return UpdateDate;
	}
	public void setUpdateDate(Date updateDate) {
		UpdateDate = updateDate;
	}
    
    
    
    
    
	
    
}
