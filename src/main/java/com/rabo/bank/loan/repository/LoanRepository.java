package com.rabo.bank.loan.repository;


import org.springframework.data.repository.CrudRepository;

import com.rabo.bank.loan.model.Loan;

public interface LoanRepository extends CrudRepository<Loan, Integer>{


}
