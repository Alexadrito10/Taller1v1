package com.icesi.samaca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icesi.samaca.model.person.UserApp;
import com.icesi.samaca.services.UserServiceImp;
import com.icesi.samaca.validation.CredentInfoValidation;

@Controller
public class UserControllerImp implements UserController {
	
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
//

}
