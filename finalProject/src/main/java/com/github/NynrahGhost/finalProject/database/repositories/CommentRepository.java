package com.github.NynrahGhost.finalProject.database.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.NynrahGhost.finalProject.database.entities.CommentEntity;
import com.github.NynrahGhost.finalProject.database.entities.UserEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
	List<CommentEntity> findAll();
	
	List<CommentEntity> findAllByAuthor(UserEntity author);
}