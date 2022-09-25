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

import com.insuranceapp.model.Purchase;
import com.insuranceapp.service.PurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	private PurchaseService service;
	
	@PostMapping("/cId={cid}/nId={nid}/pId={pid}")
	public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase purchase,@PathVariable("cid")int cid,@PathVariable("nid")int nid,@PathVariable("pid")int pid)
	{
		return new ResponseEntity<Purchase>(service.createPurchase(purchase,cid,nid,pid),HttpStatus.CREATED);
	}
	
	@GetMapping("/all-purchase")
	public ResponseEntity<List<Purchase>> getAll()
	{
		return new ResponseEntity<List<Purchase>>(service.getAllPurchase(),HttpStatus.OK);
	}
	
	@GetMapping("/{purchaseId}")
	public ResponseEntity<Purchase> getByPid(@PathVariable int purchaseId)
	{
		return new ResponseEntity<Purchase>(service.getPurchaseByPid(purchaseId),HttpStatus.OK);
	}
	
	@GetMapping("/getstatus/{status}")
	public ResponseEntity<List<Purchase>> getstatus(@PathVariable String status)
	{
		return new ResponseEntity<List<Purchase>>(service.getPurchaseByStatus(status),HttpStatus.OK);
	}
	
	@GetMapping("/of{custId}") 
	  public ResponseEntity<List<Purchase>> getByCid(@PathVariable int custId) { 
		  return new ResponseEntity<List<Purchase>>(service.getPurchaseByCid(custId),HttpStatus.OK); 
		  }
	

	//Update purchase object
	@PutMapping("/update/{purchaseId}/by/{userId}")
	public ResponseEntity<Purchase> updateByPid(@RequestBody Purchase purchase,@PathVariable("purchaseId") int purchaseId)
	{
		return new ResponseEntity<Purchase>(service.updatePurchaseByPid(purchase, purchaseId),HttpStatus.OK);
	}
	
	//Delete purchase
		@DeleteMapping("/deleteP/{purchaseId}")
		public ResponseEntity<String> deleteByPid(@PathVariable int purchaseId)
		{
			service.deletePurchaseByPid(purchaseId);
			return new ResponseEntity<String>("Value Deleted",HttpStatus.OK);
		}
}
