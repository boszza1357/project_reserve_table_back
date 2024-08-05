package com.project.project_reserve_table.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.project_reserve_table.entity.TableTypeEntity;

@Repository
public interface TableTypeRepository extends JpaRepository<TableTypeEntity, Integer>{

	
}
