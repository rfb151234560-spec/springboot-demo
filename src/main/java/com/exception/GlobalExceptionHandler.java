package com.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleNotFound(ResourceNotFoundException ex) {
		return new ErrorResponse(
				404,
				"Not Found",
				ex.getMessage()
			); 
				
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleValidation(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
		
		  List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

		    for (FieldError error : fieldErrors) {
		        String fieldName = error.getField();
		        String errorMessage = error.getDefaultMessage();

		        errors.put(fieldName, errorMessage);
		    }
		return errors;
	}
}
