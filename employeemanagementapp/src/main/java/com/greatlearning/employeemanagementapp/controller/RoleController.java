package com.greatlearning.employeemanagementapp.controller;

import java.util.List;
import java.util.Optional;

import com.greatlearning.employeemanagementapp.dto.RoleDto;
import com.greatlearning.employeemanagementapp.model.Role;
import com.greatlearning.employeemanagementapp.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    RoleService service;

    @GetMapping
    public List<Role> get() {
        return service.getAllRoles();
    }

    @GetMapping("{id}")
    public Role get(@PathVariable long id) {
        return service.getRoleById(id).orElseThrow(() -> new RuntimeException("Role Not Found."));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public String post(@RequestBody RoleDto roleDto) throws Exception {
        Optional<Role> optional = service.getRoleByName(roleDto.getName());
        if (optional.isPresent()) {
            throw new Exception("Role Already Exist.");
        }

        Role role = new Role(roleDto.getName());
        service.saveRole(role);
        return "Saved role id - " + role.getId();
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable long id) throws Exception {
        Optional<Role> optional = service.getRoleById(id);
        if (optional.isEmpty()) {
            throw new Exception("Role Not Found.");
        }
        service.deleteRoleById(id);
        return "Deleted role id " + id;
    }
}
