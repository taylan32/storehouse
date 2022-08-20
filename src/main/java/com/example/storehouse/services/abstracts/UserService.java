package com.example.storehouse.services.abstracts;

import java.util.List;

import com.example.storehouse.models.dto.UserWithDepartmentDto;
import com.example.storehouse.requests.AssignRoleRequest;
import com.example.storehouse.responses.UserResponse;
import com.example.storehouse.utils.results.DataResult;
import com.example.storehouse.utils.results.Result;

public interface UserService {

	Result addRoleToUser(AssignRoleRequest request);
	
	DataResult<UserResponse> getById(Long id);
	
	DataResult<List<UserResponse>> getAll();
	
	boolean checkIfEmailExists(String email);
	
	DataResult<List<UserResponse>> getByDepartmentId(Long departmentId);
	
	DataResult<List<UserWithDepartmentDto>> getAllUsersWithDepartments();
}
