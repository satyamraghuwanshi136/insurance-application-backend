package com.insuranceapp.service;

import java.util.List;

import com.insuranceapp.model.CustDetails;


public interface CustDetailsServiceInterface {
	public CustDetails saveCustDetails(CustDetails cDetails,int id);
	public CustDetails getById(int id);
	public void delete(int id);
	public List<CustDetails> listAll();
	public CustDetails updateCustDetails(CustDetails cDetails,int id);
	public List<CustDetails> getByState(String state);
	public List<CustDetails> getByPincode(String pincode);
	public List<CustDetails> getByCity(String city);
	public CustDetails getByIdFK(int id);
}

