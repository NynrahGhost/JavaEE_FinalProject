package com.github.NynrahGhost.finalProject.database.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.NynrahGhost.finalProject.database.entities.RecipeEntity;
import com.github.NynrahGhost.finalProject.database.entities.UserEntity;
import com.github.NynrahGhost.finalProject.database.repositories.RecipeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeService {

    //private final EntityManager entityManager;
	private final RecipeRepository userRepository;

    @Transactional
    public RecipeEntity createRecipe(UserEntity author, String name, String shortDesc, String longDesc) {
        RecipeEntity recipe = new RecipeEntity();
        recipe.setAuthor(author);
        recipe.setName(name);
        recipe.setShortDesc(shortDesc);
        recipe.setLongDesc(longDesc);
        recipe.setDate(new Date(new java.util.Date().getTime()));

        return userRepository.saveAndFlush(recipe);
    }
    
    public List<RecipeEntity> getRecipes() {
    	return userRepository.findAll();
    }
    
    public List<RecipeEntity> getRecipe(int id) {
    	List<RecipeEntity> res = new ArrayList<RecipeEntity>();
    	res.add(userRepository.findById(id).get());
    	return res;
    }

}