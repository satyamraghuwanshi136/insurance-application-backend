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
import com.insuranceapp.model.User;
import com.insuranceapp.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(value = UserController.class)
public class UserControllerTests {
	@Autowired
	public MockMvc mockMvc;

	@MockBean
	private UserService service;

	ObjectMapper om = new ObjectMapper();

	@Test
	public void createUserTest() throws Exception {
		User user = new User(92, "zz11@gmail.com", "zz@123", "underwriter");

		String jsonRequest = om.writeValueAsString(user);
		String URI = "/user";

		Mockito.when(service.createUser(Mockito.any(User.class))).thenReturn(user);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(jsonRequest).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		// assertThat(outputInJson).isEqualTo(jsonRequest);
		Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

	@Test
	public void getUserByIdTest() throws Exception {

		User user = new User(92, "zz11@gmail.com", "zz@123", "underwriter");

		String jsonRequest = om.writeValueAsString(user);
		// String URI = "/user/{userId}";
		String URI = "/user/92";

		Mockito.when(service.getUserById(Mockito.anyInt())).thenReturn(user);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				.with(SecurityMockMvcRequestPostProcessors.user("\"zz11@gmail.com\"").roles("underwriter"))
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		assertThat(outputInJson).isEqualTo(jsonRequest);
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	 
	// testing get user by email
	@Test
	public void getUserTest() throws Exception {

		User user = new User(95, "dd11@gmail.com", "dd@123", "underwriter");

		String jsonRequest = om.writeValueAsString(user);
		// String URI = "/user/{userId}";
		String URI = "/user/by/dd11@gmail.com";

		Mockito.when(service.getUserByEmail(Mockito.anyString())).thenReturn(user);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				.with(SecurityMockMvcRequestPostProcessors.user("dd11@gmail.com").roles("underwriter"))
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		assertThat(outputInJson).isEqualTo(jsonRequest);
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void deleteUserTest() throws Exception {

		User user = new User(93, "pp11@gmail.com", "pp@123", "underwriter");
		String URI = "/user/93";
		service.deleteUserById(user.getUserId());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				.with(SecurityMockMvcRequestPostProcessors.user("pp11@gmail.com").roles("underwriter"))
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void updateUserTest() throws Exception {
		User user = new User(95, "dd11@gmail.com", "dd@123", "underwriter");

		String jsonRequest = om.writeValueAsString(user);
		String URI = "/user/95";

		Mockito.when(service.getUserById(Mockito.anyInt())).thenReturn(user);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI)
				.with(SecurityMockMvcRequestPostProcessors.user("dd11@gmail.com").roles("underwriter"))
				.accept(MediaType.APPLICATION_JSON).content(jsonRequest).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		//assertThat(outputInJson).isEqualTo(jsonRequest);
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	

	/*
	  @Test public void showAllUserTest() throws Exception {
	  
	  List<User> mockUserList = Stream.of(new User(92, "zz11@gmail.com", "zz@123",
	  "underwriter"), new User(93, "pp11@gmail.com", "pp@123",
	  "underwriter")).collect(Collectors.toList()); String jsonRequest =
	  om.writeValueAsString(mockUserList); String URI = "/user/all-user";
	  
	  Mockito.when(service.getAllUser()).thenReturn(mockUserList);
	  
	  RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
	  
	  MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	  
	  MockHttpServletResponse response = result.getResponse(); String outputInJson
	  = response.getContentAsString(); //
	  assertThat(outputInJson).isEqualTo(jsonRequest);
	  Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
	  
	  }
	*/
}
