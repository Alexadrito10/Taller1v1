package com.icesi.samaca.backend.dao;

import java.util.List;

import com.icesi.samaca.backend.model.person.Person;

public interface PersonDao {
	
	public Person savePerson(Person person);
	public Person updatePerson(Person person);
	public void deletePerson(Integer bussinessentityid);
	public List<Person> findAllPersons();
	public Person findPersonById(Integer id);
	public void deleteAllPersons();

}
