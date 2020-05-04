package com.github.NynrahGhost.finalProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.NynrahGhost.finalProject.database.entities.UserEntity;
import com.github.NynrahGhost.finalProject.database.services.MyUserDetailsService;
import com.github.NynrahGhost.finalProject.database.services.RecipeService;
import com.github.NynrahGhost.finalProject.database.services.UserService;

@Controller
public class IndexController {

	@Autowired
	private ApplicationContext applicationContext;
	
    @RequestMapping({ "/", "" })
    public String index() {
        return "index";
    }
    
    @RequestMapping(value = "/recipes/", method = RequestMethod.GET)
    @ResponseBody
    public String recipes(Model model, @PathVariable("id") long id) {
        return "redirect:/index";
    }
    
    @RequestMapping(value = "/recipes/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String recipe(Model model, @PathVariable("id") long id) {
        return applicationContext.getBean(RecipeService.class).getRecipe((int)id).get(0).toString();
    }
    
    
    /*
    @RequestMapping(value = "/books-list", method = RequestMethod.GET)
    public String booksList(Model model) {
    	
        model.addAttribute("books", applicationContext.getBean(BookService.class).getBooks());
        return "index";
    }
    
    @RequestMapping(value = "/book-form", method = RequestMethod.GET)
    public String bookForm(Model model) {
        return "bookForm";
    }
	*/
    
    @RequestMapping(value = "/add-recipe", method = RequestMethod.POST)
    public String addNewBook(
    		@ModelAttribute(name = "name") String name,
    		@ModelAttribute(name = "shortDesc") String shortDesc,
    		@ModelAttribute(name = "longDesc") String longDesc
    ) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String currentPrincipalName = authentication.getName();
    	//UserDetails userDetails = applicationContext.getBean(MyUserDetailsService.class).loadUserByUsername(currentPrincipalName);
    	UserEntity user = applicationContext.getBean(UserService.class).getUser(currentPrincipalName).get(0);
    	
    	applicationContext.getBean(RecipeService.class).createRecipe(user, name, shortDesc, longDesc);
        return "redirect:/recipes";
    }
}
