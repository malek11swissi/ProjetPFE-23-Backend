package com.pack.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pack.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.pack.service.UserService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class UserController {

	@Autowired
	UserService userService;


	@PreAuthorize("hasRole('ROLE_ADMIN')")
	
	@RequestMapping(method = RequestMethod.GET, value = "/users") 
	public List<User> getUser() {
	
		return userService.getAllUser();
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

	@DeleteMapping( value = "/users/deleteUser/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
	
	@PostMapping( value = "/users/addMarchand")
	public ResponseEntity<String> addMarchand(@RequestBody MarchandInformations marchandInformations) {
		
		ResponseEntity<String> response;
		try {
		  response = userService.addMarchand(marchandInformations);
		}  catch (Exception e) {
            String errorMessage = "marchand existe!";
            response = new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        
        return response;
		 
	}


	@GetMapping(value="/users/getAllMarchands")
	public List<Marchand> getAllMarchands() {
		return userService.getAllMarchands();
	}

	@GetMapping(value="/users/getMarchandInfos/{id}")
	public MarchandInfo getMarchandInfos(@PathVariable Long id) {
		return userService.getMarchandInfos(id);
	}

	@PutMapping(value="/users/updateMarchand")
	public MarchandInfo updateMarchand(@RequestBody MarchandInfo marchandInfo) {
		return userService.updateMarchand(marchandInfo);
	}

	@GetMapping(value="/users/getProfile")
	public Profile getMarchandInfos(Authentication authentication) {
		return userService.getUserProfile(authentication);
	}
 
	@PutMapping(value="/users/updateProfile")
	public void getMarchandInfos(Profile profile) {
		 userService.updateUserProfile(profile);
	} 

	@PutMapping(value = "/users/blockUser/{id}")
	public String blockUser(@PathVariable Long id)
	{
		return userService.blockUser(id);
	}

	@PutMapping(value = "/users/unblockUser/{id}")
	public String unblockUser(@PathVariable Long id)
	{
		return userService.unblockUser(id);
	}
}
