package com.icesi.samaca.backend.restControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icesi.samaca.backend.model.hr.Employee;
import com.icesi.samaca.backend.model.person.Person;
import com.icesi.samaca.backend.services.PersonServiceImp;

@RestController
@RequestMapping("/api")
public class PersonRestController  {
	
	@Autowired
	private PersonServiceImp personService;
	
	@RequestMapping("/persons/")
	public Iterable<Person> getPerson(){
		return personService.findAllPersons();
	}

	@RequestMapping("/persons/{id}")
	public Person getPerson(@PathVariable("id") Integer bussinessentityid){
		return personService.findPersonById(bussinessentityid);
	}
	
	@PostMapping("/persons/add")
	public Person addPerson(@RequestBody Person person){
		personService.save(person);
		return person;
	}
	@DeleteMapping("/persons/{id}")
	public Person deleteEmployee(@PathVariable("id") Integer bussinessentityid){
		Person result = personService.findPersonById(bussinessentityid);
		personService.deletePerson(bussinessentityid);
		return result;
		
	
	
	}
}
