package com.insuranceapp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.insuranceapp.model.Customer;
import com.insuranceapp.model.User;
import com.insuranceapp.repo.Customer_Repo;
import com.insuranceapp.repo.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private Customer_Repo customerRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = this.userRepo.findByEmail(email);
		Customer customer = this.customerRepo.findByEmail(email);
		CustomUserDetails userDetails = null;
		CustomCustomerDetails customerDetails = null;
		if(user!=null) {
			userDetails = new CustomUserDetails();
			userDetails.setUser(user);
			return userDetails;
			
		}else if(customer!=null)
		{
			//return new org.springframework.security.core.userdetails.User(customer.getEmail(),customer.getPsw(),new ArrayList<>());
			customerDetails = new CustomCustomerDetails();
			customerDetails.setCustomer(customer);
			return customerDetails;
			
		}
		else {
			throw new UsernameNotFoundException("User not exist with name : "+ email);
		}
		
		
	}
}
