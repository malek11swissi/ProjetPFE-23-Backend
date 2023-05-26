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
import com.pack.models.Token;
import com.pack.service.PanierService;
import com.pack.service.TokenService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class TokenController {

	@Autowired
	TokenService tokenService;
	@Autowired
	PanierService panierService;
	//@RequestMapping("/tokens")
	@PreAuthorize("hasRole('ADMIN')")
	//@PreAuthorize("hasRole('ROLE_MODERATOR')")
	
	@RequestMapping(method = RequestMethod.GET, value = "/tokens") 
	public List<Token> getToken() 
	{
		tokenService.getAllToken().forEach(t->{
			System.out.println(t.toString());
		});
		return (List<Token>) tokenService.getAllToken();
	}

	/*
	 * @RequestMapping(method = RequestMethod.POST, value = "/tokens") public void
	 * addToken(@RequestBody Token token) {
	 * System.out.println("je suis dans ajout token");
	 * System.out.println(token.toString()); tokenService.addToken(token); }
	 */
	

	@RequestMapping("/tokens/{id}")
	public Optional<Token> getSingleToken(@PathVariable Long id) {
		return tokenService.getSingleToken(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/tokens/{id}")
	public void updateToken(@RequestBody Token token, @PathVariable Long id) {
		System.out.println(token.toString());
		tokenService.updateToken(id, token);
	}


	
}
