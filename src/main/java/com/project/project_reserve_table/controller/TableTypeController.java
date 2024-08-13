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

import com.project.project_reserve_table.model.ResponseModel;
import com.project.project_reserve_table.model.TablesRequsetModel;
import com.project.project_reserve_table.model.TablesTypeRequsetModel;
import com.project.project_reserve_table.service.TableTypeService;


@RestController
@RequestMapping("/tableType")
public class TableTypeController {
	
	@Autowired
	private TableTypeService tableTypeService;
	
	
	
	@GetMapping("/getTableTypeById")
	public ResponseModel getTableTypeById(@RequestParam (name ="tableTypeId")Integer tableTypeId) {
		ResponseModel response = new ResponseModel();
		
		try {
			// service
			response.setData(tableTypeService.getTableTypeById(tableTypeId));
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	@GetMapping("/getTableTypeAll")
	public ResponseModel getTableTypeAll() {
		ResponseModel response = new ResponseModel();
		
		try {
			// service
			response.setData(tableTypeService.getTableTypeAll());
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	@PostMapping("/saveTypeTable")
	public ResponseModel saveTypeTable(@RequestBody TablesTypeRequsetModel tablesTypeRequsetModel) {
		
		ResponseModel response = new ResponseModel();
		
		try {
			response.setData(tableTypeService.saveTypeTable(tablesTypeRequsetModel));
			response.setStatus("SUccESS");
		} catch (Exception e) {
			// TODO: handle exception
			
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		return response;
	}


	@PutMapping("/update/{tableTypeId}")
	public ResponseModel update(@RequestBody TablesTypeRequsetModel tableRequest, @PathVariable Integer tableTypeId) {
		ResponseModel response = new ResponseModel();
		
		try {
			// service
			response.setData(tableTypeService.update(tableRequest, tableTypeId));
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	@DeleteMapping("/delete")
	public ResponseModel delete(@RequestParam(name = "tableTypeId") Integer tableTypeId) {
		
		ResponseModel response = new ResponseModel();
		
		try {
			response.setData(tableTypeService.delete(tableTypeId));
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		
		return response;
	}
	
	
	

}
