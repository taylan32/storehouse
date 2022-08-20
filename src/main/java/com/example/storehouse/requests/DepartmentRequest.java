package com.example.storehouse.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DepartmentRequest {

	@NotBlank(message = "Department name is not allowed to be empty")
	@Size(min = 2, message = "Department name must contains at least 2 characters")
	private String departmentName;
	
}
