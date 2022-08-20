package com.example.storehouse.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterRequest {

	@NotBlank(message = "Email is not allowed to be empty")
	@Email()
	private String email;
	
	@NotBlank(message = "Password is not allowed to be empty")
	@Size(min = 4, message = "Password cannot be less than 4 characters")
	private String password;
	
	@NotBlank(message = "Name is not allowed to be empty")
	@Size(min = 2, message = "Name cannot be less than 2 characters")
	private String firstName;
	
	@NotBlank(message = "Last name is not allowed to be empty")
	@Size(min = 2, message = "Last name cannot be less than 2 characters")
	private String lastName;
	
	
	
}
