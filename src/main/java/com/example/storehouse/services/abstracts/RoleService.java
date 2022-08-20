package com.example.storehouse.services.abstracts;

import java.util.List;

import com.example.storehouse.models.Role;
import com.example.storehouse.utils.results.DataResult;
import com.example.storehouse.utils.results.Result;

public interface RoleService {

	Result add(Role role);
	
	DataResult<List<Role>> getAll();
	
}
