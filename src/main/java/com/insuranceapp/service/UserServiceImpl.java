
package com.insuranceapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.insuranceapp.model.User;
import com.insuranceapp.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepository;

	@Override
	public User createUser(User user) throws Exception {
		// TODO Auto-generated method stub
		User local = this.userRepository.findByEmail(user.getEmail());
		if(local != null) {
			System.out.println("User is already there !!");
			throw new Exception("User is already there !!");
		}else {
			//create user
			local = this.userRepository.save(user);
		}
		return local;
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return this.userRepository.findByEmail(email);
	}

	@Override
	public void deleteUserById(Integer id) {
		// TODO Auto-generated method stub
		this.userRepository.deleteById(id);
	}

	@Override
	public User updateUserById(User user, Integer id) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser = this.userRepository.findById(id);
		User user1 = optionalUser.get();
		user1.setEmail(user.getEmail());
		user1.setPsw(user.getPsw());
		user1.setRole(user.getRole());
		this.userRepository.save(user1);
		return user1;
	}

	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		return this.userRepository.findById(id).get();
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return this.userRepository.findAll();
	}
	

}

