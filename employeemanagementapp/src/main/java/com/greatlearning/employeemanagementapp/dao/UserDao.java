package com.greatlearning.employeemanagementapp.dao;

import java.util.ArrayList;

import com.greatlearning.employeemanagementapp.entity.Role;

public class UserDao {

    String username;
    String password;
    ArrayList<Role> roles;
	
    public UserDao(String username, String password, ArrayList<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

    public String getUsername() {
		return username;
	}

    public void setUsername(String username) {
		this.username = username;
	}

    public String getPassword() {
		return password;
	}

    public void setPassword(String password) {
		this.password = password;
	}

    public ArrayList<Role> getRoles() {
		return roles;
	}

    public void setRoles(ArrayList<Role> roles) {
		this.roles = roles;
	}

    @Override
	public String toString() {
		return "UserDto [username=" + username + ", password=" + password + ", roles=" + roles + "]";
	}
    
}
