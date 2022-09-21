package com.petshop.web.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	@Query(value = "SELECT * FROM user_tbl AS u WHERE u.user_nickname = :#{#user_nickname}", nativeQuery = true)
	UserEntity selectUser(@Param(value = "user_nickname") String user_nickname);
	
}