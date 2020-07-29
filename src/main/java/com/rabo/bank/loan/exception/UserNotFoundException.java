package com.rabo.bank.loan.exception;

public class UserNotFoundException extends RuntimeException{
 String message;

public UserNotFoundException(String message) {
	super();
	this.message = message;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}
}
