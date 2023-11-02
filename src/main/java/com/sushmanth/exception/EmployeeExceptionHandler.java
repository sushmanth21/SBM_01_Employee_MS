package com.sushmanth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(EmployeeException e){
		
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
				.body(e.getMessage());
	}
}
