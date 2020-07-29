package com.rabo.bank.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rabo.bank.loan.exception.LoanNotSavedException;
import com.rabo.bank.loan.exception.LoansNotFound;
import com.rabo.bank.loan.model.Loan;
import com.rabo.bank.loan.service.LoanService;
@RestController
public class LoanController {
	@Autowired
	LoanService loanService;
	
	@GetMapping(path="loans", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Loan>> getLoans() throws LoanNotSavedException, LoansNotFound {
 
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		// This returns a JSON or XML with the users
		return new ResponseEntity<Iterable<Loan>>(loanService.getLoans(), headers, HttpStatus.ACCEPTED);
	}
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET,RequestMethod.POST, RequestMethod.OPTIONS} )
	@PostMapping(path="loans", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Loan> saveLoan(@RequestBody Loan loan) throws LoanNotSavedException {
		System.out.println("loan"+loan);
		HttpHeaders headers = new HttpHeaders();
		//headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
		headers.add("Access-Control-Allow-Headers", "*");
		headers.add("Access-Control-Expose-Headers", "Content-Type");
		Loan savedLoan = loanService.saveLoan(loan);
		System.out.println("------------------------");
		// This returns a JSON or XML with the users
		return new ResponseEntity<Loan>(savedLoan, headers, HttpStatus.ACCEPTED);
	}
}
