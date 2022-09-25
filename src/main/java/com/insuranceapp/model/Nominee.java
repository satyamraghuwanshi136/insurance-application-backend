package com.insuranceapp.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="nominee")
public class Nominee 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int nomineeId;
	private String name;
	private Date dob;
	private String relationship;
	private String idProof;
	
	// mapping with customer
	@ManyToOne
	@JoinColumn(name="customer_id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Customer customer;
	
	public Nominee(int nomineeId, int purchaseId, String name, Date dob, String relationship, String idProof) {
		super();
		this.nomineeId = nomineeId;
		this.name = name;
		this.dob = dob;
		this.relationship = relationship;
		this.idProof = idProof;
	}
	public int getNomineeId() {
		return nomineeId;
	}
	public void setNomineeId(int nomineeId) {
		this.nomineeId = nomineeId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getIdProof() {
		return idProof;
	}
	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Nominee() {
		super();
		
	}
	
}
