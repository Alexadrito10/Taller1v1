package com.icesi.samaca.backend.dao;

import java.util.List;

//import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.icesi.samaca.backend.model.person.Person;

@Repository
@Scope("singleton")
public class PersonDaoImp implements PersonDao{
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public void savePerson(Person person) {
		entityManager.persist(person);
		
	}
	@Transactional 
	@Override
	public void updatePerson(Person person) {
		entityManager.merge(person);
		
	}

	@Override
	@Transactional
	public void deletePerson(Integer bussinessentityid) {
		entityManager.remove(this.findPersonById(bussinessentityid));
		
	}

	@Override
	public List<Person> findAllPersons() {
		String jpql = "Select p from Person p";
		return 	entityManager.createQuery(jpql,Person.class).getResultList();
	}

	@Override
	public Person findPersonById(Integer bussinessentityid) {
		return 	entityManager.find(Person.class, bussinessentityid);
	}

	@Override
	public void deleteAllPersons() {
		Query query= entityManager.createQuery("DELETE FROM Person");
		query.executeUpdate();	
		
	}
	
	

}
