package com.example.storehouse.api.controllers;



import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.storehouse.requests.LoginRequest;
import com.example.storehouse.requests.RegisterRequest;
import com.example.storehouse.services.abstracts.AuthService;
import com.example.storehouse.utils.results.Result;



@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private AuthService authService;
	
	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<Result> register(@RequestBody @Valid RegisterRequest registerRequest) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/auth/register").toUriString());
		return ResponseEntity.created(uri).body(this.authService.register(registerRequest));
	}
	
	@PostMapping("/login")
	public ResponseEntity<Result> login(@RequestBody @Valid LoginRequest loginRequest) {
		return ResponseEntity.ok().body(this.authService.login(loginRequest));
	}
	
}
