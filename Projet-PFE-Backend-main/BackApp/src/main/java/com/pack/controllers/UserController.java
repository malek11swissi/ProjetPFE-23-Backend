package com.pack.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.ERole;
import com.pack.models.User;
import com.pack.service.UserService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class UserController {

	@Autowired
	UserService userService;

	//@RequestMapping("/users")
	//@PreAuthorize("hasRole('ADMIN')")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	
	@RequestMapping(method = RequestMethod.GET, value = "/users") 
	public List<User> getUser() {
		List<User> listeusers=new ArrayList<>();
		for(User user:userService.getAllUser()) {
			System.out.println("user :="+user);
			if(!user.getUsername().equals("admin"))
				listeusers.add(user);
		}
		System.out.println("listeusers");
		listeusers.forEach(u->{
			System.out.println(u.toString());
		});
		//return (List<User>) userService.getAllUser();
		return listeusers;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/users")
	public void addUser(@RequestBody User user) {
		System.out.println(user.toString());
		userService.addUser(user);
	}

	@RequestMapping("/users/{id}")
	public Optional<User> getSingleUser(@PathVariable Long id) {
		return userService.getSingleUser(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
	public void updateUser(@RequestBody User user, @PathVariable Long id) {
		System.out.println(user.toString());
		userService.updateUser(id, user);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
	
	

}
