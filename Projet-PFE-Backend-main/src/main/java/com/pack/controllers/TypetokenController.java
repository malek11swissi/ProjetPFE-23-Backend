package com.pack.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.ERole;
import com.pack.models.Typetoken;
import com.pack.service.TypetokenService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test/typetokens")
@RestController
public class TypetokenController {

	@Autowired
	TypetokenService typetokenService;

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	

	@GetMapping( value = "/getAllTypeToken") 
	public List<Typetoken> getTypetoken() {
		return typetokenService.getAllTypetoken();

	}

	@GetMapping("/getTypeToken/{id}")
	public Optional<Typetoken> getSingleTypetoken(@PathVariable Long id) {
		return typetokenService.getSingleTypetoken(id);
	}


	@PostMapping( value = "/saveTypeToken")
	public void addTypetoken(@RequestBody Typetoken typetoken) {
		System.out.println(typetoken.toString());
		typetokenService.addTypetoken(typetoken);
	}


	@RequestMapping(method = RequestMethod.PUT, value = "/typetokens/{id}")
	public void updateTypetoken(@RequestBody Typetoken typetoken, @PathVariable Long id) {
		System.out.println(typetoken.toString());
		typetokenService.updateTypetoken(id, typetoken);
	}

	@DeleteMapping( value = "/deleteTypeToken/{id}")
	public void deleteTypetoken(@PathVariable Long id) {
		typetokenService.deleteTypetoken(id);
	}
 
}
