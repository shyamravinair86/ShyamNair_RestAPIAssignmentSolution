package com.greatlearning.employeemanagementapp.service;

import java.util.List;
import java.util.Optional;

import com.greatlearning.employeemanagementapp.model.Employee;

import org.springframework.data.domain.Sort.Direction;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    List<Employee> getEmployeesByFirstName(String firstName);

    List<Employee> getEmployeesSorted(Direction sortDirection);

    Optional<Employee> getEmployeeById(long id);

    Optional<Employee> getEmployeeByEmail(String email);

    void saveEmployee(Employee employee);

    void deleteEmployeeById(long id);

}
