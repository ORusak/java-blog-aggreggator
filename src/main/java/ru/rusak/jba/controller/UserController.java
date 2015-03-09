package ru.rusak.jba.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.rusak.jba.entity.Blog;
import ru.rusak.jba.entity.Role;
import ru.rusak.jba.entity.User;
import ru.rusak.jba.repository.RoleRepository;
import ru.rusak.jba.service.BlogService;
import ru.rusak.jba.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
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
	
	@RequestMapping("/users")
	public String user(Model model){
		model.addAttribute("users", userService.findAll());
		return "users";
	}
	
	@RequestMapping("/users/{id}")
	public String detail(Model model, @PathVariable int id) {
		model.addAttribute("user", userService.findOneWithBlogs(id));
		
		return "user-detail";
	}
	
	@RequestMapping("/register")
	public String showRegister(){
		return "user-register";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String doRegister(@ModelAttribute("user") User user){
		user.setEnabled(true);
		
		List<Role> roles	=	new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		
		user.setRoles(roles);
		
		userService.save(user);
		return "redirect:/register.html?success=true";
	}
	
	@RequestMapping("/account")
	public String account(Model model, Principal principal){
		String name = principal.getName();
		model.addAttribute("user", userService.findOneWithBlogs(name));
		return "account";
	}
	
	@RequestMapping(value="/account",method=RequestMethod.POST)
	public String doAddBlog(@ModelAttribute("blog") Blog blog, Principal principal){
		String name = principal.getName();
		blogService.save(blog, name);
		
		return "redirect:/account.html";
	}
}
