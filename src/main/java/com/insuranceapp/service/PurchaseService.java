package com.insuranceapp.service;

import java.util.List;

import com.insuranceapp.model.Purchase;

public interface PurchaseService {
	
	public Purchase createPurchase(Purchase purchase,int custId,int nomineeId,int policyId);
	
	public void deletePurchaseByPid(int purchaseId);
	
	//public void deletePurchaseByCid(int custId);
	
	public Purchase getPurchaseByPid(int purchaseId);
	
	public List<Purchase> getPurchaseByCid(int custId);
	
	public List<Purchase> getPurchaseByStatus(String status);
	
	public List<Purchase> getAllPurchase();
	
	public Purchase updatePurchaseByPid(Purchase purchase,int purchaseId);

}
