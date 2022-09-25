package com.insuranceapp.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insuranceapp.model.CustDetails;
import com.insuranceapp.model.Customer;
import com.insuranceapp.repo.CustDetailsRepository;
import com.insuranceapp.repo.Customer_Repo;

@Service
public class CustDetailsService implements CustDetailsServiceInterface{

	@Autowired private CustDetailsRepository cDetailsRepo;
	@Autowired private Customer_Repo repo;
	public CustDetailsService() {
		
	}
	@Override
	public CustDetails saveCustDetails(CustDetails cDetails,int id) {
			
		  CustDetails temp=new CustDetails(); 
		  temp.setGender(cDetails.getGender());
		  temp.setNationality(cDetails.getNationality());
		  temp.setOccupation(cDetails.getOccupation());
		  temp.setIncome(cDetails.getIncome());
		  temp.setPlaceOfBirth(cDetails.getPlaceOfBirth());
		  temp.setIdProof(cDetails.getIdProof()); 
		  temp.setCoName(cDetails.getCoName());
		  temp.setRelationship(cDetails.getRelationship());
		  temp.setDob(cDetails.getDob()); 
		  temp.setStreet(cDetails.getStreet());
		  temp.setLocality(cDetails.getLocality()); 
		  temp.setArea(cDetails.getArea());
		  temp.setCity(cDetails.getCity()); 
		  temp.setState(cDetails.getState());
		  temp.setPincode(cDetails.getPincode());
		  temp.setCustomer(repo.findByCustId(id));
		  cDetailsRepo.save(temp); 
		  return temp;
		  		 
	}
	
	@Override
	public CustDetails getById(int id) {
		return cDetailsRepo.findById(id).get();
	}
	//Deleting a single object by id
	@Override
	public void delete(int id) {
		cDetailsRepo.deleteById(id);
		//add a condition to find out whether the user exists or not
	}
	//Returning a list of all objects in the table
	@Override
	public List<CustDetails> listAll(){
		return cDetailsRepo.findAll();
	}

@Override	
public CustDetails updateCustDetails(CustDetails cDetails,int id) {
		CustDetails temp=cDetailsRepo.findById(id).get();
		temp.setGender(cDetails.getGender());
		temp.setNationality(cDetails.getNationality()); 
		  temp.setOccupation(cDetails.getOccupation()); 
		  temp.setIncome(cDetails.getIncome()); 
		  temp.setPlaceOfBirth(cDetails.getPlaceOfBirth()); 
		  temp.setIdProof(cDetails.getIdProof());
		  temp.setCoName(cDetails.getCoName()); 
		  temp.setRelationship(cDetails.getRelationship()); 
		 temp.setDob(cDetails.getDob());
		 temp.setStreet(cDetails.getStreet());
		 temp.setLocality(cDetails.getLocality());
		 temp.setArea(cDetails.getArea());
		 temp.setCity(cDetails.getCity());
		 temp.setState(cDetails.getState());
		 temp.setPincode(cDetails.getPincode());
		cDetailsRepo.save(temp);
		return  temp;
	}
	
    @Override
	public List<CustDetails> getByState(String state) {
		return cDetailsRepo.findByStateLike(state);
	}

	@Override
	public List<CustDetails> getByPincode(String pincode) {
		return cDetailsRepo.findByPincodeLike(pincode);
	
	}

	@Override
	public List<CustDetails> getByCity(String city) {
		return cDetailsRepo.findByCityLike(city);
	}
	  @Override 
	  public CustDetails getByIdFK(int custId) {
		List<CustDetails> list=cDetailsRepo.findAll(); 
		Customer ct=repo.findByCustId(custId);
		CustDetails cust=new CustDetails();
		for(CustDetails c:list) {
		if(c.getCustomer().getCustId()==ct.getCustId())
			cust=c;
		}
		return cust;
	  }
	 
}
