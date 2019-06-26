package com.hcl.ox.service.impl;

import java.util.List;

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
		String msg = null;
		try {
			List<Loan> loanList = loanRepository.findByManagerIdAndStatus(managerId, "rejected");
			if(!loanList.isEmpty()) {
				loanRepository.deleteAll(loanList);
				msg = "deleted successfully";
			} else {
				msg = "No records with Rejected status";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return msg;
	
	}

	

}
