package com.insuranceapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insuranceapp.controller.PolicyController;
import com.insuranceapp.model.Policy;
import com.insuranceapp.service.PolicyService;

@SpringBootTest
@AutoConfigureMockMvc
public class PolicyControllerTests {
	@Autowired
	public MockMvc mockMvc;

	@MockBean
	private PolicyService service;
	
	@Autowired
	private PolicyController policyController;

	ObjectMapper om = new ObjectMapper();

	@Test
	public void allPolicyTest() throws Exception {

		List<Policy> mockPolicyList = Stream.of(new Policy(92, 200000,"policy1", 10,10000,4, "type1","subtype1", 4, 5, "extraforNotEligible", 50,18, "description1"),
				new Policy(93,40000, "policy2", 20, 40000,4, "type2","subtype2", 4, 5, "extrafornoteligible",75, 18, "description2")).collect(Collectors.toList());
		String jsonRequest = om.writeValueAsString(mockPolicyList);
		String URI = "/policy";

		Mockito.when(service.listAll()).thenReturn(mockPolicyList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString(); //
		assertThat(outputInJson).isEqualTo(jsonRequest);
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void findPolicyByIdTest() throws Exception {

		Policy policy = new Policy(93,40000, "policy2", 20, 40000,4, "type2","subtype2", 4, 5, "extrafornoteligible",75, 18, "description2");

		String jsonRequest = om.writeValueAsString(policy);
		
		String URI = "/policy/93";

		Mockito.when(service.getById(Mockito.anyInt())).thenReturn(policy);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		assertThat(outputInJson).isEqualTo(jsonRequest);
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void editPolicyTest() throws Exception {
		Policy policy = new Policy(93,40000, "policy2", 20, 40000,4, "type2","subtype2", 4, 5, "extrafornoteligible",75, 18, "description2");

		String jsonRequest = om.writeValueAsString(policy);
		String URI = "/policy/92";

		Mockito.when(service.getById(Mockito.anyInt())).thenReturn(policy);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI)
				
				.accept(MediaType.APPLICATION_JSON).content(jsonRequest).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		//assertThat(outputInJson).isEqualTo(jsonRequest);
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	
	/*
	 * @Test public void savePolicyTest() throws Exception { Policy policy = new
	 * Policy(93,40000, "policy2", 20, 40000,4, "type2","subtype2", 4, 5,
	 * "extrafornoteligible",75, 18, "description2");
	 * Mockito.when(service.savePolicy(Mockito.any(Policy.class))).thenReturn(policy
	 * ); String jsonRequest = om.writeValueAsString(policy); String URI
	 * ="/addPolicy";
	 * //Mockito.when(service.savePolicy(Mockito.any(Policy.class))).thenReturn(
	 * policy);
	 * 
	 * RequestBuilder requestBuilder =
	 * MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
	 * .content(jsonRequest).contentType(MediaType.APPLICATION_JSON); MvcResult
	 * result = mockMvc.perform(requestBuilder).andReturn(); MockHttpServletResponse
	 * response = result.getResponse(); String outputInJson=
	 * response.getContentAsString(); //
	 * assertThat(outputInJson).isEqualTo(jsonRequest);
	 * Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus()); }
	 */
	
	/*
	@Test
	public void deletePolicyTest() throws Exception {

		Policy policy = new Policy(93, "policy2", 20, 400000, "type2","subtype2", 4, 5, 75, 18, "description2");
		//String URI = "/policy/delete/92";
		String URI = "/delete/92";
		service.delete(policy.getPolicy_id());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI)
				
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	*/
}
