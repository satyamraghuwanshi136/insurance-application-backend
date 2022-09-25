package com.insuranceapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insuranceapp.model.Purchase;

@Repository
public interface PurchaseRepo extends JpaRepository<Purchase, Integer>{
	
	//List<Purchase> getAllByCustId(int custId);

	List<Purchase> getAllByStatus(String status);

	//void deleteAllByCustId(int custId);
}
