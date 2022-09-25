package com.insuranceapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insuranceapp.model.User;

@Repository
public interface LoginRepository extends JpaRepository<User, Integer> 
{
	User findByEmailAndPsw(String email, String psw);
}
