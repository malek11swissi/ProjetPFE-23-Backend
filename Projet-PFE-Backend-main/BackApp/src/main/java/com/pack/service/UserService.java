package com.pack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pack.models.User;
import com.pack.repository.UserRepository;


@Component
public class UserService {
	
	User user;

	@Autowired
	private UserRepository userRepo;
	
	
	public List<User> getAllUser() {
		return userRepo.findAll();
	}
	
	public void addUser(User user) {
		userRepo.save(user);
	}
	
	public Optional<User> getSingleUser(Long id) {
		return userRepo.findById(id);
	}
	
	public User getUser(String username) {
		userRepo.findAll().forEach(u->{
			if(u.getUsername().equals(username))
				user=u;
		});
		return user;
	}
	
	public void updateUser(Long id, User user) {
		userRepo.save(user);
	}
	
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}
	
	/*
	 * public User findUserbyUsername(String username) { User user= new User();
	 * userRepo.findAll().forEach(u->{ if(u.getUsername().equals(username)) {
	 * user.setId(u.getId()); user.se } }); return user; }
	 */
	
	
	
	
}
