package com.project.project_reserve_table.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.project_reserve_table.entity.TableTypeEntity;

@Repository
public interface TableTypeRepository extends JpaRepository<TableTypeEntity, Integer>{

	
	@Modifying(clearAutomatically = true)
	@Query("delete from TableTypeEntity t where t.id = ?1")
	void deleteBytableTypeId(Integer tableTypeId);
}
