package com.project.project_reserve_table.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.project_reserve_table.entity.PaymentEntity;
import com.project.project_reserve_table.entity.ReservationsEntity;
import com.project.project_reserve_table.entity.UserDetailEntity;
import com.project.project_reserve_table.entity.UserEntity;
import com.project.project_reserve_table.model.PaymentRequestModel;
import com.project.project_reserve_table.model.PaymentResponseModel;
import com.project.project_reserve_table.model.RegisterResponseModel;
import com.project.project_reserve_table.model.ReservationsResponseModel;
import com.project.project_reserve_table.repository.PaymentRepository;
import com.project.project_reserve_table.repository.ReservationsRepository;
import com.project.project_reserve_table.repository.UserDetailRepository;
import com.project.project_reserve_table.repository.UserRepository;
import com.project.project_reserve_table.utils.Constants;

import jakarta.transaction.Transactional;

@Service
public class PaymentService {

	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	ReservationsRepository reservationsRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserDetailRepository userDetailRepository;
	

	@Autowired
    private SendMailService mailService;
	
	public PaymentResponseModel getById(Integer paymentId) {
		
		PaymentResponseModel response = null;
		
		Optional<PaymentEntity> paymentEntity  = paymentRepository.findById(paymentId);
		
		if(paymentEntity.isPresent()) {
			
			response = new PaymentResponseModel();
			
			PaymentEntity payment = paymentEntity.get();
			
			response.setReservationId(payment.getReservationId());
			response.setUserId(payment.getUserId());
			response.setStatusPayment(payment.getStatusPayment());
			response.setPricePayment(payment.getPricePayment());
			response.setCreateDate(payment.getCreateDate());
			
					
		}
		
		return response;
		
	}
public ReservationsResponseModel getByIdreservations(Integer reservationsId ) {
		
		ReservationsResponseModel  response = null;
		
	Optional<ReservationsEntity> reservationsEntity = reservationsRepository.findById(reservationsId);
	
		if(reservationsEntity.isPresent()) {
			
			response = new  ReservationsResponseModel();
			
			ReservationsEntity reservations = reservationsEntity.get();
			
			response.setReservationsId(reservations.getId());
			response.setUserId(reservations.getUserId());
			response.setTableId(reservations.getTableId());
			response.setBookingDate(reservations.getBookingDate());
			response.setStatus(reservations.getStatus());
			response.setStartTime(reservations.getStartTime());
			response.setEndTime(reservations.getEndTime());
			response.setCreateDate(reservations.getCreateDate());
			response.setUpdateDate(reservations.getUpdateDate());
			
		}
		
		return response ;
	}

public RegisterResponseModel getByIduser(Integer userid) {
	
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
	
	
	
	public List<PaymentResponseModel> getAll() {
		
		List<PaymentResponseModel> response = null;
		
		List<PaymentEntity> paymentEntity = paymentRepository.findAll();
		
		if(null != paymentEntity) {
			
			response = new  ArrayList<>();
			
			for(PaymentEntity payment : paymentEntity) {
				
				PaymentResponseModel object = new PaymentResponseModel();
				
				object.setPaymentId(payment.getId());
				object.setReservationId(payment.getReservationId());
				object.setUserId(payment.getUserId());
				object.setStatusPayment(payment.getStatusPayment());
				object.setPricePayment(payment.getPricePayment());
				object.setCreateDate(payment.getCreateDate());
				
				response.add(object);
				
			}
		}
		
		return  response ;
	}
	
	public List<PaymentResponseModel> getByUsershowBooking(Integer userId) {
	    List<PaymentResponseModel> response = new ArrayList<>();

	    List<PaymentEntity> paymentEntities = paymentRepository.findByUserId(userId);

	    for (PaymentEntity payment : paymentEntities) {
	        PaymentResponseModel paymentResponse = new PaymentResponseModel();

	        paymentResponse.setReservationId(payment.getReservationId());
	        paymentResponse.setUserId(payment.getUserId());
	        paymentResponse.setStatusPayment(payment.getStatusPayment());
	        paymentResponse.setPricePayment(payment.getPricePayment());
	        paymentResponse.setCreateDate(payment.getCreateDate());

	        response.add(paymentResponse);
	    }

	    return response;
	}

	
	@Transactional
	public Integer save(PaymentRequestModel paymentRequestModel) throws IOException {
		
		Integer response = null;
		
		if(null != paymentRequestModel) {
			
			PaymentEntity paymentEntity = new PaymentEntity();
			
			paymentEntity.setReservationId(paymentRequestModel.getReservationId());
			paymentEntity.setUserId(paymentRequestModel.getUserId());
			paymentEntity.setStatusPayment("1");
			paymentEntity.setPricePayment(paymentRequestModel.getPricePayment());
			paymentEntity.setCreateDate(new Timestamp(System.currentTimeMillis()));
			
			paymentEntity =  paymentRepository.save(paymentEntity);
			 
			response = paymentEntity.getId();
			
		}
		
		 ReservationsResponseModel reservationData = getByIdreservations(paymentRequestModel.getReservationId());
	        if (reservationData != null) {
	            String emailTo = getByIduser(paymentRequestModel.getUserId()).getEmail();
	            String subject = "จองโต๊ะสำเร็จ - ขอบคุณที่ใช้บริการ";

	            mailService.sendMailpayment(reservationData, emailTo, subject, Constants.TYPE_SENDMAIL_PAYMENT);
	        }
		
		return response;
	}
	
	@Transactional
	public Integer delete(Integer paymentId, Integer reservationsId ) {
		
		Integer response = null;
		
		if(null != paymentId ) {
			paymentRepository.deleteByPaymentId(paymentId);
			reservationsRepository.deleteByreservationsId(reservationsId);
			
			response = paymentId;
		}
		 	
		return response;
	}
}
