package com.project.project_reserve_table.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.project_reserve_table.model.ResponseModel;
import com.project.project_reserve_table.model.TablesRequsetModel;
import com.project.project_reserve_table.model.TablesResponseModel;
import com.project.project_reserve_table.service.GenExcelService;
import com.project.project_reserve_table.service.GenPdfService;
import com.project.project_reserve_table.service.TableService;
import com.project.project_reserve_table.utils.Constants;

@RestController
@RequestMapping("/table")
public class TableController {

	@Autowired
	TableService tableService;
	
	  @Autowired
	    private GenPdfService genPdfService;
	  
	  @Autowired
		private GenExcelService genExcelService;
	
	@GetMapping("/getById")
	public ResponseModel getById(@RequestParam(name = "tableId") Integer tableId) {
		
		ResponseModel response = new ResponseModel();
		
		try {
			// service
			response.setData(tableService.getById(tableId));
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	@GetMapping("/getTableTypeById")
	public ResponseModel getTableTypeById(@RequestParam (name ="tableTypeId")Integer tableTypeId) {
		ResponseModel response = new ResponseModel();
		
		try {
			// service
			response.setData(tableService.getTableTypeById(tableTypeId));
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
			response.setData(tableService.getTableTypeAll());
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	@GetMapping("/getTableImgByTableId")
	public ResponseModel getTableImgByTableId(@RequestParam(name = "tableId") Integer tableId) {
		ResponseModel response = new ResponseModel();
		
		try {
			// service
			response.setData(tableService.getProductImgByProductId(tableId));
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	@GetMapping("/getAll")
	public ResponseModel getAll() {
		ResponseModel response = new ResponseModel();
		
		try {
			// service
			response.setData(tableService.getAll());
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	@GetMapping("/download/pdf")
    public ResponseEntity<?> downloadPdf() throws IOException {
        File file = genPdfService.genPDFTable(tableService.getAll(), Constants.TYPE_TABLE);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders(); headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Table-data.pdf");
        
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
	
	@GetMapping("/download/excel")
    public ResponseEntity<?> downloadTablesExcel() throws IOException {
        List<TablesResponseModel> tablesData = tableService.getAll();
        File file = genExcelService.genExcelForTables(tablesData);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=tables-data.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
	
	@PostMapping("/save")
	public ResponseModel save(@RequestBody TablesRequsetModel tableRequest) {
		ResponseModel response = new ResponseModel();
		
		try {
			// service
			response.setData(tableService.save(tableRequest));
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	@PutMapping("/update/{tableId}")
	public ResponseModel update(@RequestBody TablesRequsetModel tableRequest, @PathVariable Integer tableId) {
		ResponseModel response = new ResponseModel();
		
		try {
			// service
			response.setData(tableService.update(tableRequest, tableId));
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	@DeleteMapping("/delete")
	public ResponseModel delete(@RequestParam(name = "tableId") Integer tableId) {
		
		ResponseModel response = new ResponseModel();
		
		try {
			response.setData(tableService.delete(tableId));
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		
		return response;
	}
	
	@GetMapping("/getImageByte")
	public ResponseEntity<byte[]> getImageByte(@RequestParam(name = "fileName") String fileName) throws IOException, DataFormatException {

		return ResponseEntity.ok(tableService.getImageByte(fileName));
	}
	
	@PostMapping(value =("/saveImage/{tableId}") , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseModel saveImage(@RequestParam("file") MultipartFile file, @PathVariable Integer tableId) {
		
		ResponseModel response = new ResponseModel();
		
		try {
			response.setData(tableService.saveImage(file, tableId));
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		
		return response;
	}
	
	@DeleteMapping(value =("/deleteImgByFileName"))
	public ResponseModel deleteImgByFileName(@RequestParam(name = "fileName") String fileName) {
		
		ResponseModel response = new ResponseModel();
		
		try {
			tableService.deleteImgByFileName(fileName);
			response.setData("SUCCESS");
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		
		return response;
	}
}
