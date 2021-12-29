package com.greatlearning.employeemanagementapp.controller;

import java.util.List;
import java.util.Optional;

import com.greatlearning.employeemanagementapp.dao.EmployeeDao;
import com.greatlearning.employeemanagementapp.entity.Employee;
import com.greatlearning.employeemanagementapp.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @GetMapping("{id}")
    public Employee get(@PathVariable long id) {
        return service.getEmployeeById(id).orElseThrow(() -> new RuntimeException("Employee Not Found."));
    }

    @GetMapping("/search/{firstName}")
    public List<Employee> get(@PathVariable String firstName) {
        return service.getEmployeesByFirstName(firstName);
    }

    @GetMapping
    public List<Employee> get() {
        return service.getAllEmployees();
    }

    @GetMapping("/sort")
    public List<Employee> getSorted(@RequestParam String order) {
    	Direction direction = order.toLowerCase().contains("asc") ? Direction.ASC : Direction.DESC;
    	return service.getEmployeesSorted(direction);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public String post(@RequestBody EmployeeDao employeeDao) throws Exception {
        Optional<Employee> optional = service.getEmployeeByEmail(employeeDao.getEmail());
        if (optional.isPresent()) {
            throw new Exception("Employee Already Exist.");
        }

        Employee employee = new Employee(employeeDao.getFirstName(), employeeDao.getLastName(), employeeDao.getEmail());
        service.saveEmployee(employee);
        return "Saved employee id - " + employee.getId();
    }

    @PutMapping
    public Employee put(@RequestBody EmployeeDao employeeDao) {
        Employee employee = new Employee();
        Optional<Employee> optional = service.getEmployeeById(employeeDao.getId());
        if (optional.isPresent()) {
            employee = optional.get();
            employee.setFirstName(employeeDao.getFirstName());
            employee.setLastName(employeeDao.getLastName());
            employee.setEmail(employeeDao.getEmail());
        } else {
            employee = new Employee(employeeDao.getFirstName(), employeeDao.getLastName(), employeeDao.getEmail());
        }
        service.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable long id) throws Exception {
        Optional<Employee> optional = service.getEmployeeById(id);
        if (optional.isEmpty()) {
            throw new Exception("Employee Not Found.");
        }
        service.deleteEmployeeById(id);
        return "Deleted employee id - " + id;
    }

}
