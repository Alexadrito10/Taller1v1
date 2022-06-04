package com.icesi.samaca.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icesi.samaca.backend.model.hr.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
