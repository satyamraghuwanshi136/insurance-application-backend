package com.insuranceapp.model;


import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import java.sql.Date;

@Entity
@Table(name="custdetails")
public class CustDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int custId;
	private char gender;
	private String nationality;
	private String occupation;
	private long income;
	private String placeOfBirth;
	private String idProof;
	private String coName;
	private String relationship;
	private Date dob;
	private String street;
	private String locality;
	private String area;
	private String city;
	private String state;
	private String pincode;
	
	  @OneToOne
	  @JoinColumn(name="customer_id")
	  @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	  private Customer customer;
	 
	  
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustDetails() {
	}

	public CustDetails(int custId, char gender, String nationality, String occupation, int income, String placeOfBirth,
			String idProof, String coName, String relationship,String street, String locality, String area, String city, String state,
			String pincode) {
		super();
		this.custId = custId;
		this.gender = gender;
		this.nationality = nationality;
		this.occupation = occupation;
		this.income = income;
		this.placeOfBirth = placeOfBirth;
		this.idProof = idProof;
		this.coName = coName;
		this.relationship = relationship;
		this.street = street;
		this.locality = locality;
		this.area = area;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public long getIncome() {
		return income;
	}

	public void setIncome(long income) {
		this.income = income;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getIdProof() {
		return idProof;
	}

	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}

	public String getCoName() {
		return coName;
	}

	public void setCoName(String coName) {
		this.coName = coName;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}


	
}
