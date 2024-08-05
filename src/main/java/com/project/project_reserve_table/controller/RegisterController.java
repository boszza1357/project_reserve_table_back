package com.project.project_reserve_table.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.project_reserve_table.model.RegisterRequestModel;
import com.project.project_reserve_table.model.ResponseModel;
import com.project.project_reserve_table.service.RegisterService;

@RestController
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private RegisterService registerService;

	@GetMapping("/getById")
	public ResponseModel getById(@RequestParam(name = "userId")Integer userId) {
		
		ResponseModel response = new ResponseModel();
		
		try {
			// service
			response.setData(registerService.getById(userId));
			response.setStatus("SUCCESS");
			response.setStatusCode(null);
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setStatusCode(null);
			response.setData(e.getMessage());
		}
		return response;
	}
	
	@PostMapping("/save")
	public ResponseModel save(@RequestBody RegisterRequestModel request) {
			
		ResponseModel response = new ResponseModel();
		
		try {
			response.setData(registerService.save(request));
			response.setStatus("SUCCESS");
			response.setStatusCode(null);
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		
		return response ;
	}
	
	@PutMapping("/update/{userId}")
	public ResponseModel update(@RequestBody RegisterRequestModel request, @PathVariable Integer userId) {
		
		ResponseModel response = new ResponseModel();
		
		try {
			response.setData(registerService.update(request, userId));
			response.setStatus("SUCCESS");
			response.setStatusCode("200");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		
		return response ;
	}
	
	@DeleteMapping("/delete")
	public ResponseModel delete(@RequestParam (name = "userId") Integer userId) {
		
		ResponseModel response = new ResponseModel();
		
		try {
			response.setData(registerService.delete(userId));
			response.setStatus("SUCCESS");
			response.setStatusCode("200");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		
		return response;
	}

}
