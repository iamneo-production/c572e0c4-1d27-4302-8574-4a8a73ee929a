package com.data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MyCustomExceptions {

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = Exception.class)
	public String exceptionHandlerNull() {
		return "id is Not Found";
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorHandling> myException(ResourceNotFoundException ex) {
		ErrorHandling er = new ErrorHandling(HttpStatus.NOT_FOUND.value(), ex.getMessage());

		return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);

	}
}