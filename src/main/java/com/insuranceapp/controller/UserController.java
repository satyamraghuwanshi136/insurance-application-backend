
package com.insuranceapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.insuranceapp.model.Policy;
import com.insuranceapp.model.User;
import com.insuranceapp.service.UserService;


@EnableWebMvc
@RestController
@RequestMapping("/user")
//@CrossOrigin("*")
public class UserController {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserService userService;
	
	//creating user
	@PostMapping("")
	public ResponseEntity<User> creatUser(@RequestBody User user) throws Exception {
		user.setPsw(this.bCryptPasswordEncoder.encode(user.getPsw()));
		return new ResponseEntity<User>(this.userService.createUser(user), HttpStatus.CREATED);
	}
	// get the user by username/email
	
	@GetMapping("/by/{email}")
	public ResponseEntity<User> getUser(@PathVariable("email") String email) {
		return new ResponseEntity<User>(this.userService.getUserByEmail(email), HttpStatus.OK);
	}
	
	
	//delete the user by id
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Integer id) {
		this.userService.deleteUserById(id);
	}
	
	//update user
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User user ,@PathVariable("userId") Integer id) {
		user.setPsw(this.bCryptPasswordEncoder.encode(user.getPsw()));
		return new ResponseEntity<User>(this.userService.updateUserById(user, id), HttpStatus.OK);
	}
	
	// add more api"s....
	// get the user by id
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") Integer id) {
		return new ResponseEntity<User>(this.userService.getUserById(id),HttpStatus.OK);
	}
	
	//to get all users
	@GetMapping("/all-user")
	public List<User> showAllUser(){
		return this.userService.getAllUser();
	}

}
