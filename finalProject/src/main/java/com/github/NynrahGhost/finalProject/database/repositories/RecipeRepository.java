package com.github.NynrahGhost.finalProject.database.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.NynrahGhost.finalProject.database.entities.RecipeEntity;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer> {
	List<RecipeEntity> findAll();
	
	List<RecipeEntity> findAllByName(String title);
}