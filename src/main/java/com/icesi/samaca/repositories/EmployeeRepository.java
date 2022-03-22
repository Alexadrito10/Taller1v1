package com.icesi.samaca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icesi.samaca.model.hr.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
