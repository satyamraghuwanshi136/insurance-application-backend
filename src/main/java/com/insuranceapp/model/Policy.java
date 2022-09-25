
package com.insuranceapp.model;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name="policy")
public class Policy 
{
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int policyId;
private long minIncome;
private String name;
private int tenure;
private long amount;
private long basePremium;
private String type;
private String subtype;
private int numberOfDependent;
private int extraForAgeSlap;
private String extraForNotEligible;
private int maxAge;
private int minAge;
private String description;

public int getPolicyId() {
	return policyId;
}
public void setPolicyId(int policyId) {
	this.policyId = policyId;
}
public long getMinIncome() {
	return minIncome;
}
public void setMinIncome(long minIncome) {
	this.minIncome = minIncome;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getTenure() {
	return tenure;
}
public void setTenure(int tenure) {
	this.tenure = tenure;
}
public long getAmount() {
	return amount;
}
public void setAmount(long amount) {
	this.amount = amount;
}
public long getBasePremium() {
	return basePremium;
}
public void setBasePremium(long basePremium) {
	this.basePremium = basePremium;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getSubtype() {
	return subtype;
}
public void setSubtype(String subtype) {
	this.subtype = subtype;
}
public int getNumberOfDependent() {
	return numberOfDependent;
}
public void setNumberOfDependent(int numberOfDependent) {
	this.numberOfDependent = numberOfDependent;
}
public int getExtraForAgeSlap() {
	return extraForAgeSlap;
}
public void setExtraForAgeSlap(int extraForAgeSlap) {
	this.extraForAgeSlap = extraForAgeSlap;
}
public String getExtraForNotEligible() {
	return extraForNotEligible;
}
public void setExtraForNotEligible(String extraForNotEligible) {
	this.extraForNotEligible = extraForNotEligible;
}
public int getMaxAge() {
	return maxAge;
}
public void setMaxAge(int maxAge) {
	this.maxAge = maxAge;
}
public int getMinAge() {
	return minAge;
}
public void setMinAge(int minAge) {
	this.minAge = minAge;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Policy(int policyId, long minIncome, String name, int tenure, long amount, long basePremium, String type,
		String subtype, int numberOfDependent, int extraForAgeSlap, String extraForNotEligible, int maxAge, int minAge,
		String description) {
	super();
	this.policyId = policyId;
	this.minIncome = minIncome;
	this.name = name;
	this.tenure = tenure;
	this.amount = amount;
	this.basePremium = basePremium;
	this.type = type;
	this.subtype = subtype;
	this.numberOfDependent = numberOfDependent;
	this.extraForAgeSlap = extraForAgeSlap;
	this.extraForNotEligible = extraForNotEligible;
	this.maxAge = maxAge;
	this.minAge = minAge;
	this.description = description;
}
public Policy() {
	super();
	
}

}


	