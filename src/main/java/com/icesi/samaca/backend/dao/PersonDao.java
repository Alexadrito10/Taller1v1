package com.icesi.samaca.backend.dao;

import java.util.List;

import com.icesi.samaca.backend.model.person.Person;

public interface PersonDao {
	
	public void savePerson(Person person);
	public void updatePerson(Person person);
	public void deletePerson(Integer bussinessentityid);
	public List<Person> findAllPersons();
	public Person findPersonById(Integer id);
	public void deleteAllPersons();

}
