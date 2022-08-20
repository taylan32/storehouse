package com.example.storehouse.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateSupplierRequest {

	@NotBlank(message = "Email is not allowed to be empty")
	@Email
	private String email;
	
	@NotBlank(message = "Company name is not allowed to be empty")
	@Size(min = 4, message = "Company name is too long. Must be at least 4 characters")
	private String companyName;
	

	@NotBlank(message = "Phone number is not allowed to be empty")
	@Size(min = 10, message = "Phone number is too long. Must be at least  10 characters")
	private String phoneNumber;
	

	@NotBlank(message = "Address is not allowed to be empty")
	@Size(min = 20, message = "Address is too long. Must be at least  20 characters")
	private String address;
	
}
