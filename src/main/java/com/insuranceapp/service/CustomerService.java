
package com.insuranceapp.service;

import java.util.List;

import com.insuranceapp.model.Customer;


public interface CustomerService 
{
	//creating customer
	public Customer createCustomer(Customer customer) throws Exception;
	
	//get user by email
	public Customer getCustomerByEmail(String email);
			
	// deleting user by id
	public void deleteCustomerById(Integer id);
			
	//updating a customer
	public Customer updateCustomerById(Customer customer, Integer id);
	
	//get customer by id
	public Customer getCustomerById(Integer id);
			
	//get all customers 
	public List<Customer> getAllCustomer();

}

