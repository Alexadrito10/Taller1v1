package com.icesi.samaca.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.samaca.backend.dao.EmployeeDaoImp;
import com.icesi.samaca.backend.model.hr.Employee;

@Service
public class EmployeeServiceImp implements EmployeeService {
	
	@Autowired
	private EmployeeDaoImp employeeDAO;

	@Override
	public void save(Employee emp) {
		
		this.employeeDAO.saveEmployee(emp);
		
		
	}

	@Override
	public void update(Employee emp) {
		// TODO Auto-generated method stub
		this.employeeDAO.updateEmployee(emp);
	}

	@Override
	public List<Employee> findAllEmployees() {
		return this.employeeDAO.findAllEmployees();
	}

	@Override
	public Employee findEmployeeById(Integer bussinessentityid) {
		return this.employeeDAO.findEmployeeById(bussinessentityid);
	}

	@Override
	public void deleteAllEmployees() {
		// TODO Auto-generated method stub
		 this.employeeDAO.deleteAllEmployee();
		
	}
	public Employee deleteEmployee(Integer bussinessentityid) {
		
		Employee result = employeeDAO.findEmployeeById(bussinessentityid);
		this.employeeDAO.deleteEmployee(employeeDAO.findEmployeeById(bussinessentityid));
		
		return result;
		
	}

}
