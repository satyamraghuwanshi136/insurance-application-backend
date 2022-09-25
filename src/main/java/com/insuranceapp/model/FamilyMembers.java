package com.insuranceapp.model;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class FamilyMembers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int familyMemId;
	private String name;
	private String relationship;
	private Date dob;
	private String mobileNo;
	private String idProof;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Customer customer;
	
	@OneToOne
	@JoinColumn(name="purchase_id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Purchase purchase;

	public int getFamilyMemId() {
		return familyMemId;
	}

	public void setFamilyMemId(int familyMemId) {
		this.familyMemId = familyMemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	

	public String getIdProof() {
		return idProof;
	}

	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}

	public FamilyMembers(int familyMemId, String name, String relationship, Date dob, String mobileNo,
			Customer customer, Purchase purchase) {
		this.familyMemId = familyMemId;
		this.name = name;
		this.relationship = relationship;
		this.dob = dob;
		this.mobileNo = mobileNo;
		this.customer = customer;
		this.purchase = purchase;
	}

	public FamilyMembers() {
	}
	
	
}


