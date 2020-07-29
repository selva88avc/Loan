package com.rabo.bank.loan.exception;

public class LoanNotSavedException extends Exception {
	 String message;

	 public LoanNotSavedException(String message) {
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
