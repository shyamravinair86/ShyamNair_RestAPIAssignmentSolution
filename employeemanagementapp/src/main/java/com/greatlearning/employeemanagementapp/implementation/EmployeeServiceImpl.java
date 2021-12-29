package com.greatlearning.employeemanagementapp.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.greatlearning.employeemanagementapp.model.Employee;
import com.greatlearning.employeemanagementapp.repository.EmployeeRepository;
import com.greatlearning.employeemanagementapp.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository repository;

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public List<Employee> getEmployeesByFirstName(String firstName) {
        return repository.findByFirstNameContainsIgnoreCase(firstName);
    }

    @Override
    public List<Employee> getEmployeesSorted(Direction sortDirection) {
        switch (sortDirection) {
            case ASC:
                return repository.findAll(Sort.by(Sort.Order.asc("firstName")));
            case DESC:
                return repository.findAll(Sort.by(Sort.Order.desc("firstName")));
            default:
                return new ArrayList<Employee>();
        }
    }

    @Override
    public Optional<Employee> getEmployeeById(long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Employee> getEmployeeByEmail(String email) {
        Employee employee = new Employee();
        employee.setEmail(email);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.ignoreCase())
                .withIgnorePaths("id", "name");
        Example<Employee> example = Example.of(employee, matcher);
        return repository.findOne(example);
    }

    @Override
    public void saveEmployee(Employee employee) {
        repository.save(employee);
    }

    @Override
    public void deleteEmployeeById(long id) {
        repository.deleteById(id);
    }

}
