package com.github.NynrahGhost.finalProject;

import org.hibernate.Hibernate;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.github.NynrahGhost.finalProject.database.services.UserService;
import com.github.NynrahGhost.finalProject.dto.UserDto;
import com.github.NynrahGhost.finalProject.exceptions.UserAlreadyExistException;

@SpringBootApplication
public class FinalProjectApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(FinalProjectApplication.class, args);
		
		UserDto user = new UserDto();
		user.setEmail("xyzghost@gmail.com");
		user.setPassword("7110eda4d09e062aa5e4a390b0a572ac0d2c0220");
		user.setMatchingPassword("7110eda4d09e062aa5e4a390b0a572ac0d2c0220");
		user.setUsername("Admin");
		
		try {
			applicationContext.getBean(UserService.class).registerNewUserAccount(user); //createUser("Admin", "7110eda4d09e062aa5e4a390b0a572ac0d2c0220", "Me", 0);
		} catch (BeansException | UserAlreadyExistException e) {
			e.printStackTrace();
		}
		
		System.out.println(applicationContext.getBean(UserService.class).getUsers());
	}

}
