package com.project.project_reserve_table.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.project.project_reserve_table.entity.UserDetailEntity;
import com.project.project_reserve_table.entity.UserEntity;
import com.project.project_reserve_table.model.RegisterRequestModel;
import com.project.project_reserve_table.model.RegisterResponseModel;
import com.project.project_reserve_table.repository.UserDetailRepository;
import com.project.project_reserve_table.repository.UserRepository;
import com.project.project_reserve_table.utils.Constants;

import jakarta.transaction.Transactional;

@Service
public class RegisterService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDetailRepository userDetailRepository;
	
	@Autowired
    private SendMailService mailService;

	public RegisterResponseModel getById(Integer userid) {
		
		RegisterResponseModel response = null;
		
		
		Optional<UserEntity> userEntity = userRepository.findById(userid);
		
		if(userEntity.isPresent()) {
			
			response = new RegisterResponseModel();
			UserEntity user = userEntity.get();
			UserDetailEntity userDetail = userDetailRepository.findByUserId(userid);
			
			// user
			response.setUserId(user.getId());
			response.setUserName(user.getUserName());
			response.setPassword(user.getPassword());
			response.setRoleId(user.getRoleId());
			response.setStatus(user.getStatus());

			// userDatail
			if(null != userDetail) {
				
				response.setFristName(userDetail.getFristName());
				response.setLastName(userDetail.getLastName());
				response.setEmail(userDetail.getEmail());
				response.setPhone(userDetail.getPhone());
				response.setAge(userDetail.getAge());
				response.setGender(userDetail.getGender());
			}
		}
		
		return response;
		
	}
	
	@Transactional
	public Integer save(RegisterRequestModel request) {
		
		Integer response = null;
		
		if(null != request) {
			
			UserEntity entity = userRepository.findByUserName(request.getUserName());
			
		
            if(null != entity ) {
                return null ;
            }
            
            UserDetailEntity userDetailEntity = userDetailRepository.findByEmail(request.getEmail());
            
            if (null != userDetailEntity) {
      
            	return null ;
				
			}
			
		UserEntity userEntity = new UserEntity();
		
		userEntity.setUserName(request.getUserName());
		userEntity.setPassword(request.getPassword());
		userEntity.setRoleId(null != request.getRoleId() ? request.getRoleId() : 2);
		userEntity.setStatus(null != request.getStatus() ? request.getStatus() : "1");
		userEntity.setCreateBy("Dew");
		userEntity.setCreateDate(new Date());
		userEntity.setUpdateBy("Dew");
		userEntity.setUpdateDate(new Date());
		
		
		userEntity = userRepository.save(userEntity);
		
		response = userEntity.getId();
		
		if(null != userEntity) {
			UserDetailEntity userDatailEntity = new UserDetailEntity();
			
			userDatailEntity.setUserId(userEntity.getId());
			userDatailEntity.setFristName(request.getFristName());
			userDatailEntity.setLastName(request.getLastName());
			userDatailEntity.setEmail(request.getEmail());
			userDatailEntity.setPhone(request.getPhone());
			userDatailEntity.setAge(request.getAge());
			userDatailEntity.setGender(request.getGender());
			userDatailEntity.setCreateBy("Dew");
			userDatailEntity.setCreateDate(new Date());
			userDatailEntity.setUpdateBy("Dew");
			userDatailEntity.setUpdateDate(new Date());
	
			userDetailRepository.save(userDatailEntity);
			
			}

		}
		
		
		RegisterResponseModel data = getById(response);
        if(null != data) {
            String emailTo = data.getEmail();
            String subject = "Test Email";

            mailService.sendMail(data,emailTo, subject, Constants.TYPE_SENDMAIL_REGISTER);
        }
		
		return response;
		
		
	}
	
	@Transactional
	public Integer update(RegisterRequestModel request, Integer userId) {
		
		Integer response = null;
		
		if(null != request) {
			
			Optional<UserEntity> userEntity = userRepository.findById(userId);
			
			if(userEntity.isPresent()){
				UserEntity user = userEntity.get();
				
				 response = user.getId();			
				
				 // user 
				 
				user.setPassword(null != request.getPassword() ? request.getPassword() : user.getPassword());
				user.setStatus(null != request.getStatus() ? request.getStatus() : user.getStatus());
				user.setUpdateBy("update By Dew");
				user.setUpdateDate(new Date());
				
				userRepository.save(user);
				
				
				//userDatali
				UserDetailEntity userDetail = userDetailRepository.findByUserId(userId);
				
				if(null != userDetail) {
					
					userDetail.setFristName(null != request.getFristName() ? request.getFristName() : userDetail.getFristName());
					userDetail.setLastName(null != request.getLastName() ? request.getLastName() : userDetail.getLastName());
					userDetail.setEmail(null != request.getEmail() ? request.getEmail() : userDetail.getEmail());
					userDetail.setPhone(null != request.getPhone() ? request.getPhone() : userDetail.getPhone());
					userDetail.setAge(null != request.getAge() ? request.getAge() : userDetail.getAge());
					userDetail.setGender(null != request.getGender() ? request.getGender() : userDetail.getGender());
					userDetail.setUpdateBy("update By Dew");
	                userDetail.setUpdateDate(new Date());
	                
	                userDetailRepository.save(userDetail);
					
				}
				
				
			}
		}
		
		return response;
	}
	
	@Transactional
	public Integer delete(Integer userId) {
		
		Integer response = null;
		
		if(null != userId) {
			userRepository.deleteByUserId(userId);
			userDetailRepository.deleteByUserId(userId);
			
			response = userId;
		}
		 
		return response;
	}
}
