package com.greatlearning.employeemanagementapp.service;

import java.util.List;
import java.util.Optional;

import com.greatlearning.employeemanagementapp.model.User;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserById(long id);

    void saveUser(User user);

    void deleteUserById(long id);

}
