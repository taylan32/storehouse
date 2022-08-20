package com.example.storehouse.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.storehouse.models.Role;
import com.example.storehouse.services.abstracts.RoleService;
import com.example.storehouse.utils.results.DataResult;
import com.example.storehouse.utils.results.Result;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@PostMapping("/")
	public ResponseEntity<Result> add(@RequestBody Role role) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles/").toUriString());
		return ResponseEntity.created(uri).body(this.roleService.add(role));
	}
	
	@GetMapping("/")
	public ResponseEntity<DataResult<List<Role>>> getAll() {
		return ResponseEntity.ok().body(this.roleService.getAll());
	}
	
}
