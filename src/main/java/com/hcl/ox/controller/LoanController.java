package com.hcl.ox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ox.entity.Loan;
import com.hcl.ox.service.ILoanService;

@RestController
@RequestMapping("/loan")
public class LoanController {
	
	@Autowired
	private ILoanService loanServioce;
	
	@PostMapping("/apply")
	public ResponseEntity<Loan> applyLoan(@RequestBody Loan loan){
		Loan loan1=loanServioce.applyLoan(loan);
		return new ResponseEntity<>(loan1, HttpStatus.CREATED);
		
	}
}
