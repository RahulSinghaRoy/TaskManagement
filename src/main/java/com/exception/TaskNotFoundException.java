package com.exception;

public class TaskNotFoundException extends RuntimeException {

	private String message;
	
	public TaskNotFoundException() {
		super();
	}
	public TaskNotFoundException(String message) {
		super(message);
		this.message=message;
	}
}
