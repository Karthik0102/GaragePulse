package com.garagepulse.exception;

public class WorkOrderNotFoundException extends RuntimeException {
	public WorkOrderNotFoundException(String message) {
		super(message);
	}
}
