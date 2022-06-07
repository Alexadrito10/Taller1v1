package com.icesi.samaca.backend.services;

import java.util.List;

import com.icesi.samaca.backend.model.person.Person;
import com.icesi.samaca.backend.model.person.UserApp;

public interface PersonService {
	public void save(Person user);
	public void update(Person user);
	public List<Person> findAllPersons();
	public Person findPersonById(Integer bussinessentityid);
	public void deleteAllPersons();
	
}

