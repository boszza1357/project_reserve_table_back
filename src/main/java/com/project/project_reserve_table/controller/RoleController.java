package com.project.project_reserve_table.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.project_reserve_table.model.RoleRequestModel;
import com.project.project_reserve_table.model.RoleResponeModel;
import com.project.project_reserve_table.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roloService;

	@GetMapping("/getById")
	public RoleResponeModel getById(@RequestParam Integer id) {

		return roloService.getById(id);
	}
	
	@GetMapping("/getAll")
	public List<RoleResponeModel> getAll(){
		
		return roloService.getAll();
	}
	
	@PostMapping("/save")
	public String save(@RequestBody RoleRequestModel requsst) {
		
		roloService.save(requsst);
		return "Messenger : Insert Successfully";
	}
	
	@PutMapping("/update")
	public String update(@RequestBody RoleRequestModel requset) {
		
		roloService.update(requset);
		return "Messenger : Update Successfully";
	}
	
	  @DeleteMapping("/delete")
	    public String delete(@RequestParam(name = "id") Integer id) {
		   roloService.delete(id);
		   return "Messenger : Delete Successfully";
	    }
}
