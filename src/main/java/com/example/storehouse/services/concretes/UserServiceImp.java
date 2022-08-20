package com.example.storehouse.services.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.storehouse.contants.Messages;
import com.example.storehouse.exceptions.NotFoundException;
import com.example.storehouse.models.Role;
import com.example.storehouse.models.User;
import com.example.storehouse.models.dto.UserWithDepartmentDto;
import com.example.storehouse.repositories.RoleRepository;
import com.example.storehouse.repositories.UserRepository;
import com.example.storehouse.requests.AssignRoleRequest;
import com.example.storehouse.responses.UserResponse;
import com.example.storehouse.services.abstracts.UserService;
import com.example.storehouse.utils.results.DataResult;
import com.example.storehouse.utils.results.Result;
import com.example.storehouse.utils.results.SuccessDataResult;
import com.example.storehouse.utils.results.SuccessResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImp implements UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;

	@Autowired
	public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	@Transactional
	public Result addRoleToUser(AssignRoleRequest request) {

		Role roleToAssign = this.roleRepository.getById(request.getRoleId());
		if (!checkIfUserExistsById(request.getUserId())) {
			User user = this.userRepository.getById(request.getUserId());
			user.getRoles().add(roleToAssign);
			this.userRepository.save(user);
			log.info("{} role has been assigned to user:{}", roleToAssign.getName(), user.getEmail());
		}
		return new SuccessResult(Messages.roleAssigned);

	}

	@Override
	public DataResult<UserResponse> getById(Long id) {
		User user = this.userRepository.getById(id);
		if (user == null) {
			throw new NotFoundException(Messages.userNotFound);
		}
		return new SuccessDataResult<UserResponse>(new UserResponse(user), Messages.listed);
	}

	@Override
	public DataResult<List<UserResponse>> getAll() {
		List<User> users = userRepository.findAll();
		return new SuccessDataResult<List<UserResponse>>(
				users.stream().map(u -> new UserResponse(u)).collect(Collectors.toList()), Messages.listed);
	}

	@Override
	public DataResult<List<UserResponse>> getByDepartmentId(Long departmentId) {
		List<User> users = this.userRepository.getByDepartmentId(departmentId);
		return new SuccessDataResult<List<UserResponse>>(
				users.stream().map(user -> new UserResponse(user)).collect(Collectors.toList()), Messages.listed);
	}
	
	@Override
	public DataResult<List<UserWithDepartmentDto>> getAllUsersWithDepartments() {
		return new SuccessDataResult<List<UserWithDepartmentDto>>(this.userRepository.getAllUsersWithDepartment(), Messages.listed);
	}

	public boolean checkIfEmailExists(String email) {
		return this.userRepository.existsByEmail(email);
	}

	private boolean checkIfUserExistsById(Long userId) {
		if (!this.userRepository.existsById(userId)) {
			throw new NotFoundException(Messages.userNotFound);
		}
		return false;
	}

}
