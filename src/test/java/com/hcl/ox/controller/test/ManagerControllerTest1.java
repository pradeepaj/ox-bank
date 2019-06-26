package com.hcl.ox.controller.test;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.ox.controller.ManagerController;
import com.hcl.ox.service.ILoanService;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { TestContext.class, ManagerController.class })
@WebAppConfiguration
public class ManagerControllerTest1 {

	@InjectMocks
	ManagerController managerController;

	private MockMvc mockMvc;

	@Mock
	ILoanService loanServioce;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(managerController).build();
	}

	@Test
	public void testdeleteByStatus() throws JsonProcessingException, Exception {
		long managerId = 10L;
		String msg = "deleted successfully";
		ResponseEntity<String> res = new ResponseEntity<>(msg, HttpStatus.OK);
		Mockito.when(loanServioce.deleteByStatus(Mockito.anyLong())).thenReturn(msg);
		this.mockMvc.perform(delete("/delete/{managerId}", 10L).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(asJsonString(managerId))).andReturn();

		ResponseEntity<String> res1 = managerController.deleteByStatus(managerId);
		assertEquals(res, res1);
	}

	public static String asJsonString(final Object object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);

	}

}
