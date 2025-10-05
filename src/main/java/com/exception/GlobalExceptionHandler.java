package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(TaskCreationException.class)
	public ResponseEntity<ExceptionResponse> handelTaskCreationException(Exception exception){
		ExceptionResponse response = new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handelUserNotFoundException(Exception exception){
		ExceptionResponse response = new ExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TaskNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handelTaskNotFoundException(Exception exception){
		ExceptionResponse response = new ExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TaskUpdateException.class)
	public ResponseEntity<ExceptionResponse> handelTaskUpdateException(Exception exception){
		ExceptionResponse response = new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TaskDeletionException.class)
	public ResponseEntity<ExceptionResponse> handelTaskDeleteException(Exception exception){
		ExceptionResponse response = new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}
}
