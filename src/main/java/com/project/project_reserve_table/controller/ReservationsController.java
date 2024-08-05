package com.project.project_reserve_table.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

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

import com.project.project_reserve_table.model.ReservationsReqDateTableModel;
import com.project.project_reserve_table.model.ReservationsRequestModel;
import com.project.project_reserve_table.model.ReservationsResponseModel;
import com.project.project_reserve_table.model.ResponseModel;
import com.project.project_reserve_table.service.GenExcelService;
import com.project.project_reserve_table.service.GenPdfService;
import com.project.project_reserve_table.service.ReservationsService;
import com.project.project_reserve_table.utils.Constants;

@RestController
@RequestMapping("/reservation")
public class ReservationsController {

	@Autowired
	ReservationsService reservationsService;
	
	@Autowired
    private GenPdfService genPdfService;
	
	 @Autowired
	private GenExcelService genExcelService;
	
	@GetMapping("/getById")
	public ResponseModel getById(@RequestParam(name ="reservationsId") Integer reservationsId ) {
		
		ResponseModel response = new ResponseModel();
		try {
			response.setData(reservationsService.getById(reservationsId));
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
			response.setData(reservationsService.getAll());
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
	        File file = genPdfService.genPDFreservations(reservationsService.getAll(), Constants.TYPE_RESERVATIONS);
	        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

	        HttpHeaders headers = new HttpHeaders(); 
	        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reservation-data.pdf");
	        
	        return ResponseEntity.ok()
	                .headers(headers)
	                .contentLength(file.length())
	                .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                .body(resource);
	    }
	 @GetMapping("/download/excel")
	    public ResponseEntity<?> downloadReservationsExcel() throws IOException {
	        List<ReservationsResponseModel> reservationsData = reservationsService.getAll();
	        File file = genExcelService.genExcelForReservations(reservationsData);
	        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

	        HttpHeaders headers = new HttpHeaders();
	        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reservations-data.xlsx");

	        return ResponseEntity.ok()
	                .headers(headers)
	                .contentLength(file.length())
	                .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                .body(resource);
	    }
	
	
	
	@PostMapping("/getAllTime")
	public ResponseModel getAllTime(@RequestBody ReservationsReqDateTableModel reqDateTableModel) {
		ResponseModel response = new ResponseModel();
		
		try {
			// service
			response.setData(reservationsService.getAllTime(reqDateTableModel));
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	@PostMapping("/save")
	public ResponseModel save(@RequestBody ReservationsRequestModel reservationsRequest) {
		ResponseModel response = new ResponseModel();
		
		try {
			// service
			response.setData(reservationsService.save(reservationsRequest));
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	@PutMapping("/update/{reservationId}")
	public ResponseModel update(@RequestBody ReservationsRequestModel reservationsRequest, @PathVariable Integer reservationId) {
		ResponseModel response = new ResponseModel();
		
		try {
			// service
			response.setData(reservationsService.update(reservationsRequest, reservationId));
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	@DeleteMapping("/delete")
	public ResponseModel delete(@RequestParam(name = "reservationsId") Integer reservationsId) {
		
		ResponseModel response = new ResponseModel();
		
		try {
			response.setData(reservationsService.delete(reservationsId));
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		
		return response;
	}
}
