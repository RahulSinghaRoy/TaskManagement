package com.exception;

public class TaskDeletionException extends RuntimeException {

	private String message;
	
	public TaskDeletionException() {
		super();
	}
	public TaskDeletionException(String message) {
		super(message);
		this.message=message;
	}
}
