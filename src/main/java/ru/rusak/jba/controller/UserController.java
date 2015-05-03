package ru.rusak.jba.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.rusak.jba.entity.Blog;
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
	
	@RequestMapping("/account")
	public String account(Model model, Principal principal){
		String name = principal.getName();
		model.addAttribute("user", userService.findOneWithBlogs(name));
		
		return "account";
	}
	
	@RequestMapping(value="/account",method=RequestMethod.POST)
	public String doAddBlog(@Valid @ModelAttribute("blog") Blog blog, BindingResult result, Model model, Principal principal){
		if(result.hasErrors()){
			model.addAttribute("show_add_blog", "true");
			return account(model, principal);
		}
		
		String name = principal.getName();
		blogService.save(blog, name);
		
		return "redirect:/account.html";
	}
	
	@RequestMapping("/blog/remove/{id}")
	public String removeBlog(@PathVariable int id) {
		Blog blog	=	blogService.findOne(id);
		
		blogService.delete(blog);
		return "redirect:/account.html";
	}
}
