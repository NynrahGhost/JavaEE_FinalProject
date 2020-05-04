package com.github.NynrahGhost.finalProject.database.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.NynrahGhost.finalProject.database.entities.Permission;
import com.github.NynrahGhost.finalProject.database.entities.PermissionEntity;
import com.github.NynrahGhost.finalProject.database.entities.UserEntity;
import com.github.NynrahGhost.finalProject.database.repositories.UserRepository;
import com.github.NynrahGhost.finalProject.dto.UserDto;
import com.github.NynrahGhost.finalProject.exceptions.UserAlreadyExistException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    //private final EntityManager entityManager;
	private final UserRepository userRepository;

    @Transactional
    public UserEntity createUser(String username, String password, String realname, int access) throws UserAlreadyExistException {
    	
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        user.setRealname(realname);

        return userRepository.saveAndFlush(user);
    }
    
    public List<UserEntity> getUsers() {
    	return userRepository.findAll();
    }
    
    public List<UserEntity> getUser(int id) {
    	List<UserEntity> res = new ArrayList<UserEntity>();
    	res.add(userRepository.findById(id).get());
    	return res;
    }
    
    public List<UserEntity> getUser(String username) {
    	List<UserEntity> res = new ArrayList<UserEntity>();
    	res.add(userRepository.findByUsername(username).get());
    	return res;
    }

}