package com.example.storehouse.responses;

import com.example.storehouse.models.Department;

import lombok.Data;

@Data
public class DepartmentResponse {

	private Long id;
	private String departmentName;
	
	public DepartmentResponse(Department department) {
		this.id = department.getId();
		this.departmentName = department.getName();
	}
	
}
