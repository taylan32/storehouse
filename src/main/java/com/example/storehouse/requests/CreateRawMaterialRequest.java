package com.example.storehouse.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateRawMaterialRequest {

	@NotBlank(message = "Material name cannot be empty")
	@Size(min = 2, message = "Material name must be at least 2 characters")
	private String name;
	

	@NotBlank(message = "Material amount cannot be empty")
	@Size(min = 2, message = "Invalid amount")
	private String amount;
	
}
