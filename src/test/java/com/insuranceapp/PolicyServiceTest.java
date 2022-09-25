package com.insuranceapp;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.insuranceapp.model.CustDetails;
import com.insuranceapp.model.Policy;
import com.insuranceapp.repo.PolicyRepository;
import com.insuranceapp.service.PolicyService;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class PolicyServiceTest 
{
	@MockBean
	PolicyRepository policyRepository;
	@Autowired
	PolicyService policyService;
	
	//save policy Test
	@Test
	public void savePolicyTest()
	{
		Policy policy = new Policy();
		policy.setAmount(10000);
		policy.setBasePremium(5000);
		policy.setDescription("description1");
		policy.setExtraForAgeSlap(4);
		policy.setExtraForNotEligible("efne");
		policy.setMaxAge(80);
		policy.setMinAge(18);
		policy.setMinIncome(6000);
		policy.setName("policy1");
		policy.setNumberOfDependent(5);
		policy.setPolicyId(45);
		policy.setSubtype("subtype");
		policy.setTenure(10);
		policy.setType("type");
		when(policyRepository.save(policy)).thenReturn(policy);
		assertEquals(policy, policyService.savePolicy(policy));
	}
	//policy get by id
	@Test
	public void policyGetById()
	{
		Policy policy = new Policy();
		policy.setAmount(10000);
		policy.setBasePremium(5000);
		policy.setDescription("description1");
		policy.setExtraForAgeSlap(4);
		policy.setExtraForNotEligible("efne");
		policy.setMaxAge(80);
		policy.setMinAge(18);
		policy.setMinIncome(6000);
		policy.setName("policy1");
		policy.setNumberOfDependent(5);
		policy.setPolicyId(45);
		policy.setSubtype("subtype");
		policy.setTenure(10);
		policy.setType("type");
		when(policyRepository.findById(policy.getPolicyId())).thenReturn(Optional.ofNullable(policy));
		assertEquals(policy, policyService.getById(policy.getPolicyId()));
	}

	//delete by id
	@Test
	public void policyDeletetById()
	{
		Policy policy = new Policy();
		policy.setAmount(10000);
		policy.setBasePremium(5000);
		policy.setDescription("description1");
		policy.setExtraForAgeSlap(4);
		policy.setExtraForNotEligible("efne");
		policy.setMaxAge(80);
		policy.setMinAge(18);
		policy.setMinIncome(6000);
		policy.setName("policy1");
		policy.setNumberOfDependent(5);
		policy.setPolicyId(45);
		policy.setSubtype("subtype");
		policy.setTenure(10);
		policy.setType("type");
		policyService.delete(policy.getPolicyId());
		verify(policyRepository).deleteById(policy.getPolicyId());
	}
	
	//get all Policies Test
	@Test
	public void listAllPolicyTest()
	{
		when(policyRepository.findAll()).thenReturn(Stream.of(new Policy(37, 30000, "policy1", 10, 10000, 
				8000,"type","subtype",5, 6,"efne", 80, 20,"description"),
		new Policy(37, 30000, "policy1", 10, 10000, 8000,"type",
		"subtype",5, 6,"efne", 80, 20,"description")).collect(Collectors.toList()));
		assertEquals(2, policyService.listAll().size());
	}
	
	//delete all policies test
	@Test
	public void DeleteAllPolicyTest()
	{
		Policy policy = new Policy(37, 30000, "policy1", 10, 10000, 
				8000,"type","subtype",5, 6,"efne", 80, 20,"description");
		Policy policy1 = new Policy(37, 30000, "policy2", 10, 10000, 
				8000,"type","subtype",5, 6,"efne", 80, 20,"description");
		policyRepository.save(policy);
		policyRepository.save(policy1);
		policyService.deleteAll();
		verify(policyRepository, times(1)).deleteAll();
	}
	  
	
}
