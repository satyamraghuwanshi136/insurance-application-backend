
package com.insuranceapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insuranceapp.model.User;



public interface UserRepo extends JpaRepository<User, Integer> {
	public User findByEmail(String email);

}
