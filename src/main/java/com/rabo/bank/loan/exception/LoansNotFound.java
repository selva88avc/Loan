package com.rabo.bank.loan.exception;

public class LoansNotFound extends Exception {
	 String message;

	 public LoansNotFound(String message) {
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
