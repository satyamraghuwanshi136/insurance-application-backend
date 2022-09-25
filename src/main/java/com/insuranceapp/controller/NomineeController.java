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

import com.insuranceapp.model.Nominee;
import com.insuranceapp.model.Policy;
import com.insuranceapp.service.NomineeService;

@RestController
@RequestMapping("/nominee")
public class NomineeController
{
	
	@Autowired
	private NomineeService nService;

	public NomineeController(NomineeService nService) {
		super();
		this.nService = nService;
	}
	//adding a nominee object
	@PostMapping("/add/id={custId}")
	public ResponseEntity<Nominee> saveNominee(@RequestBody Nominee nominee,@PathVariable("custId")int custId){
	return new ResponseEntity<Nominee>(nService.saveNominee(nominee,custId), HttpStatus.CREATED);
	}

	@GetMapping("")
	public ResponseEntity<List<Nominee>> allPolicy() {
		return new ResponseEntity<List<Nominee>>(nService.listAll(),HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Nominee> findPolicyById(@PathVariable("id") int id) {
		return new ResponseEntity<Nominee>(nService.getById(id), HttpStatus.OK);
	}
	
	//Edit nominee details
		@PutMapping("/{id}")
		public ResponseEntity<Nominee> editPolicy(@PathVariable("id") int id,@RequestBody Nominee nominee) {
			return new ResponseEntity<Nominee>(nService.updateNominee(nominee, id), HttpStatus.OK);
		} 

		//Delete nominee by ID
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<String> deletePolicy(@PathVariable("id")int id){
			nService.delete(id);
			return null;
		}
		//Find nominee by customerID
		@GetMapping("/custId={custId}")
		public ResponseEntity<List<Nominee>> findCustomerNominee(@PathVariable("custId")int custId){
			return new ResponseEntity<List<Nominee>>(nService.findCustomerNominee(custId),HttpStatus.OK);
		}
	
}
