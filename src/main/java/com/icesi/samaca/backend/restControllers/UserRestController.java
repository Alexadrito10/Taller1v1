package com.icesi.samaca.backend.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icesi.samaca.backend.model.person.Stateprovince;
import com.icesi.samaca.backend.model.person.UserApp;
import com.icesi.samaca.backend.services.UserServiceImp;

@RestController
public class UserRestController {
	
	@Autowired
	private UserServiceImp userService;
	
	@RequestMapping("/userapps/")
	public Iterable<UserApp> getUserApps(){
		return userService.findAll();
		
		
	}
	@RequestMapping("/userapps/{id}")
	public UserApp getUserApp(@PathVariable("id") long id){
		return userService.findById(id).get();
		
		
	}
	
	@PostMapping("/userapps/add")
	public UserApp addUserApp(@RequestBody UserApp uA){
		return userService.save(uA);
		
		
	}
	@DeleteMapping("/userapps/add")
	public UserApp addUserApp(@PathVariable("id") long id){
		return userService.delete(id);
		
	}
	@PutMapping("/userapps/{id}")
	public UserApp updateUserApp(@RequestBody UserApp uA){
		return userService.update(uA);
		
	}
	
	
	
	
	

}
