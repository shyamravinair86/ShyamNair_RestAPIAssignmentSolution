package com.greatlearning.employeemanagementapp.service;

import java.util.List;
import java.util.Optional;

import com.greatlearning.employeemanagementapp.entity.Role;

public interface RoleService {

    List<Role> getAllRoles();

    Optional<Role> getRoleByName(String name);

    Optional<Role> getRoleById(long id);

    void saveRole(Role role);

    void deleteRoleById(long id);

}
