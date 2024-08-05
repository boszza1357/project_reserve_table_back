package com.project.project_reserve_table.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.project_reserve_table.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	@Query("select t from UserEntity t where t.userName = ?1")
    public UserEntity findByUserName(String userName);
	
	@Query("select t from UserEntity t where t.userName = ?1 and t.password =?2 ")
	public UserEntity findByUserNameAndPassword(String userName, String password);
	
	@Query("select t from UserEntity t where t.roleId = 2 ")
	public List<UserEntity> findAllUser();
	
	@Modifying(clearAutomatically = true)
    @Query("delete from UserEntity t where t.id = ?1")
    void deleteByUserId(Integer userId);
	
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserEntity u WHERE u.userName = ?1")
	boolean existsByUsername(String userName);
}
