package com.example.storehouse.services.abstracts;

import java.util.List;

import com.example.storehouse.requests.DepartmentRequest;
import com.example.storehouse.responses.DepartmentResponse;
import com.example.storehouse.utils.results.DataResult;
import com.example.storehouse.utils.results.Result;

public interface DepartmentService {

	Result add(DepartmentRequest departmentRequest);
	
	Result update(Long id, DepartmentRequest departmentRequest);
	
	Result delete(Long id);
	
	DataResult<List<DepartmentResponse>> getAll();
	
	DataResult<DepartmentResponse> getById(Long id);
	
}
