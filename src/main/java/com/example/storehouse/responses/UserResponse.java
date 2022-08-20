package com.example.storehouse.responses;

import java.util.Collection;

import com.example.storehouse.models.Role;
import com.example.storehouse.models.User;

import lombok.Data;

@Data
public class UserResponse {

	private Long id;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private Collection<Role> roles;
	
	private Long departmentId;
	
	public UserResponse(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.roles = user.getRoles();
		this.departmentId = user.getDepartment().getId();
	}
	
}
