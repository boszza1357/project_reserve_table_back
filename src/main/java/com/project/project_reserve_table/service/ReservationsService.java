package com.project.project_reserve_table.service;

import java.io.IOException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.project.project_reserve_table.entity.ReservationsEntity;
import com.project.project_reserve_table.model.ReservationsReqDateTableModel;
import com.project.project_reserve_table.model.ReservationsRequestModel;
import com.project.project_reserve_table.model.ReservationsResponseModel;
import com.project.project_reserve_table.model.ReservationsResponseStartandTimeModel;
import com.project.project_reserve_table.repository.ReservationsRepository;

import jakarta.transaction.Transactional;

@Service
public class ReservationsService {
	
	@Autowired
	ReservationsRepository reservationsRepository;
	
	

	public ReservationsResponseModel getById(Integer reservationsId ) {
		
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
	
	public List<ReservationsResponseStartandTimeModel> getAllTime(ReservationsReqDateTableModel reqDateTableModel){
		
		List<ReservationsResponseStartandTimeModel> responser = null;
		
		List<ReservationsEntity> reservationsEntities = reservationsRepository.findByTableIdAndBookingDate(reqDateTableModel.getTableId(), reqDateTableModel.getBookingDate());		
		
		if(null != reservationsEntities) {
			
			responser = new ArrayList<>();
			
			for(ReservationsEntity reservation : reservationsEntities) {
				
				ReservationsResponseStartandTimeModel responseStartandTimeModel = new ReservationsResponseStartandTimeModel();
				
				responseStartandTimeModel.setStartTime(reservation.getStartTime());
				responseStartandTimeModel.setEndTime(reservation.getEndTime());
				
				responser.add(responseStartandTimeModel);
			}
		}
		
		return responser ;
	}
	
	
	public List<ReservationsResponseModel> getAll() {
		
		List<ReservationsResponseModel> response = null ;
		
		List<ReservationsEntity> reservationsEntitry = reservationsRepository.findAll();
		
		if(null != reservationsEntitry) {
			
			response = new ArrayList<>();
			
			for(ReservationsEntity reservations : reservationsEntitry) {
				
				ReservationsResponseModel object = new ReservationsResponseModel();
				
				object.setReservationsId(reservations.getId());
				object.setUserId(reservations.getUserId());
				object.setTableId(reservations.getTableId());
				object.setBookingDate(reservations.getBookingDate());
				object.setStatus(reservations.getStatus());
				object.setStartTime(reservations.getStartTime());
				object.setEndTime(reservations.getEndTime());
				object.setCreateDate(reservations.getCreateDate());
				object.setUpdateDate(reservations.getUpdateDate());
				
				response.add(object);
			}
			
		}
		return response ;
	}
	
	@Transactional
	    public Integer save(ReservationsRequestModel reservationsRequest) throws IOException {

	        Integer response = null;

	        if (reservationsRequest != null) {

	            // ตรวจสอบว่ามีการจองที่มีเวลาตรงกันในวันที่เดียวกันหรือไม่
	            ReservationsEntity existingReservation = reservationsRepository.findByStartTimeAndEndTimeAndBookingDate(reservationsRequest.getStartTime(), reservationsRequest.getEndTime(), reservationsRequest.getBookingDate());

	            if (existingReservation != null) {
	                return null; // มีการจองที่มีเวลาตรงกันในวันที่เดียวกัน
	            }

	            // สร้าง Entity ใหม่สำหรับการจอง
	            ReservationsEntity reservationsEntity = new ReservationsEntity();

	            reservationsEntity.setUserId(reservationsRequest.getUserId());
	            reservationsEntity.setTableId(reservationsRequest.getTableId());
	            reservationsEntity.setBookingDate(reservationsRequest.getBookingDate());
	            reservationsEntity.setStatus("1");
	            reservationsEntity.setStartTime(reservationsRequest.getStartTime());
	            reservationsEntity.setEndTime(reservationsRequest.getEndTime());
	            reservationsEntity.setCreateDate(new Timestamp(System.currentTimeMillis()));
	            reservationsEntity.setUpdateDate(new Timestamp(System.currentTimeMillis()));

	            reservationsEntity = reservationsRepository.save(reservationsEntity);
	            response = reservationsEntity.getId();
	        }

	        return response;
	    }
	
//	private Date getTomorrowDate(Date date) {
//	    Calendar calendar = Calendar.getInstance();
//	    calendar.setTime(date);
//	    calendar.add(Calendar.DATE, 1); // เพิ่มวันที่ 1
//	    return calendar.getTime();
//	}
	
	@Transactional
	public Integer update(ReservationsRequestModel reservationsRequest, Integer reservationsId)throws IOException {
		
		Integer responser = null ;
		
		Optional<ReservationsEntity> reservationsEntity =  reservationsRepository.findById(reservationsId);
		
		if(reservationsEntity.isPresent()) {
			
			ReservationsEntity reservations =	reservationsEntity.get();
			
			reservations.setUserId(null != reservationsRequest.getUserId() ? reservationsRequest.getUserId() : reservations.getUserId());
			reservations.setTableId(null != reservationsRequest.getTableId() ? reservationsRequest.getTableId()  :reservations.getTableId());
			reservations.setBookingDate(null != reservations.getBookingDate() ? reservationsRequest.getBookingDate() : reservations.getBookingDate());
			reservations.setStatus("2");
			reservations.setStartTime(null !=  reservationsRequest.getStartTime() ? reservationsRequest.getStartTime()  : reservations.getStartTime());
			reservations.setEndTime(null != reservationsRequest.getEndTime()  ? reservationsRequest.getEndTime() : reservations.getEndTime());
			reservations.setUpdateDate(new Timestamp(System.currentTimeMillis()));
			
			reservations  = reservationsRepository.save(reservations);
			 
			responser = reservations.getId();
	
		}
		
		return responser ;
	}
	
	@Transactional
	public Integer delete(Integer reservationsId) throws IOException {
		
		Integer response = null;
		
		if(null != reservationsId) {
			reservationsRepository.deleteByreservationsId(reservationsId);
			response = reservationsId;
		}
		return response;
	}
}
