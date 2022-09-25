package com.insuranceapp.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insuranceapp.model.FamilyMembers;
import com.insuranceapp.service.FamilyService;


@RestController
@RequestMapping("/family")
public class FamilyController {

	@Autowired
	FamilyService service;
	
	//method to add family members with purchase id and customer id
	@PostMapping("/add/cust={custId}/purchase={purchaseId}")
	public ResponseEntity<FamilyMembers> saveFamily(@RequestBody FamilyMembers fam,@PathVariable("custId")int custId,@PathVariable("purchaseId")int purchaseId){
	return new ResponseEntity<FamilyMembers>(service.addFamily(fam,custId,purchaseId),HttpStatus.CREATED);
	}


	
//	@PostMapping("/add/{id}")
//	public ResponseEntity<FamilyMembers> saveFamily(@PathVariable("fam")FamilyMembers fam,@PathVariable("custId")int custId,int purchaseId){
//		return new ResponseEntity<FamilyMembers>(service.addFamily(fam,custId,purchaseId),HttpStatus.CREATED);
//	}
	
	
	//method to delete family member row
	@DeleteMapping("delete/{id}")
	public ResponseEntity<FamilyMembers> deleteFamily(@PathVariable("id")int id){
		return new ResponseEntity<FamilyMembers>(service.deleteFamily(id),HttpStatus.OK);
	}
	
	//method to update family member
	@PutMapping("/{id}")
	public ResponseEntity<FamilyMembers> updateFamily(@RequestBody FamilyMembers fam,@PathVariable("id")int id){
		return new ResponseEntity<FamilyMembers>(service.updateFamily(fam,id),HttpStatus.OK);
	}
	
	//method to show family member in a particular purchase of a customer
	@GetMapping("/showof{custId}/of{purchaseId}")
	public ResponseEntity<List<FamilyMembers>> showByCustAndPurchase(@PathVariable("custId")int custId,@PathVariable("purchaseId")int purchaseId){
		return new ResponseEntity<List<FamilyMembers>>(service.getByCustPurchaseId(custId,purchaseId),HttpStatus.OK);
	}
	
	//method to show all members
	@GetMapping("")
	public ResponseEntity<List<FamilyMembers>> showAll(){
		return new ResponseEntity<List<FamilyMembers>>(service.showAll(),HttpStatus.OK);
	}
}
