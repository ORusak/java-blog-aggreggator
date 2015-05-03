package ru.rusak.jba.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.rusak.jba.entity.Blog;
import ru.rusak.jba.entity.Role;
import ru.rusak.jba.entity.User;
import ru.rusak.jba.repository.RoleRepository;
import ru.rusak.jba.service.UserService;

@Controller

@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@ModelAttribute("user")
	public User constuctUser(){
		return new User();
	}
	
	@ModelAttribute("blog")
	public Blog constuctBlog(){
		return new Blog();
	}
	
	@RequestMapping
	public String showRegister(){
		return "user-register";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult result){
		if(result.hasErrors()){
			return "user-register";
		}
		
		user.setEnabled(true);
		
		List<Role> roles	=	new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		
		user.setRoles(roles);
		
		userService.save(user);
		return "redirect:/register.html?success=true";
	}	
}
