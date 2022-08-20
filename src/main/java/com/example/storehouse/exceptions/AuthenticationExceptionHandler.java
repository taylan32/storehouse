package com.example.storehouse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.storehouse.utils.results.ErrorResult;

@ControllerAdvice
public class AuthenticationExceptionHandler {

	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ErrorResult handleAuthException(AuthenticationException authenticationException) {
		return new ErrorResult(authenticationException.getMessage());
	}

}
