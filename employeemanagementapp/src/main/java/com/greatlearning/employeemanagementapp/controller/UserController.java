package com.greatlearning.employeemanagementapp.controller;

import com.greatlearning.employeemanagementapp.dao.UserDao;
import com.greatlearning.employeemanagementapp.entity.Role;
import com.greatlearning.employeemanagementapp.entity.User;
import com.greatlearning.employeemanagementapp.service.RoleService;
import com.greatlearning.employeemanagementapp.service.UserService;

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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping
    public List<User> get() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public User get(@PathVariable long id) {
        return userService.getUserById(id).orElseThrow(() -> new RuntimeException("User Not Found."));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public String post(@RequestBody UserDao userDao) throws Exception {
        Optional<User> optional = userService.getUserByUsername(userDao.getUsername());
        if (optional.isPresent()) {
            throw new Exception("User Already Exist.");
        }
        User user = new User(userDao.getUsername(), userDao.getPassword(), userDao.getRoles());
        for (Role role : userDao.getRoles()) {
            Optional<Role> rOptional = roleService.getRoleById(role.getId());
            if (rOptional.isEmpty()) {
                throw new Exception("Role Not Found.");
            }
        }
        userService.saveUser(user);
        return " Saved user id -  " + user.getId();
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable long id) throws Exception {
        Optional<User> optional = userService.getUserById(id);
        if (optional.isEmpty()) {
            throw new Exception("User Not Found.");
        }
        userService.deleteUserById(id);
        return " Deleted user id - " + id;
    }

}
