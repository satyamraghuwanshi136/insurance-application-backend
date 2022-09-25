package com.insuranceapp;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.insuranceapp.model.User;
import com.insuranceapp.repo.UserRepo;
import com.insuranceapp.service.UserService;

@SpringBootTest
public class UserTests {

	@Autowired
	private UserService service;
	@MockBean
	private UserRepo userRepo;

	@Test
	public void showAllUserTest() {
		when(userRepo.findAll()).thenReturn(Stream
				.of(new User(21, "a@gmail.com", "a@123", "admin"), new User(22, "b@gmail.com", "b@123", "underwriter"))
				.collect(Collectors.toList()));
		Assertions.assertEquals(2, service.getAllUser().size());
	}
	
	@Test
	public void createUserTest() throws Exception {
		User user = new User(23, "c@gmail.com", "c@123", "underwriter");
		when(userRepo.save(user)).thenReturn(user);
		Assertions.assertEquals(user, service.createUser(user));
	}
	
	// test getUser by email
	@Test
	public void getUserTest() {
		String email = "c@gmail.com";
		User user = new User(23, "c@gmail.com", "c@123", "underwriter");
		when(userRepo.findByEmail(email)).thenReturn(user);
		Assertions.assertEquals(user, service.getUserByEmail(email));
	}
	//test getUser by id
	
	@Test
	public void getUserByIdTest() {
		int userId = 26;
		//Optional<User> user = Optional.ofNullable(new User(23, "c@gmail.com", "c@123", "underwriter"));
		User user = new User(26, "f@gmail.com", "f@123", "underwriter");
		//userRepo.save(user);
		when(userRepo.findById(userId)).thenReturn(Optional.ofNullable(user));
		Assertions.assertEquals(user, service.getUserById(userId));
	}
	
	@Test
	public void deleteUserTest() {
		int userId = 25;
		User user = new User(25, "d@gmail.com", "d@123", "underwriter");
		userRepo.save(user);
		service.deleteUserById(userId);
		verify(userRepo, times(1)).deleteById(userId);
	}
	
	@Test
	public void updateUserTest() {
		int userId = 23;
		User user = new User(23, "d@gmail.com", "d@123", "underwriter");
		//userRepo.save(user);
		when(userRepo.findById(userId)).thenReturn(Optional.ofNullable(user));
		Assertions.assertEquals(user, service.updateUserById(user, userId));
	}
	
	
}
