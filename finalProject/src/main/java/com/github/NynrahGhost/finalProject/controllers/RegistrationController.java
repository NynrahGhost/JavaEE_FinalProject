package com.github.NynrahGhost.finalProject.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.github.NynrahGhost.finalProject.database.entities.UserEntity;
import com.github.NynrahGhost.finalProject.database.services.RecipeService;
import com.github.NynrahGhost.finalProject.database.services.UserService;
import com.github.NynrahGhost.finalProject.dto.UserDto;
import com.github.NynrahGhost.finalProject.exceptions.UserAlreadyExistException;

@Controller
public class RegistrationController {

	@Autowired
	private ApplicationContext applicationContext;
	
	@GetMapping("/user/registration")
	public String showRegistrationForm(WebRequest request, Model model) {
	    UserDto userDto = new UserDto();
	    model.addAttribute("user", userDto);
	    return "registration";
	}
	
	@PostMapping("/user/registration")
	public ModelAndView registerUserAccount(
	  @ModelAttribute("user") @Valid UserDto userDto, 
	  HttpServletRequest request, Errors errors) { 
	    
		ModelAndView mav = new ModelAndView();
		
	    try {
	        UserEntity registered = applicationContext.getBean(UserService.class).registerNewUserAccount(userDto);
	    } catch (UserAlreadyExistException uaeEx) {
	        mav.addObject("message", "An account for that username/email already exists.");
	        return mav;
	    }
	 
	    return new ModelAndView("successRegister", "user", userDto);
	}

}
