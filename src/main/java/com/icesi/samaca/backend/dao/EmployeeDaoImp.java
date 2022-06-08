package com.icesi.samaca.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.icesi.samaca.backend.model.hr.Employee;
import com.icesi.samaca.backend.model.person.Countryregion;
import com.icesi.samaca.backend.model.person.Person;

@Repository
@Scope("singleton")
public class EmployeeDaoImp implements EmployeeDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void saveEmployee(Employee emp) {
		
		entityManager.persist(emp);
	}

	@Override
	@Transactional
	public void updateEmployee(Employee emp) {
		
		entityManager.merge(emp);
		
	}

	@Override
	public void deleteEmployee(Employee emp) {
		
		entityManager.remove(emp);
		
	}

	@Override
	public List<Employee> findAllEmployees() {
		String jpql = "Select emp from Employee emp";
		return 	entityManager.createQuery(jpql,Employee.class).getResultList();
	}
	
	@Override
	public Employee findEmployeeById(Integer businessentityid) {
		
		return entityManager.find(Employee.class, businessentityid);
		
		
	}

	@Override
	public void deleteAllEmployee() {
		Query query= entityManager.createQuery("DELETE FROM Employee");
		query.executeUpdate();
		
		
		
	}

		
}
