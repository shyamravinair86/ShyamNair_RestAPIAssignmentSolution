package com.greatlearning.employeemanagementapp.implementation;

import java.util.List;
import java.util.Optional;

import com.greatlearning.employeemanagementapp.entity.User;
import com.greatlearning.employeemanagementapp.repository.UserRepository;
import com.greatlearning.employeemanagementapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(repository.getUserByUsername(username));
    }

    @Override
    public Optional<User> getUserById(long id) {
        return repository.findById(id);
    }

    @Override
    public void saveUser(User user) {
        repository.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        repository.deleteById(id);
    }

}
