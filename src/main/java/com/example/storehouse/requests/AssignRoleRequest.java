package com.example.storehouse.requests;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AssignRoleRequest {

	@NotNull
	private Long userId;
	
	@NotNull
	private Long roleId;
	
}
