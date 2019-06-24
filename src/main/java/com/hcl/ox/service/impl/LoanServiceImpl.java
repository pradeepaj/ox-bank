package com.hcl.ox.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ox.entity.Loan;
import com.hcl.ox.repository.LoanRepository;
import com.hcl.ox.service.ILoanService;

@Service
public class LoanServiceImpl implements ILoanService {

	@Autowired
	private LoanRepository loanRepository;

	@Override
	public Loan applyLoan(Loan loan) {

		return loanRepository.save(loan);
	}

	@Override
	public List<Loan> findByLoanStatus(String loanStatus) {
		List<Loan> loanList = loanRepository.findByLoanStatus(loanStatus);
		return loanList;
	}

	@Override
	public String deleteByStatus( long managerId) {
		
		String msg;
	loanRepository.findBymanagerId(managerId);
	
		msg="Record deleted successfull ";
		
	return msg;
	
	}

	

}
