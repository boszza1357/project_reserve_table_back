package com.project.project_reserve_table.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.project_reserve_table.entity.UserDetailEntity;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetailEntity, Integer>{
	
	@Query("select t from UserDetailEntity t where t.email = ?1")
	public UserDetailEntity findByEmail(String email);

	@Query("select t from UserDetailEntity t where t.userId = ?1")
	public UserDetailEntity findByUserId(Integer userId);
	
	@Modifying(clearAutomatically = true)
	@Query("delete from UserDetailEntity t where t.userId = ?1")
	public void deleteByUserId(Integer userId);
	
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserDetailEntity u WHERE u.email = ?1")
	boolean existsByEmail(String email);
}
