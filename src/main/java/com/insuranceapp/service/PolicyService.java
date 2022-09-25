package com.insuranceapp.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.insuranceapp.model.Policy;
import com.insuranceapp.repo.PolicyRepository;



@Service
public class PolicyService 
{
	@Autowired
	private PolicyRepository repo;
	//Policy p;
	
	//SAving an object
	public Policy savePolicy(Policy p) {
		return repo.save(p);
		//repo.save(p);
	}
	//Finding an object
	public Policy getById(int id) {
		return repo.findById(id).get();
	}
	//Deleting a single object by id
	public void delete(int id) {
		repo.deleteById(id);
		//add a condition to find out whether the user exists or not
	}
	//Returning a list of all objects in the table
	public List<Policy> listAll(){
		return repo.findAll();
	}
	//Deleting all the objects in the table
	public void deleteAll() {
		repo.deleteAll();
	}

	//finding policy by type{Individual/Family}
	public List<Policy> findPolicyByType(String type){
		return repo.findByTypeLike(type);
	}
	
	//finding policy by subtype
	public List<Policy> findPolicyBySubtype(String sub){
		return repo.findBySubtype(sub);
	}
	//finding policy by name
	public List<Policy> findPolicyByName(String name) {
		return repo.findByNameLike(name);
		
	}
	//updating policy by id
	public Policy updatePolicy(Policy p,int id) {
		Policy temp=repo.findById(id).get();
		temp.setAmount(p.getAmount());
		temp.setDescription(p.getDescription());
		temp.setExtraForAgeSlap(p.getExtraForAgeSlap());
		temp.setMaxAge(p.getMaxAge());
		temp.setMinAge(p.getMinAge());
		temp.setName(p.getName());
		temp.setNumberOfDependent(p.getNumberOfDependent());
		temp.setSubtype(p.getSubtype());
		temp.setTenure(p.getTenure());
		temp.setType(p.getType());
		temp.setBasePremium(p.getBasePremium());
		temp.setExtraForNotEligible(p.getExtraForNotEligible());
		temp.setMinIncome(p.getMinIncome());
		temp.setPolicyId(p.getPolicyId());
		repo.save(temp);
		return temp;
	}
	
	
	public List<Policy> findPolicy(int age,int dependents,long income){
		List<Policy> userPlan=new ArrayList<Policy>();
		List<Policy> allPolicy=repo.findAll();
		for(Policy p:allPolicy) {
			if(p.getMaxAge()>=age && age>=p.getMinAge() && (age+p.getTenure()<=65)&& dependents<=p.getNumberOfDependent() && p.getMinIncome()<=income)
				userPlan.add(p);	
		}	
		return userPlan;
	}
	
	
	public Long calculatePremium(int age,int id){
		Policy p=repo.findById(id).get();
		int dependents=0;
		long add1=(p.getBasePremium()*p.getExtraForAgeSlap()*((Math.abs(age-p.getMinAge()))/5))/100;
		System.out.println(add1);
		int y=p.getNumberOfDependent()-dependents;
		if(y>0)
		y=y*p.getExtraForAgeSlap()/p.getNumberOfDependent();
		else y=0;
		long add2=(p.getBasePremium()*y)/100;
		//System.out.println(add2);
		long amount= (p.getBasePremium()+add1+add2)/p.getTenure();
		//System.out.println(amount);
		return amount;
	}
		
}
