package com.icesi.samaca.backend.services;

import java.util.List;

import com.icesi.samaca.backend.model.hr.Employee;
import com.icesi.samaca.backend.model.person.Person;

public interface EmployeeService {
	
	public void save(Employee emp);
	public void update(Employee emp);
	public List<Employee> findAllEmployees();
	public Employee findEmployeeById(Integer bussinessentityid);
	public void deleteAllEmployees();
	

}
