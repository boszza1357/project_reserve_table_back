package com.project.project_reserve_table.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.project_reserve_table.model.ResponseModel;
import com.project.project_reserve_table.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	LoginService loginService;

	@GetMapping("/authen")
	public ResponseModel authen(@RequestParam(name ="userName")String username,@RequestParam(name ="password")String password) {
		
		ResponseModel response = new ResponseModel();
		
		try {
			response.setData(loginService.authen(username, password));
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
