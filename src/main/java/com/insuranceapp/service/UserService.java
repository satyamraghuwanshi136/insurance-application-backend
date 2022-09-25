
package com.insuranceapp.service;

import java.util.List;

import com.insuranceapp.model.User;


public interface UserService {
	//creating user
		public User createUser(User user) throws Exception;
		
		//get user by username
		public User getUserByEmail(String mail_id);
		
		// deleting user by id
		public void deleteUserById(Integer id);
		
		//updating a user
		public User updateUserById(User user, Integer id);
		
		//get user by id
		public User getUserById(Integer id);
		
		//get all user 
		public List<User> getAllUser();

}

