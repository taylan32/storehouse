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

import com.example.storehouse.requests.CreateRawMaterialRequest;
import com.example.storehouse.requests.UpdateRawMatetialRequest;
import com.example.storehouse.responses.RawMaterialResponse;
import com.example.storehouse.services.abstracts.RawMaterialService;
import com.example.storehouse.utils.results.DataResult;
import com.example.storehouse.utils.results.Result;

@RestController
@RequestMapping("/api/rawmaterials")
public class RawMaterialController {

	@Autowired
	private RawMaterialService materialService;

	@PostMapping("/")
	public ResponseEntity<Result> add(@RequestBody @Valid CreateRawMaterialRequest createRawMaterialRequest) {
		return ResponseEntity
				.created(URI.create(
						ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/rawmaterials").toUriString()))
				.body(this.materialService.add(createRawMaterialRequest));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Result> update(@PathVariable Long id, @RequestBody @Valid UpdateRawMatetialRequest matetialRequest) {
		return ResponseEntity.ok().body(this.materialService.update(id, matetialRequest));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Result> delete(@PathVariable Long id) {
		return ResponseEntity.ok().body(this.materialService.delete(id));
	}
	
	@GetMapping("/")
	public ResponseEntity<DataResult<List<RawMaterialResponse>>> getAll(){
		return ResponseEntity.ok().body(this.materialService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DataResult<RawMaterialResponse>> getAll(@PathVariable Long id){
		return ResponseEntity.ok().body(this.materialService.getById(id));
	}
}
