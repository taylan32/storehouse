package com.example.storehouse.services.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.storehouse.contants.Messages;
import com.example.storehouse.exceptions.BusinessException;
import com.example.storehouse.models.Role;
import com.example.storehouse.repositories.RoleRepository;
import com.example.storehouse.services.abstracts.RoleService;
import com.example.storehouse.utils.results.DataResult;
import com.example.storehouse.utils.results.Result;
import com.example.storehouse.utils.results.SuccessDataResult;
import com.example.storehouse.utils.results.SuccessResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleServiceImp implements RoleService {

	private RoleRepository roleRepository;

	@Autowired
	public RoleServiceImp(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public Result add(Role role) {
		if (checkIfRoleExists(role.getName())) {
			throw new BusinessException(Messages.roleExists);
		}
		this.roleRepository.save(role);
		log.info("A new role, {} , has been created.", role.getName());
		return new SuccessResult(Messages.added);
	}
	
	@Override
	public DataResult<List<Role>> getAll() {
		return new SuccessDataResult<List<Role>>(this.roleRepository.findAll(), Messages.listed);
	}

	private boolean checkIfRoleExists(String roleName) {
		return this.roleRepository.existsByName(roleName);
	}

}
