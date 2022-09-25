package com.insuranceapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer 
{
	/*
	 * @Autowired BCryptPasswordEncoder bCryptPasswordEncoder;
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int custId;
	private String name;
	private String email;
	private String psw;
	private String contactNo;

	public Customer() {	}

	public Customer(int custId, String name, String email, String psw, String contactNo) {
		super();
		this.custId = custId;
		this.name = name;
		this.email = email;
		this.psw = psw;
		this.contactNo = contactNo;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	public String getRole()
	{
	return "normal";
	}
	

}
