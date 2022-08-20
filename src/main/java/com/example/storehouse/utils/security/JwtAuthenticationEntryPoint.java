package com.example.storehouse.utils.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.example.storehouse.utils.results.ErrorResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		ObjectMapper objectMapper = new ObjectMapper();
		if (authException.getMessage().equals("Full authentication is required to access this resource")) {
			ErrorResult result = new ErrorResult("You must login");
			response.getOutputStream().println(objectMapper.writeValueAsString(result));
		} else {
			response.getOutputStream()
					.println(objectMapper.writeValueAsString(new ErrorResult(authException.getMessage())));
		}
	}

}