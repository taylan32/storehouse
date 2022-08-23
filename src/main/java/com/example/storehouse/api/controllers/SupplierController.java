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

import com.example.storehouse.requests.CreateSupplierRequest;
import com.example.storehouse.requests.UpdateSupplierRequest;
import com.example.storehouse.responses.SupplierResponse;
import com.example.storehouse.services.abstracts.SupplierService;
import com.example.storehouse.utils.results.DataResult;
import com.example.storehouse.utils.results.Result;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	@PostMapping("/")
	public ResponseEntity<Result> add(@RequestBody @Valid CreateSupplierRequest createSupplierRequest) {
		return ResponseEntity
				.created(URI.create(
						ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/suppliers").toUriString()))
				.body(this.supplierService.add(createSupplierRequest));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Result> update(@PathVariable Long id, @RequestBody @Valid UpdateSupplierRequest updateSupplierRequest) {
		return ResponseEntity.ok().body(this.supplierService.update(id, updateSupplierRequest));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Result> delete(@PathVariable Long id) {
		return ResponseEntity.ok().body(this.supplierService.delete(id));
	}
	
	
	@GetMapping("/")
	public ResponseEntity<DataResult<List<SupplierResponse>>> getAll() {
		return ResponseEntity.ok().body(this.supplierService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DataResult<SupplierResponse>> getById(@PathVariable Long id) {
		return ResponseEntity.ok().body(this.supplierService.getById(id));
	}
	
	
}
