package com.exception;

public class TaskUpdateException extends RuntimeException {

	private String message;
	
	public TaskUpdateException() {
		super();
	}
	public TaskUpdateException(String message) {
		super(message);
		this.message=message;
	}
}
