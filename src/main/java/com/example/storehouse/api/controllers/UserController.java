package com.example.storehouse.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.storehouse.models.dto.UserWithDepartmentDto;
import com.example.storehouse.requests.AssignRoleRequest;
import com.example.storehouse.responses.UserResponse;
import com.example.storehouse.services.abstracts.UserService;
import com.example.storehouse.utils.results.DataResult;
import com.example.storehouse.utils.results.Result;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public ResponseEntity<DataResult<List<UserResponse>>> getAll() {
		return ResponseEntity.ok().body(this.userService.getAll());
	}

	@PostMapping("/assign")
	public ResponseEntity<Result> assignRole(@RequestBody @Valid AssignRoleRequest request) {
		return ResponseEntity.ok().body(this.userService.addRoleToUser(request));

	}

	@GetMapping("/getByDepartment")
	public ResponseEntity<DataResult<List<UserResponse>>> getByDepartmentId(@RequestParam Long departmentId) {
		return ResponseEntity.ok().body(this.userService.getByDepartmentId(departmentId));
	}

	@GetMapping("/userDetails")
	public ResponseEntity<DataResult<List<UserWithDepartmentDto>>> getAllUsersWithDepartment() {
		return ResponseEntity.ok().body(this.userService.getAllUsersWithDepartments());
	}
	
}
