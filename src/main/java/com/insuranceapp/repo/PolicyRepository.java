package com.insuranceapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insuranceapp.model.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer>{

 List<Policy> findByNameLike(String policyName);
	 List<Policy> findByTypeLike(String type);
	 List<Policy> findBySubtype(String sub);
}
