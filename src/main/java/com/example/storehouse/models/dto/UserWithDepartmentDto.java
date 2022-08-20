package com.example.storehouse.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithDepartmentDto {

	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	private Long departmentId;
	private String departmentName;
	
}
