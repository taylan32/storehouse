package com.example.storehouse.responses;

import lombok.Data;

@Data
public class AuthResponse {

	private long userId;
	
	private String email;
	
	private String accessToken;
	

	
}
