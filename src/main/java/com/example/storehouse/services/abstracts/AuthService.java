package com.example.storehouse.services.abstracts;

import com.example.storehouse.requests.LoginRequest;
import com.example.storehouse.requests.RegisterRequest;
import com.example.storehouse.responses.AuthResponse;
import com.example.storehouse.utils.results.DataResult;
import com.example.storehouse.utils.results.Result;

public interface AuthService {

	Result register(RegisterRequest registerRequest);
	
	DataResult<AuthResponse> login(LoginRequest loginRequest);
	
}
