package com.project.project_reserve_table.repository;



import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.project_reserve_table.entity.ReservationsEntity;

@Repository
public interface ReservationsRepository extends JpaRepository<ReservationsEntity, Integer>{
	
	
	@Query("select t from ReservationsEntity t where t.startTime = ?1 and t.endTime = ?2 and t.bookingDate = ?3 ")
   public ReservationsEntity findByStartTimeAndEndTimeAndBookingDate(Time startTime, Time endTime, Date bookingDate);

	@Query("select t from ReservationsEntity t where t.tableId = ?1 and t.bookingDate = ?2")
	public List<ReservationsEntity>  findByTableIdAndBookingDate(Integer tableId, Date bookingDate);

	
	@Modifying(clearAutomatically = true)
	@Query("delete from ReservationsEntity t where t.id = ?1")
	void deleteByreservationsId(Integer reservationsId);


}
