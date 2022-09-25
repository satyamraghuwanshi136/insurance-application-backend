
package com.insuranceapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insuranceapp.model.Customer;

public interface Customer_Repo extends JpaRepository<Customer, Integer>
{
   public Customer findByEmail(String email);
   public Customer findByCustId(int i);

}

