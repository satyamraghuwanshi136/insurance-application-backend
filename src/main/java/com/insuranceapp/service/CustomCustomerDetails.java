package com.insuranceapp.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.insuranceapp.model.Authority;
import com.insuranceapp.model.Customer;

@Service
public class CustomCustomerDetails implements UserDetails{
	//create user & set username & password
		//Override granted authority
		private Customer customer;
		
		
		/*
		 * public CustomerSecurity(Customer customer) { this.customer = customer; }
		 */
		public Customer getCustomer() {
			return customer;
		}
		public void setCustomer(Customer customer) {
			this.customer = customer;
		}
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() 
		{
			Set<Authority> myAuth = new HashSet<>();
			myAuth.add(new Authority(customer.getRole()));
			return myAuth;
		}
		@Override
		public String getPassword() {
			return customer.getPsw();
		}
		@Override
		public String getUsername() {
			return customer.getEmail();
		}
		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}
}
