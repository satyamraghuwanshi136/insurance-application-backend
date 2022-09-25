package com.insuranceapp.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.insuranceapp.model.Authority;
import com.insuranceapp.model.User;

@Service
public class CustomUserDetails implements UserDetails {
	//create user & set username & password
	//Override granted authority
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		//user.getRole().map(role -> new SimpleGrantedAuthority("ROLE_"+ role)).collect(Collectors.toList());
		//return new SimpleGrantedAuthority("ROLE_"+ user.getRole()).collect(Collectors.toList());
		Set<Authority> myAuth = new HashSet<>();
		myAuth.add(new Authority(user.getRole()));
		return myAuth;
		
		
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
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPsw();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
