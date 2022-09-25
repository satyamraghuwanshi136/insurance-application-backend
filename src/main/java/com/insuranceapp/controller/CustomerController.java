
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

import com.insuranceapp.model.Customer;
import com.insuranceapp.model.User;
import com.insuranceapp.service.CustomerService;
@EnableWebMvc
@RestController
@RequestMapping("/customer")
//@CrossOrigin("*")
public class CustomerController{
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private CustomerService customerService;
	
	//creating customer
	@PostMapping("")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws Exception{
		customer.setPsw(this.bCryptPasswordEncoder.encode(customer.getPsw()));
		return new ResponseEntity<Customer>(this.customerService.createCustomer(customer),HttpStatus.CREATED);
	}
	
	//getting the customer
	@GetMapping("/by/{email}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("email") String mail_id) {
		return new ResponseEntity<Customer>(this.customerService.getCustomerByEmail(mail_id),HttpStatus.OK);
	}
	
	
	//delete the customer by id
	@DeleteMapping("/{custId}")
	public void deleteUser(@PathVariable("custId") Integer id) {
		this.customerService.deleteCustomerById(id);
	}
		
	//update customer
	@PutMapping("/{custId}")
	public ResponseEntity<Customer> updateUser(@RequestBody Customer customer ,@PathVariable("custId") Integer id) {
		customer.setPsw(this.bCryptPasswordEncoder.encode(customer.getPsw()));
		return new ResponseEntity<Customer>(this.customerService.updateCustomerById(customer, id),HttpStatus.OK);
	}
	
	// get the customer by id
	@GetMapping("/{custId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("custId") Integer id) {
		return new ResponseEntity<Customer>(this.customerService.getCustomerById(id),HttpStatus.OK);
	}
		
	//to get all customer
	@GetMapping("/all-customer")
	public List<Customer> showAllCustomer(){
		return this.customerService.getAllCustomer();
	}

}
