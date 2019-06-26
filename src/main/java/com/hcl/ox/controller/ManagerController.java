package com.hcl.ox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ox.entity.Loan;
import com.hcl.ox.service.ILoanService;

@RestController
@RequestMapping("/loan")
public class ManagerController {

	@Autowired
	private ILoanService loanServioce;

	@GetMapping("/{status}")
	public ResponseEntity<List<Loan>>findAllByStatus(@PathVariable String status) {
		List<Loan> loanList = loanServioce.findByLoanStatus(status);
		return new ResponseEntity<>(loanList, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{managerId}")
	public ResponseEntity<String> deleteByStatus(@PathVariable("managerId") long managerId) {
		String msg = loanServioce.deleteByStatus(managerId);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
