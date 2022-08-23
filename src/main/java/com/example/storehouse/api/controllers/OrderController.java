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

import com.example.storehouse.requests.CreateOrderRequest;
import com.example.storehouse.requests.UpdateOrderRequest;
import com.example.storehouse.responses.OrderResponse;
import com.example.storehouse.services.abstracts.OrderService;
import com.example.storehouse.utils.results.DataResult;
import com.example.storehouse.utils.results.Result;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/")
	public ResponseEntity<Result> add(@RequestBody @Valid CreateOrderRequest createOrderRequest) {
		return ResponseEntity
				.created(URI
						.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/orders").toUriString()))
				.body(this.orderService.add(createOrderRequest));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Result> update(@PathVariable Long id, @RequestBody @Valid UpdateOrderRequest orderRequest) {
		return ResponseEntity.ok().body(this.orderService.update(id, orderRequest));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Result> delete(@PathVariable Long id) {
		return ResponseEntity.ok().body(this.orderService.delete(id));
	}
	
	@GetMapping("/")
	public ResponseEntity<DataResult<List<OrderResponse>>> getAll() {
		return ResponseEntity.ok().body(this.orderService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DataResult<OrderResponse>> getById(@PathVariable Long id) {
		return ResponseEntity.ok().body(this.orderService.getById(id));
	}
	
}
