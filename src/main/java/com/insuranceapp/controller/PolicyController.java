package com.insuranceapp.controller;
import java.lang.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.insuranceapp.model.Policy;
import com.insuranceapp.service.PolicyService;

@EnableWebMvc
@RestController
public class PolicyController 
{
	@Autowired
	private PolicyService service;
	

	public PolicyController(PolicyService service) {
		super();
		this.service = service;
	}
		
	@PostMapping("/addPolicy")
	public ResponseEntity<Policy> savePolicy(@RequestBody Policy p){
	return new ResponseEntity<Policy>(service.savePolicy(p), HttpStatus.CREATED);
	}
	
	@GetMapping("/policy")
	public ResponseEntity<List<Policy>> allPolicy() {
		return new ResponseEntity<List<Policy>>(service.listAll(),HttpStatus.OK);
		
	}
	
	@GetMapping("/policy/{id}")
	public ResponseEntity<Policy> findPolicyById(@PathVariable("id") int id) {
		return new ResponseEntity<Policy>(service.getById(id), HttpStatus.OK);
	}
	
	@PutMapping("/policy/{id}")
	public ResponseEntity<Policy> editPolicy(@PathVariable("id") int id,@RequestBody Policy p) {
		return new ResponseEntity<Policy>(service.updatePolicy(p, id), HttpStatus.OK);
	} 
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePolicy(@PathVariable("id")int id){
		service.delete(id);
		return null;
	}
	@GetMapping("/policy/name/{name}")
	public ResponseEntity<List<Policy>> findPolicyByName(@PathVariable("name") String policyName) {
		return new ResponseEntity<List<Policy>>(service.findPolicyByName("%"+policyName+"%"),HttpStatus.OK);
	}
	@GetMapping("/policy/type/{type}")
	public ResponseEntity<List<Policy>> findPolicyByType(@PathVariable("type") String policyType) {
		return new ResponseEntity<List<Policy>>(service.findPolicyByType(policyType),HttpStatus.OK);
	}
	
	@GetMapping("/policy/subtype/{sub}")
	public ResponseEntity<List<Policy>> findPolicyBySubtype(@PathVariable("sub") String policyType) {
		return new ResponseEntity<List<Policy>>(service.findPolicyBySubtype(policyType),HttpStatus.OK);
	}
	@GetMapping("/showPolicies/age={age}/members={dependents}/income={income}")
	public ResponseEntity<List<Policy>> showPolicy(@PathVariable("age")int age,@PathVariable("dependents")int dependents,@PathVariable("income")long income){
		return new ResponseEntity<List<Policy>>(service.findPolicy(age, dependents,income), HttpStatus.OK);
	}
	
	@GetMapping("/calculate/policy/{id}/for/{age}")
	public ResponseEntity<Long> calculatePremium(@PathVariable("age") int age,@PathVariable("id") int id) {
		return new ResponseEntity<Long>(service.calculatePremium(age, id), HttpStatus.OK);
		
	}

}
