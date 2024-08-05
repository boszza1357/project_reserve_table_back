package com.project.project_reserve_table.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.project_reserve_table.entity.TableImgEntity;

@Repository
public interface TableImgRepository extends JpaRepository<TableImgEntity, Integer>{

	@Query("select t from TableImgEntity t where t.tableId = ?1")
	public List<TableImgEntity> findByTableId(Integer tableId);
	
	@Modifying(clearAutomatically = true)
	@Query("delete from TableImgEntity t where t.tableId = ?1")
	void deleteByTableId(Integer tableId);
	
	@Query("select t from TableImgEntity t where t.tableImgName = ?1")
	public TableImgEntity findByTableName(String tableImgName);
}
