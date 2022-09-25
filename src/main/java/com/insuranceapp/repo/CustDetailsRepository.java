package com.insuranceapp.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insuranceapp.model.CustDetails;

@Repository
public interface CustDetailsRepository extends JpaRepository<CustDetails, Integer> {
	
    List<CustDetails> findByStateLike(String state);
	List<CustDetails> findByCityLike(String city);
	List<CustDetails> findByPincodeLike(String pincode);

}

