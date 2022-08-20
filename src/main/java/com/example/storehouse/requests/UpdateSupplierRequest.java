package com.example.storehouse.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateSupplierRequest {

	@NotBlank(message = "Phone number is not allowed to be empty")
	@Size(min = 10, message = "Phone number is too long. Must be at least  10 characters")
	private String phoneNumber;
	

	@NotBlank(message = "Address is not allowed to be empty")
	@Size(min = 20, message = "Address is too long. Must be at least  20 characters")
	private String address;
	
}
