package com.insuranceapp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insuranceapp.config.JwtUtil;
import com.insuranceapp.model.JwtRequest;
import com.insuranceapp.model.JwtResponse;
import com.insuranceapp.model.User;
import com.insuranceapp.service.CustomUserDetailsService;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	//api for generate token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		}
		//there was UserNotFounsException
		catch(UsernameNotFoundException e){
			e.printStackTrace();
			throw new Exception("User not found");
		}
		
		//after authentication generate token
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	
	
	private void authenticate(String username, String password) throws Exception {
		//it will need authenticate manager
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		catch(DisabledException e){
			throw new Exception( "USER DISABLED " + e.getMessage());
		}
		catch(BadCredentialsException e) {
			throw new Exception("Invalid Credentials " + e.getMessage());
		}
		
	}
	
	// return the details of current user
	@GetMapping("/current-user")
	public UserDetails getCurrentUser(Principal principal) {
		// by getName() method of Principal interface we can get the username of the person who is currently loggedin
		return (this.userDetailsService.loadUserByUsername(principal.getName()));
	}

}
