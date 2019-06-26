package com.hcl.ox.controller.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.ox.controller.ManagerController;
import com.hcl.ox.entity.Customer;
import com.hcl.ox.entity.Loan;
import com.hcl.ox.entity.Officer;
import com.hcl.ox.service.impl.LoanServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class ManagerControllerTest {

	private MockMvc mockMvc;
//
//	@Autowired
//	private WebApplicationContext webAppContext;

	@InjectMocks
	private ManagerController managerController;

	@Mock
	private LoanServiceImpl loanservice;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(managerController).build();
//				MockMvcBuilders.webAppContextSetup(managerController).build();
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
			jsonObject = objectMapper.writeValueAsString(mylist);
			when(loanservice.findByLoanStatus("pending")).thenReturn(mylist);

			this.mockMvc.perform(get("/{status}", "pending").contentType(MediaType.APPLICATION_JSON).content(jsonObject))
					.andReturn();
			ResponseEntity<List<Loan>> actualList = managerController.findAllByStatus("pending");
			assertEquals(23L, actualList.getBody().get(0).getLoanAccountNumber());
		} catch (Exception e ) {
			e.printStackTrace();
		}
	}
	
	/*
	 * @Test public void testDeleteByStatus() {
	 * 
	 * Customer custome = new Customer(); Officer officer = new Officer(); Loan loan
	 * = new Loan(23L, "Nepal", 4588, "Pending", 6790, custome, officer); String
	 * expectedMsg = "deleted successfully"; try {
	 * when(loanservice.findByManagerIdAndStatus(Mockito.anyLong(),
	 * Mockito.anyString())).the }catch (Exception e) { e.printStackTrace(); } }
	 */
}
