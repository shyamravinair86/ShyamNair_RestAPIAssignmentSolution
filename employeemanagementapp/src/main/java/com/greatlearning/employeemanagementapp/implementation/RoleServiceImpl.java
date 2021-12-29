package com.greatlearning.employeemanagementapp.implementation;

import java.util.List;
import java.util.Optional;

import com.greatlearning.employeemanagementapp.model.Role;
import com.greatlearning.employeemanagementapp.repository.RoleRepository;
import com.greatlearning.employeemanagementapp.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository repository;

    @Override
    public List<Role> getAllRoles() {
        return repository.findAll();
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        return Optional.ofNullable(repository.getRoleByName(name));
    }

    @Override
    public Optional<Role> getRoleById(long id) {
        return repository.findById(id);
    }

    @Override
    public void saveRole(Role role) {
        repository.save(role);
    }

    @Override
    public void deleteRoleById(long id) {
        repository.deleteById(id);
    }

}
