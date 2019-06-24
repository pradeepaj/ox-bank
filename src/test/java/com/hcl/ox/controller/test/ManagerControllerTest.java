package com.hcl.ox.controller.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.ox.controller.ManagerController;
import com.hcl.ox.entity.Customer;
import com.hcl.ox.entity.Loan;
import com.hcl.ox.entity.Officer;
import com.hcl.ox.service.impl.LoanServiceImpl;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class ManagerControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webAppContext;
	
	@InjectMocks
	private ManagerController managerController;
	
	@Mock
	private LoanServiceImpl loanservice;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}

	@Test
	public void findAllByStatusTest() {
		Customer custome = new Customer();
		Officer officer = new Officer();
		Loan loan = new Loan(23L, "Nepal", 4588, "Pending", 6790, custome, officer);
		Loan loan1 = new Loan(23L, "Nepal", 4588, "Pending", 6790, custome, officer);
		List<Loan> mylist = new ArrayList<>();
		mylist.add(loan);
//		mylist.add(loan1);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonObject = null;

		try {
			ResponseEntity<List<Loan>> respList =new ResponseEntity<>(mylist, HttpStatus.OK);
			
			jsonObject = objectMapper.writeValueAsString(mylist);
			when(loanservice.findAllByStatus("pending")).thenReturn(mylist);

			mockMvc.perform(MockMvcRequestBuilders.get("/{status}","pending"))
			.andExpect(status().isOk()).andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON));
//			.andReturn();
			ResponseEntity<List<Loan>> actualList = managerController.findAllByStatus("pending");
			assertEquals(jsonObject, actualList.getBody());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
