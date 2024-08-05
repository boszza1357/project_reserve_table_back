package com.project.project_reserve_table.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.project_reserve_table.entity.TableEntity;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Integer>{
	
	@Modifying(clearAutomatically = true)
	@Query("delete from TableEntity t where t.id = ?1")
	void deleteByTableId(Integer tableId);

}
