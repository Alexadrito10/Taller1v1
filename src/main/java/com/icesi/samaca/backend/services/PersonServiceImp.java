package com.icesi.samaca.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.samaca.backend.dao.PersonDaoImp;
import com.icesi.samaca.backend.model.person.Person;

@Service
public class PersonServiceImp implements PersonService {
	
	@Autowired
	private PersonDaoImp personDao;

	@Override
	public void save(Person user) {
		this.personDao.savePerson(user);
	}

	@Override
	public void update(Person user) {
		this.personDao.updatePerson(user);
	}

	@Override
	public List<Person> findAllPersons() {
		// TODO Auto-generated method stub
		return this.personDao.findAllPersons();
	}

	@Override
	public Person findPersonById(Integer bussinessentityid) {
		// TODO Auto-generated method stub
		return this.personDao.findPersonById(bussinessentityid);
	}

	@Override
	public void deleteAllPersons() {
		
		this.personDao.deleteAllPersons();;
		
	}
	
	public void deletePerson(Integer bussinessentityid) {
		
		this.personDao.deletePerson(bussinessentityid);
		
	}
	
	

}
