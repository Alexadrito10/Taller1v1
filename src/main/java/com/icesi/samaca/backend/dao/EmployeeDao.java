package com.icesi.samaca.backend.dao;

import java.util.List;

import com.icesi.samaca.backend.model.hr.Employee;
import com.icesi.samaca.backend.model.person.Person;

public interface EmployeeDao {
	
	public void saveEmployee(Employee emp);
	public void updateEmployee(Employee emp);
	public void deleteEmployee(Employee person);
	public List<Employee> findAllEmployees();
	public Employee findEmployeeById(Integer businessentityid);
	public void deleteAllEmployee();

	
	

}
