package com.example.storehouse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.storehouse.utils.results.ErrorResult;

@ControllerAdvice
public class BusinessExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResult handleBusinessException(BusinessException exception) {
		return new ErrorResult(exception.getMessage());
	}
	
}
