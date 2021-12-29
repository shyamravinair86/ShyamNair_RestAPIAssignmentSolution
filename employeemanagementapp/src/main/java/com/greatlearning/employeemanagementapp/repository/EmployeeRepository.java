package com.greatlearning.employeemanagementapp.repository;

import java.util.List;

import com.greatlearning.employeemanagementapp.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByFirstNameContainsIgnoreCase(String firstName);

}
