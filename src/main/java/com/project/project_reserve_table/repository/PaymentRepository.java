package com.project.project_reserve_table.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.project_reserve_table.entity.PaymentEntity;	

@Repository
public interface PaymentRepository  extends JpaRepository<PaymentEntity, Integer>{
	
	@Modifying(clearAutomatically = true)
    @Query("delete from PaymentEntity t where t.id = ?1")
    void deleteByPaymentId(Integer paymentId);
	
	@Query("select t from PaymentEntity t where t.userId = ?1")
    public List<PaymentEntity> findByUserId(Integer userId);

}
