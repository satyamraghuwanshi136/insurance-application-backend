package com.insuranceapp.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority{

	private String role;


	public Authority(String role) {
		// TODO Auto-generated constructor stub
		this.role = role;
	}


	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.role;
	}
}
