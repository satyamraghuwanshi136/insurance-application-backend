package com.insuranceapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insuranceapp.model.Customer;
import com.insuranceapp.model.Purchase;
import com.insuranceapp.repo.Customer_Repo;
import com.insuranceapp.repo.NomineeRepository;
import com.insuranceapp.repo.PolicyRepository;
import com.insuranceapp.repo.PurchaseRepo;

@Service
public class PurchaseServiceImpl implements PurchaseService{

	@Autowired
	private PurchaseRepo repo;

		
		@Autowired private Customer_Repo cRepo;
		@Autowired private NomineeRepository nRepo;
		@Autowired private PolicyRepository policyRepo;
		@Autowired private CustDetailsService cService;
		
		
		public Purchase createPurchase(Purchase purchase,int custId,int nomineeId,int policyId) {
			Purchase p= new Purchase();
			LocalDateTime dop= LocalDateTime.now();
			LocalDateTime date=dop.plusYears(policyRepo.findById(policyId).get().getTenure());
			p.setDop(dop);
			p.setDom(date);
			p.setPayCycle(purchase.getPayCycle());
			p.setPremium(purchase.getPremium());
			p.setCustomer(cRepo.findByCustId(custId));
			//System.out.println(cRepo.findByCustId(custId));
			p.setNominee(nRepo.findById(nomineeId).get());
			//System.out.println(nRepo.findById(nomineeId).get());
			p.setPolicy(policyRepo.findById(policyId).get());
			long income=cService.getByIdFK(custId).getIncome();
			//int age=cService.getByIdFK(custId).getDob();
			//checking the credentials for auto approve
			if(income>=policyRepo.findById(policyId).get().getMinIncome())
				p.setStatus("Auto Approved");
			else
			    p.setStatus("Pending");
			
			//p.setUser(userRepo.findById(userId).get()); Remove this line and all user id mentioned
			return repo.save(p);
		}

		
		public void deletePurchaseByPid(int purchaseId) {
			this.repo.deleteById(purchaseId);
		}

		public Purchase getPurchaseByPid(int purchaseId) {
			Purchase purchase = this.repo.findById(purchaseId).get();
			return purchase;
		}

		
		  public List<Purchase> getPurchaseByCid(int custId) { 
		  
			  List<Purchase> list= repo.findAll();
			  List<Purchase> customerList=new ArrayList<Purchase>();
			  Customer ct= cRepo.findByCustId(custId);
			  for(Purchase p:list) {
				 if(p.getCustomer().getCustId()==ct.getCustId())
					 customerList.add(p);
			  }
			  return customerList;
		   }
		 

		public List<Purchase> getPurchaseByStatus(String status) {
			return this.repo.getAllByStatus(status);
		}

		public List<Purchase> getAllPurchase() {
			return this.repo.findAll();
		}

		public Purchase updatePurchaseByPid(Purchase purchase, int purchaseId) {
			Purchase p = this.repo.findById(purchaseId).get();
			p.setStatus("Approved By Underwriter");
			//p.setUser(userRepo.findById(userId).get()); Remove this 
			//p.setUserId(purchase.getUserId());
			return this.repo.save(p);
		}

	}


