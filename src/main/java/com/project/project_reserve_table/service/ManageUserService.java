package com.project.project_reserve_table.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.project_reserve_table.entity.UserDetailEntity;
import com.project.project_reserve_table.entity.UserEntity;
import com.project.project_reserve_table.model.ManageUserResponseModel;
import com.project.project_reserve_table.repository.UserDetailRepository;
import com.project.project_reserve_table.repository.UserRepository;

@Service
public class ManageUserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserDetailRepository userDetailRepository;

	public List<ManageUserResponseModel> getAllUser(){
		
		List<ManageUserResponseModel> response = null;
		
		List<UserEntity> userEntityList = userRepository.findAllUser();
		
		if(null != userEntityList) {
			
			response = new ArrayList<>();
			
			for(UserEntity user :userEntityList) {
				
				
				ManageUserResponseModel responserObject = new ManageUserResponseModel();
				
				responserObject.setUserId(user.getId());
				responserObject.setUserName(user.getUserName());
				responserObject.setPassword(user.getPassword());
				responserObject.setRoleId(user.getRoleId());
				responserObject.setStatus(user.getStatus());
				responserObject.setCreateDate(user.getCreateDate());
				
				UserDetailEntity userDatail = userDetailRepository.findByUserId(user.getId());
				
				if(null != userDatail) {
					responserObject.setFristName(userDatail.getFristName());
					responserObject.setLastName(userDatail.getLastName());
					responserObject.setPhone(userDatail.getPhone());
					responserObject.setEmail(userDatail.getEmail());
					responserObject.setAge(userDatail.getAge());
					responserObject.setGender(userDatail.getGender()); 
					
				}
				
				response.add(responserObject);
			}
		}
		
		return response;
	}
	
	
	public List<ManageUserResponseModel> getById(){
		
		List<ManageUserResponseModel> response = null;
		
		List<UserEntity> userEntityList = userRepository.findAllUser();
		
		if(null != userEntityList) {
			
			response = new ArrayList<>();
			
			for(UserEntity user :userEntityList) {
				
				
				ManageUserResponseModel responserObject = new ManageUserResponseModel();
				
				responserObject.setUserId(user.getId());
				responserObject.setUserName(user.getUserName());
				responserObject.setPassword(user.getPassword());
				responserObject.setRoleId(user.getRoleId());
				responserObject.setStatus(user.getStatus());
				responserObject.setCreateDate(user.getCreateDate());
				
				UserDetailEntity userDatail = userDetailRepository.findByUserId(user.getId());
				
				if(null != userDatail) {
					responserObject.setFristName(userDatail.getFristName());
					responserObject.setLastName(userDatail.getLastName());
					responserObject.setPhone(userDatail.getPhone());
					responserObject.setEmail(userDatail.getEmail());
					responserObject.setAge(userDatail.getAge());
					responserObject.setGender(userDatail.getGender()); 
					
				}
				
				response.add(responserObject);
			}
		}
		
		return response;
	}
}
