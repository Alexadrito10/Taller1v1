package com.icesi.samaca.backend.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icesi.samaca.backend.dao.EmployeeDaoImp;
import com.icesi.samaca.backend.model.hr.Employee;
import com.icesi.samaca.backend.model.sales.Salesterritory;
import com.icesi.samaca.backend.services.EmployeeServiceImp;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	@Autowired
	private EmployeeServiceImp empService;
	
	@RequestMapping("/employees/")
	public Iterable<Employee> getEmployees(){
		return empService.findAllEmployees();
	}
	
	@RequestMapping("/employees/{id}")
	public Employee getEmployee(@PathVariable("id") Integer bussinesentity){
		return empService.findEmployeeById(bussinesentity);
	}
	
	@PostMapping("/employees/")
	public void addEmployee(@RequestBody Employee emp){
		empService.save(emp);		
	}
	
	@DeleteMapping("/employees/{id}")
	public Employee deleteEmployee(@PathVariable("id") Integer bussinessentityid){
		return empService.deleteEmployee(bussinessentityid);
	
		
		
	}
	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@RequestBody Employee emp) {
		empService.update(emp);
		return emp;
	}
	
	
}
