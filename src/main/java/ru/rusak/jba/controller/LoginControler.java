package ru.rusak.jba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginControler {
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
}
