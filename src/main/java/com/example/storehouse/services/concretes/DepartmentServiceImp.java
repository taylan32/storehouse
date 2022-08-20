package com.example.storehouse.services.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.storehouse.contants.Messages;
import com.example.storehouse.exceptions.BusinessException;
import com.example.storehouse.exceptions.NotFoundException;
import com.example.storehouse.models.Department;
import com.example.storehouse.repositories.DepartmentRepository;
import com.example.storehouse.requests.DepartmentRequest;
import com.example.storehouse.responses.DepartmentResponse;
import com.example.storehouse.services.abstracts.DepartmentService;
import com.example.storehouse.utils.results.DataResult;
import com.example.storehouse.utils.results.Result;
import com.example.storehouse.utils.results.SuccessDataResult;
import com.example.storehouse.utils.results.SuccessResult;

@Service
public class DepartmentServiceImp implements DepartmentService {

	private DepartmentRepository departmentRepository;

	@Autowired
	public DepartmentServiceImp(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public Result add(DepartmentRequest departmentRequest) {
		if (checkIfDepartmentExistsByName(departmentRequest.getDepartmentName())) {
			throw new BusinessException(Messages.departmentExists);
		}
		Department department = new Department();
		department.setName(departmentRequest.getDepartmentName());
		this.departmentRepository.save(department);
		return new SuccessResult(Messages.added);
	}

	@Override
	public Result update(Long id, DepartmentRequest departmentRequest) {
		if (!checkIfDepartmentExistsById(id)) {
			throw new NotFoundException(Messages.departmentNotFound);
		}
		Department department = this.departmentRepository.getById(id);
		department.setName(department.getName());
		this.departmentRepository.save(department);
		return new SuccessResult(Messages.updated);
	}

	@Override
	public Result delete(Long id) {
		if (!checkIfDepartmentExistsById(id)) {
			throw new NotFoundException(Messages.departmentNotFound);
		}
		this.departmentRepository.delete(this.departmentRepository.getById(id));
		return new SuccessResult(Messages.deleted);
	}

	@Override
	public DataResult<List<DepartmentResponse>> getAll() {
		List<Department> departments = this.departmentRepository.findAll();
		return new SuccessDataResult<List<DepartmentResponse>>(
				departments.stream().map(department -> new DepartmentResponse(department)).collect(Collectors.toList()),
				Messages.listed);
	}

	@Override
	public DataResult<DepartmentResponse> getById(Long id) {
		Department department = this.departmentRepository.getById(id);
		if (department == null) {
			throw new NotFoundException(Messages.departmentNotFound);
		}
		return new SuccessDataResult<DepartmentResponse>(new DepartmentResponse(department), Messages.listed);
	}

	private boolean checkIfDepartmentExistsByName(String departmentName) {
		return this.departmentRepository.existsByName(departmentName);
	}

	private boolean checkIfDepartmentExistsById(Long id) {
		return this.departmentRepository.existsById(id);
	}

}
