package com.exception;

public class TaskCreationException extends RuntimeException {

	private String message;
	public TaskCreationException() {
		super();
	}
	public TaskCreationException(String message){
		super(message);
		this.message=message;
	}
	
}
