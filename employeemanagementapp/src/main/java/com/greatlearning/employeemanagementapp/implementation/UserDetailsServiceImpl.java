package com.greatlearning.employeemanagementapp.implementation;

import com.greatlearning.employeemanagementapp.entity.User;
import com.greatlearning.employeemanagementapp.repository.UserRepository;
import com.greatlearning.employeemanagementapp.security.MyUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found.");
        }
        return new MyUserDetails(user);
    }

}