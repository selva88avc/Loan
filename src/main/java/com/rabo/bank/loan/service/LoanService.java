package com.rabo.bank.loan.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabo.bank.loan.exception.LoanNotSavedException;
import com.rabo.bank.loan.exception.LoansNotFound;
import com.rabo.bank.loan.model.Loan;
import com.rabo.bank.loan.model.User;
import com.rabo.bank.loan.repository.LoanRepository;

@Service
public class LoanService {
	@Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
	private LoanRepository loanRepository;
	@Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
	private UserService userService;

	public Loan saveLoan(Loan loan) throws LoanNotSavedException {
		// TODO Auto-generated method stub
		Loan loanSaved = loanRepository.save(loan);
		System.out.println(loanSaved);
		if(loanSaved==null) {
			System.out.println("yes");
			throw new LoanNotSavedException("Loan not saved");
		}
		return loanSaved;
	}
	public Iterable<Loan> getLoans() throws LoansNotFound {
		// TODO Auto-generated method stub
		Iterable<Loan> loans = loanRepository.findAll();
		User[] users  = userService.findUsers();
		//Stream<User> userStream = Arrays.stream(users);
		List<Loan> updatedLoans = StreamSupport.stream(loans.spliterator(), false)
			      .map((loan) -> {
			    	  loan.setBorrower(Arrays.stream(users).filter((user)->loan.getUserId()==user.getUserId()).findFirst().get());
			    	  return loan;})
			      .collect(Collectors.toList());
		if(updatedLoans==null) {
			throw new LoansNotFound("Loans not found");
		}
		return updatedLoans;
	}
	
}