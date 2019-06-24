package com.hcl.ox.service.impl.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.ox.entity.Customer;
import com.hcl.ox.entity.Loan;
import com.hcl.ox.entity.Officer;
import com.hcl.ox.repository.LoanRepository;
import com.hcl.ox.service.impl.LoanServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LoanServiceImplTest {

	@InjectMocks
	LoanServiceImpl loanServiceImpl;
	@Mock
	LoanRepository loanRepository;

	@Test
	public void applyLoan() {
		Customer custome = new Customer();
		Officer officer = new Officer();
		Loan loan = new Loan(23L, "Nepal", 4588, "Pending", 6790, custome, officer);
		when(loanRepository.save(loan)).thenReturn(loan);
		Loan actualResult = loanServiceImpl.applyLoan(loan);
		assertEquals(loan, actualResult);

	}
	
	@Test
	public void testFindByStatus() {
		Customer custome = new Customer();
		Officer officer = new Officer();
		Loan loan = new Loan(23L, "Nepal", 4588, "Pending", 6790, custome, officer);
		List<Loan> loanList = new ArrayList<>();
		loanList.add(loan);
		when(loanRepository.findAllByStatus(Mockito.anyString())).thenReturn(loanList);
		List<Loan> actualList =loanServiceImpl.findAllByStatus("pending");
		assertEquals(loanList.get(0).getCustomer(), actualList.get(0).getCustomer());
	}

}
