package com.github.NynrahGhost.finalProject.database.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.NynrahGhost.finalProject.database.entities.CommentEntity;
import com.github.NynrahGhost.finalProject.database.entities.RecipeEntity;
import com.github.NynrahGhost.finalProject.database.entities.UserEntity;
import com.github.NynrahGhost.finalProject.database.repositories.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    //private final EntityManager entityManager;
	private final CommentRepository commentRepository;

    @Transactional
    public CommentEntity createComment(UserEntity author, RecipeEntity recipe, String text) {
        CommentEntity comment = new CommentEntity();
        comment.setAuthor(author);

        return commentRepository.saveAndFlush(comment);
    }
    
    public List<CommentEntity> getComments() {
    	return commentRepository.findAll();
    }
    
    public List<CommentEntity> getComment(int id) {
    	List<CommentEntity> res = new ArrayList<CommentEntity>();
    	res.add(commentRepository.findById(id).get());
    	return res;
    }

}