package com.example.storehouse.api.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.storehouse.requests.DepartmentRequest;
import com.example.storehouse.responses.DepartmentResponse;
import com.example.storehouse.services.abstracts.DepartmentService;
import com.example.storehouse.utils.results.DataResult;
import com.example.storehouse.utils.results.Result;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

	private DepartmentService departmentService;

	@Autowired
	public DepartmentController(DepartmentService deparmentService) {
		this.departmentService = deparmentService;
	}

	@PostMapping("/")
	public ResponseEntity<Result> add(@RequestBody @Valid DepartmentRequest departmentRequest) {
		URI uri = URI
				.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/departments/").toUriString());
		return ResponseEntity.created(uri).body(this.departmentService.add(departmentRequest));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Result> update(@PathVariable Long id,
			@RequestBody @Valid DepartmentRequest departmentRequest) {
		return ResponseEntity.ok().body(this.departmentService.update(id, departmentRequest));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Result> delete(@PathVariable Long id) {
		return ResponseEntity.ok().body(this.departmentService.delete(id));
	}

	@GetMapping("/")
	public ResponseEntity<DataResult<List<DepartmentResponse>>> getAll() {
		return ResponseEntity.ok().body(this.departmentService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<DataResult<DepartmentResponse>> getById(@PathVariable Long id) {
		return ResponseEntity.ok().body(this.departmentService.getById(id));
	}

}
