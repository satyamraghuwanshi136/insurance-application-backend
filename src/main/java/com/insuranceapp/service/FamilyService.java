package com.insuranceapp.service;
import java.util.List;
import com.insuranceapp.model.FamilyMembers;


public interface FamilyService {

	public List<FamilyMembers> showAll();
	public FamilyMembers addFamily(FamilyMembers fam,int custId,int purchaseId);
	public FamilyMembers deleteFamily(int id);
	public FamilyMembers updateFamily(FamilyMembers fam,int id);
	//public List<FamilyMembers> getByCustFK(int custId);
	public List<FamilyMembers> getByCustPurchaseId(int custId,int purchaseId);
	
}
