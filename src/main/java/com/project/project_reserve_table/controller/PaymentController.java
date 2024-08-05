package com.project.project_reserve_table.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.project_reserve_table.model.PaymentRequestModel;

import com.project.project_reserve_table.model.ResponseModel;
import com.project.project_reserve_table.service.GenExcelService;
import com.project.project_reserve_table.service.GenPdfService;
import com.project.project_reserve_table.service.PaymentService;
import com.project.project_reserve_table.utils.Constants;


@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	 @Autowired
		private GenExcelService genExcelService;
	
	  @Autowired
	    private GenPdfService genPdfService;
	
	@GetMapping("/getById")
	public ResponseModel getById(@RequestParam(name = "paymentId")Integer paymentId) {
		
		ResponseModel response = new ResponseModel();
		
		try {
			// service
			response.setData(paymentService.getById(paymentId));
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
	
    @GetMapping("/getAll")
    public ResponseModel getAll() {
        ResponseModel response = new ResponseModel();
        try {
            response.setData(paymentService.getAll());
            response.setStatus("SUCCESS");
            response.setStatusCode(null);
        } catch (Exception e) {
            response.setStatus("ERROR");
            response.setStatusCode(null);
            response.setData(e.getMessage());
        }
        return response;
    }
    
    @GetMapping("/download/pdf")
    public ResponseEntity<?> downloadPdf() throws IOException {
        File file = genPdfService.genPDFpayment(paymentService.getAll(), Constants.TYPE_PAYMENT);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders(); 
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=payment-data.pdf");
        
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
    
    @GetMapping("/download/excel")
    public ResponseEntity<?> downloadPaymentExcel() throws IOException {
        File file = genExcelService.genExcelpayment(paymentService.getAll(), Constants.TYPE_PAYMENT);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=payment-data.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
    
    @GetMapping("/getUserByBooking")
	public ResponseModel getUserByBooking(@RequestParam(name = "userId")Integer userId) {
		
		ResponseModel response = new ResponseModel();
		
		try {
			// service
			response.setData(paymentService.getByUsershowBooking(userId));
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
	public ResponseModel save(@RequestBody PaymentRequestModel paymentRequestModel) {
			
		ResponseModel response = new ResponseModel();
		
		try {
			response.setData(paymentService.save(paymentRequestModel));
			response.setStatus("SUCCESS");
			response.setStatusCode(null);
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		
		return response ;
	}
	
	@DeleteMapping("/delete")
	public ResponseModel delete(@RequestParam (name = "paymentId") Integer paymentId ,@RequestParam (name = "reservationsId") Integer reservationsId) {
		
		ResponseModel response = new ResponseModel();
		
		try {
			response.setData(paymentService.delete(paymentId, reservationsId));
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
