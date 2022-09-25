package com.insuranceapp.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insuranceapp.model.Customer;
import com.insuranceapp.model.Nominee;
import com.insuranceapp.repo.Customer_Repo;
import com.insuranceapp.repo.NomineeRepository;

@Service
public class NomineeService 
{
	@Autowired
	private NomineeRepository nRepo;
	@Autowired private Customer_Repo repo;
	
	//Saving a nominee object
		public Nominee saveNominee(Nominee nominee,int custId) {
			Nominee temp=new Nominee();
			temp.setCustomer(repo.findByCustId(custId));
			temp.setDob(nominee.getDob());
			temp.setIdProof(nominee.getIdProof());
			temp.setName(nominee.getName());
			temp.setRelationship(nominee.getRelationship());
			return nRepo.save(temp);
		}
		
	public Nominee getById(int id) {
		return nRepo.findById(id).get();
	}
	//Deleting a single object by id
	public void delete(int id) {
		nRepo.deleteById(id);
		//add a condition to find out whether the user exists or not
	}
	//Returning a list of all objects in the table
	public List<Nominee> listAll(){
		return nRepo.findAll();
	}
	//updating policy by id
	public Nominee updateNominee(Nominee nominee,int id) {
		Nominee temp=nRepo.findById(id).get();
		temp.setDob(nominee.getDob());
		temp.setIdProof(nominee.getIdProof());
		temp.setName(nominee.getName());
		temp.setRelationship(nominee.getRelationship());
		nRepo.save(temp);
		return temp;
	}

	//Finding nominee by customer id
		public List<Nominee> findCustomerNominee(int custId){
			List<Nominee> all=nRepo.findAll();
			List<Nominee> list=new ArrayList<Nominee>();
			Customer ct=repo.findByCustId(custId);
			for(Nominee n:all) {
				if(n.getCustomer().getCustId()==ct.getCustId())
					list.add(n);
			}
			return list;
		}
}
