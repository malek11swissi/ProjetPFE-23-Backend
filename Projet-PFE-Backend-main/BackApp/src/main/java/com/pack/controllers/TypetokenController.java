package com.pack.controllers;

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
import com.pack.models.Typetoken;
import com.pack.service.TypetokenService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class TypetokenController {

	@Autowired
	TypetokenService typetokenService;

	//@RequestMapping("/typetokens")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	//@PreAuthorize("hasRole('ROLE_MODERATOR')")

	@RequestMapping(method = RequestMethod.GET, value = "/typetokens") 
	public List<Typetoken> getTypetoken() {
		return (List<Typetoken>) typetokenService.getAllTypetoken();

	}

	@RequestMapping(method = RequestMethod.POST, value = "/typetokens")
	public void addTypetoken(@RequestBody Typetoken typetoken) {
		System.out.println(typetoken.toString());
		typetokenService.addTypetoken(typetoken);
	}

	@RequestMapping("/typetokens/{id}")
	public Optional<Typetoken> getSingleTypetoken(@PathVariable Long id) {
		return typetokenService.getSingleTypetoken(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/typetokens/{id}")
	public void updateTypetoken(@RequestBody Typetoken typetoken, @PathVariable Long id) {
		System.out.println(typetoken.toString());
		typetokenService.updateTypetoken(id, typetoken);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/typetokens/{id}")
	public void deleteTypetoken(@PathVariable Long id) {
		typetokenService.deleteTypetoken(id);
	}

}
