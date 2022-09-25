package com.insuranceapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insuranceapp.model.Customer;
import com.insuranceapp.service.CustomerService;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTests {
	@Autowired
	public MockMvc mockMvc;

	@MockBean
	private CustomerService service;

	ObjectMapper om = new ObjectMapper();
	
	@Test
	public void createCustomerTest() throws Exception {
		Customer customer = new Customer(92,"aaa", "aaa11@gmail.com", "aaa@123", "9876543210");

		String jsonRequest = om.writeValueAsString(customer);
		String URI = "/customer";

		Mockito.when(service.createCustomer(Mockito.any(Customer.class))).thenReturn(customer);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(jsonRequest).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		// assertThat(outputInJson).isEqualTo(jsonRequest);
		Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

	@Test
	public void getCustomerByIdTest() throws Exception {

		Customer customer = new Customer(92,"aaa", "aaa11@gmail.com", "aaa@123", "9876543210");

		String jsonRequest = om.writeValueAsString(customer);
		// String URI = "/user/{userId}";
		String URI = "/customer/92";

		Mockito.when(service.getCustomerById(Mockito.anyInt())).thenReturn(customer);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				.with(SecurityMockMvcRequestPostProcessors.user("aaa11@gmail.com"))
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		assertThat(outputInJson).isEqualTo(jsonRequest);
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	// testing get user by email
	@Test
	public void getCustomerTest() throws Exception {

		Customer customer = new Customer(92,"aaa", "aaa11@gmail.com", "aaa@123", "9876543210");

		String jsonRequest = om.writeValueAsString(customer);
		// String URI = "/user/{userId}";
		String URI = "/customer/by/aaa11@gmail.com";

		Mockito.when(service.getCustomerByEmail(Mockito.anyString())).thenReturn(customer);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				.with(SecurityMockMvcRequestPostProcessors.user("aaa11@gmail.com").roles("normal"))
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		assertThat(outputInJson).isEqualTo(jsonRequest);
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void deleteCustomerTest() throws Exception {

		Customer customer = new Customer(92,"aaa", "aaa11@gmail.com", "aaa@123", "9876543210");
		String URI = "/customer/92";
		service.deleteCustomerById(customer.getCustId());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI)
				.with(SecurityMockMvcRequestPostProcessors.user("aaa11@gmail.com"))
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void updateCustomerTest() throws Exception {
		Customer customer = new Customer(92,"aaa", "aaa11@gmail.com", "aaa@123", "9876543210");

		String jsonRequest = om.writeValueAsString(customer);
		String URI = "/customer/92";

		Mockito.when(service.getCustomerById(Mockito.anyInt())).thenReturn(customer);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI)
				.with(SecurityMockMvcRequestPostProcessors.user("aaa11@gmail.com"))
				.accept(MediaType.APPLICATION_JSON).content(jsonRequest).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		//assertThat(outputInJson).isEqualTo(jsonRequest);
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());

	}


}
