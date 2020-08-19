package com.springreactive.bms.exception;

public class AlreadyExistsException extends Throwable {
	
	private String message;

	public String getMessage() {
		return "user already exists";
	}
	

}
