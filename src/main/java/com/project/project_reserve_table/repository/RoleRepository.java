package com.project.project_reserve_table.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.project_reserve_table.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer>{

}
