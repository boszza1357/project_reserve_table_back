package com.project.project_reserve_table.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.project_reserve_table.entity.UserDetailEntity;
import com.project.project_reserve_table.entity.UserEntity;
import com.project.project_reserve_table.model.LoginResponseModel;
import com.project.project_reserve_table.repository.UserDetailRepository;
import com.project.project_reserve_table.repository.UserRepository;

@Service
public class LoginService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserDetailRepository userDetailRespository;

	public LoginResponseModel authen(String userName ,String password) {
		
		// ต้องให้ response = null ก่อน ถึงจะไปประกาศใน ตัว if 
	//	LoginResponseModel response = new LoginResponseModel();
		
		LoginResponseModel response = null;
		
		UserEntity userEntity = userRepository.findByUserNameAndPassword(userName, password);
		
		if(null != userEntity) {
			
			response = new LoginResponseModel();
			
			response.setUserId(userEntity.getId());
			response.setUserName(userEntity.getUserName());
			response.setPassword(userEntity.getPassword());
			response.setRoleId(userEntity.getRoleId());
			response.setStatus(userEntity.getStatus());
			
			UserDetailEntity userDatailEntity = userDetailRespository.findByUserId(userEntity.getId());
			if(null != userDatailEntity) {
				response.setFristName(userDatailEntity.getFristName());
				response.setLastName(userDatailEntity.getLastName());
				response.setEmail(userDatailEntity.getEmail());
				response.setPhone(userDatailEntity.getPhone());
				response.setAge(userDatailEntity.getAge());
				response.setGender(userDatailEntity.getGender());
			}
		}
		return response;
	}
}
