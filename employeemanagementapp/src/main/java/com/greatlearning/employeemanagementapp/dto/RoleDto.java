package com.greatlearning.employeemanagementapp.dto;

public class RoleDto {

    String name;
    
    public RoleDto() {
    	
    }
    
    public RoleDto(String name) {
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
