package com.greatlearning.employeemanagementapp.dao;

public class RoleDao {

    String name;
    
    public RoleDao() {
    	
    }
    
    public RoleDao(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RoleDto [name=" + name + "]";
	}

}
