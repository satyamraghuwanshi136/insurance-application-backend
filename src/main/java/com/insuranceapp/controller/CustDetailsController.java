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

import com.insuranceapp.model.CustDetails;
import com.insuranceapp.service.CustDetailsService;
@RestController
@RequestMapping("/custdetails")
public class CustDetailsController {

	@Autowired CustDetailsService cDetailsService;
	//adding a Customer Details object
			@PostMapping("/add/{id}")
			public ResponseEntity<CustDetails> saveCustomerDetails(@RequestBody CustDetails custDetails,@PathVariable("id")int id){
			return new ResponseEntity<CustDetails>(cDetailsService.saveCustDetails(custDetails,id), HttpStatus.CREATED);
			}
	

		@GetMapping("")
		public ResponseEntity<List<CustDetails>> allCustomers() {
			return new ResponseEntity<List<CustDetails>>(cDetailsService.listAll(),HttpStatus.OK);
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<CustDetails> findCustDetailsById(@PathVariable("id")int id) {
			return new ResponseEntity<CustDetails>(cDetailsService.getById(id), HttpStatus.OK);
		}
		
		@PutMapping("/{id}")
		public ResponseEntity<CustDetails> editCustDetails(@PathVariable("id") int id,@RequestBody CustDetails custDetails) {
			return new ResponseEntity<CustDetails>(cDetailsService.updateCustDetails(custDetails, id), HttpStatus.OK);
		} 
		

	    @DeleteMapping("/delete/{id}")
		public String deleteCustDetails(@PathVariable("id")int id){
			cDetailsService.delete(id);
			return "Deleted";
		}
	    
	  //getting all details for customer
		
		  @GetMapping("allDetails/{custId}") 
		  public ResponseEntity<CustDetails>findDetailsById(@PathVariable("custId")int custId) 
		  {   return new ResponseEntity<CustDetails>(cDetailsService.getByIdFK(custId),HttpStatus.OK); 
		  }
		  
		//Get Customer details by state
	    @GetMapping("/state/{state}")
	    public ResponseEntity<List<CustDetails>> findCustDetailsByState(@PathVariable("state") String state) {
			return new ResponseEntity<List<CustDetails>>(cDetailsService.getByState(state), HttpStatus.OK);
		}
	  //Get Customer details by state
	    @GetMapping("/city/{city}")
	    public ResponseEntity<List<CustDetails>> findCustDetailsByCity(@PathVariable("city") String city) {
			return new ResponseEntity<List<CustDetails>>(cDetailsService.getByCity(city), HttpStatus.OK);
		}
	  //Get Customer details by state
	    @GetMapping("/pincode/{pincode}")
	    public ResponseEntity<List<CustDetails>> findCustDetailsByPincode(@PathVariable("pincode") String pincode) {
			return new ResponseEntity<List<CustDetails>>(cDetailsService.getByPincode(pincode), HttpStatus.OK);
		}
}
