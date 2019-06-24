package com.hcl.ox.service;

import java.util.List;
import java.util.Optional;

import com.hcl.ox.entity.Loan;

public interface ILoanService {

	public Loan applyLoan(Loan loan);

//	public List<Loan> findAllByStatus(String loanStatus);
	public List<Loan> findByLoanStatus(String loanStatus);


	public String deleteByStatus( long managerId);

}
