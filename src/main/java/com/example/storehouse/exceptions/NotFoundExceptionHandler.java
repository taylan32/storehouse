package com.example.storehouse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.storehouse.utils.results.ErrorResult;

@ControllerAdvice
public class NotFoundExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorResult handleNotFoundException(NotFoundException exception) {
		return new ErrorResult(exception.getMessage());
	}
	
}
