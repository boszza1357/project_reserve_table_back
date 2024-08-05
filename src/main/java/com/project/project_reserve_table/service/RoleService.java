package com.project.project_reserve_table.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.project_reserve_table.entity.RoleEntity;
import com.project.project_reserve_table.model.RoleRequestModel;
import com.project.project_reserve_table.model.RoleResponeModel;
import com.project.project_reserve_table.repository.RoleRepository;




@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	public RoleResponeModel getById(Integer id) {

		//ดึงข้อมูล
		
				Optional<RoleEntity> roleEntity =  roleRepository.findById(id);	
				RoleResponeModel response = null;
				
				if(roleEntity.isPresent()) {
					RoleEntity entity = roleEntity.get();
					// มีข้อมูลที่ได้มาจาก Table role ของ DB
					response = new RoleResponeModel();
					response.setId(entity.getId());
					response.setRoleName(entity.getRoleName());
					response.setRoleDescription(entity.getRoleDescription());
					response.setStatus(entity.getStatus());
					response.setCreateBy(entity.getCreateBy());
					response.setCreateDate(entity.getUpdateDate());
					response.setUpdateBy(entity.getUpdateBy());
					response.setUpdateDate(entity.getUpdateDate());
					
				}
				
				return response ;
		
	}
	
	public List<RoleResponeModel> getAll(){
		
		List<RoleEntity> roleEntityList = roleRepository.findAll();
		
		List<RoleResponeModel> responseList = null;
		
		if(null != roleEntityList) {
			
			responseList= new ArrayList<>();
			
			for(RoleEntity roleEntity : roleEntityList) {
				RoleResponeModel roleObj = new RoleResponeModel();
				roleObj.setId(roleEntity.getId());
				roleObj.setRoleName(roleEntity.getRoleName());
				roleObj.setRoleDescription(roleEntity.getRoleDescription());
				roleObj.setStatus(roleEntity.getStatus());
				roleObj.setCreateBy(roleEntity.getCreateBy());
				roleObj.setCreateDate(roleEntity.getCreateDate());
				roleObj.setUpdateBy(roleEntity.getUpdateBy());
				roleObj.setUpdateDate(roleEntity.getUpdateDate());
				responseList.add(roleObj);
			}
		}
		
		return responseList;
	}
	
	public void save(RoleRequestModel requset) {
		
		if(null != requset) {
			
			RoleEntity entity = new RoleEntity();
			
			entity.setRoleName(requset.getRoleName());
			entity.setRoleDescription(requset.getRoleDescription());
			entity.setStatus(requset.getStatus());
			entity.setCreateBy("Dew");
			entity.setCreateDate(new Date());
			entity.setUpdateBy("Dew");
			entity.setUpdateDate(new Date());
			
			roleRepository.save(entity);
			
		}
	}
	
	public void update(RoleRequestModel request) {

        if(null != request) {
            if(null != request.getId()) {

                Optional<RoleEntity> roleEntity =  roleRepository.findById(request.getId());

                if(roleEntity.isPresent()) {
                    RoleEntity entity = roleEntity.get();
                    entity.setRoleName(request.getRoleName());
                    entity.setRoleDescription(request.getRoleDescription());
                    entity.setStatus(request.getStatus());
                    roleRepository.save(entity);
                }
            }
        }
    }
	
	   public void delete(Integer id) {

	        roleRepository.deleteById(id);

	    }
}
