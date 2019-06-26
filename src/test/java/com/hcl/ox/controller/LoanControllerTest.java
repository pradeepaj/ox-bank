package com.hcl.ox.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.ox.entity.Customer;
import com.hcl.ox.entity.Loan;
import com.hcl.ox.entity.Officer;
import com.hcl.ox.service.impl.LoanServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { TestContext.class, LoanController.class })
@WebAppConfiguration
public class LoanControllerTest {

	@InjectMocks
	private LoanController loanController;

	private MockMvc mockMvc;

	@Mock
	private LoanServiceImpl loanService;

	@Before
	public void Setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(loanController).build();

	}

	@Test
	public void applyLoanTest() throws JsonProcessingException, Exception {
		Customer custome = new Customer();
		Officer officer = new Officer();
		Loan loan = new Loan(23L, "Nepal", 4588, "Pending", 6790, custome, officer);
		when(loanService.applyLoan(loan)).thenReturn(loan);
		this.mockMvc.perform(post("/loan/apply").contentType(MediaType.APPLICATION_JSON).content(asJsonString(loan)));
		ResponseEntity<Loan> loan1 = loanController.applyLoan(loan);
		assertEquals(201, loan1.getStatusCodeValue());
	}

	public static String asJsonString(final Object object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);

	}

}
