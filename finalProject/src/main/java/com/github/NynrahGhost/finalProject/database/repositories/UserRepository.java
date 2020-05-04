package com.github.NynrahGhost.finalProject.database.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.NynrahGhost.finalProject.database.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	List<UserEntity> findAll();
	
	List<UserEntity> findAllByUsername(String title);
	
	@Query("SELECT user FROM UserEntity user "
	        + "LEFT JOIN FETCH user.permissions "
	        + "WHERE user.username = :username")
	    Optional<UserEntity> findByUsername(@Param("username") String username);
	
	List<UserEntity> findByEmail(String title);
}