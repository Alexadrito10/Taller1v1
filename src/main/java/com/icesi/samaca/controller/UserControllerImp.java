package com.icesi.samaca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.icesi.samaca.services.UserServiceImp;

@Controller
public class UserControllerImp{
	
	UserServiceImp userService;
	
	@Autowired
	public UserControllerImp (UserServiceImp userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users/")
	public String indexUser(Model model) {
		model.addAttribute("users",userService.findAll());
		return "users/index";
		
	}
	@GetMapping("/login")
	public String loginPrincipal() {
		return "/login";
	}
	@GetMapping("/logout")
	public String logout(Model model) {
		return "/login";
	}


}
