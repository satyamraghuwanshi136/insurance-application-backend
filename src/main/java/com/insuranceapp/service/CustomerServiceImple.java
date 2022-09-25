
package com.insuranceapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insuranceapp.model.Customer;
import com.insuranceapp.repo.Customer_Repo;



@Service
public class CustomerServiceImple implements CustomerService 
{

	@Autowired
	private Customer_Repo customer_repo;

	@Override
	public Customer createCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		Customer local = this.customer_repo.findByEmail(customer.getEmail());
		if(local != null) {
			System.out.println("User is already there !!");
			throw new Exception("User is already there !!");
		}else {
			//create customer
			local = this.customer_repo.save(customer);
		}
		return local;
		
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		// TODO Auto-generated method stub
		return this.customer_repo.findByEmail(email);
	}
	
	//@Override
	//public void deleteCustomerById(Integer id) {
		// TODO Auto-generated method stub
		//this.customer_repo.deleteById(id);
		
	//}
	

	@Override
	public Customer updateCustomerById(Customer customer, Integer id) {
		// TODO Auto-generated method stub
		Optional<Customer> optionalCustomer = this.customer_repo.findById(id);
		Customer customer1 = optionalCustomer.get();
		customer1.setName(customer.getName());
		customer1.setContactNo(customer.getContactNo());
		customer1.setEmail(customer.getEmail());
		customer1.setPsw(customer.getPsw());
		this.customer_repo.save(customer1);
		return customer1;
	}

	@Override
	public void deleteCustomerById(Integer id) {
		this.customer_repo.deleteById(id);
		
	}

	@Override
	public Customer getCustomerById(Integer id) {
		// TODO Auto-generated method stub
		return this.customer_repo.findById(id).get();
	}

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return this.customer_repo.findAll();
	}

	
	
}

