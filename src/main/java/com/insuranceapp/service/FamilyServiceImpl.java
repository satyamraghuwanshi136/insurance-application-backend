package com.insuranceapp.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insuranceapp.model.FamilyMembers;
import com.insuranceapp.repo.Customer_Repo;
import com.insuranceapp.repo.FamilyRepository;
import com.insuranceapp.repo.PurchaseRepo;

@Service
public class FamilyServiceImpl implements FamilyService {

	@Autowired
	FamilyRepository repo;
	@Autowired
	PurchaseRepo purchaseRepo;
	@Autowired
	Customer_Repo customerRepo;
	
	@Override
	public List<FamilyMembers> showAll() {
	return repo.findAll();
	
	}

	@Override
	public FamilyMembers addFamily(FamilyMembers fam,int custId,int purchaseId) {
		FamilyMembers famMem= new FamilyMembers();
		famMem.setCustomer(customerRepo.getById(custId));
		famMem.setPurchase(purchaseRepo.getById(purchaseId));
		famMem.setDob(fam.getDob());
		famMem.setIdProof(fam.getIdProof());
		famMem.setMobileNo(fam.getMobileNo());
		famMem.setName(fam.getName());
		famMem.setRelationship(fam.getRelationship());
		repo.save(famMem);
		return famMem;
	}

	@Override
	public FamilyMembers deleteFamily(int id) {
		repo.deleteById(id);
		return null;
	}

	@Override
	public FamilyMembers updateFamily(FamilyMembers fam,int id) {
		FamilyMembers famMem=repo.findById(id).get();
		famMem.setDob(fam.getDob());
		famMem.setIdProof(fam.getIdProof());
		famMem.setMobileNo(fam.getMobileNo());
		famMem.setName(fam.getName());
		famMem.setRelationship(fam.getRelationship());
		repo.save(famMem);
		return famMem;
	}

	
	/*
	 * @Override public List<FamilyMembers> getByCustFK(int custId) {
	 * List<FamilyMembers> list=repo.findAll(); List<FamilyMembers> list2=new
	 * ArrayList<FamilyMembers>(); Customer ct=customerRepo.findByCustId(custId);
	 * for(FamilyMembers fam:list) { if(fam.getCustomer()==ct) list2.add(fam); }
	 * return list2; }
	 */
	  
	  @Override
	  public List<FamilyMembers> getByCustPurchaseId(int custId,int purchaseId){
		  List<FamilyMembers> list=repo.findAll(); 
			  List<FamilyMembers> list2=new ArrayList<FamilyMembers>(); 
			     for(FamilyMembers fam:list) {
			  if(fam.getCustomer().getCustId()==custId && fam.getPurchase().getPurchaseId()==purchaseId ) 
				  list2.add(fam); 
			  } 
			  return list2;
			 
	  }
	 
}

