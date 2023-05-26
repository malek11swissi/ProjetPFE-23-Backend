package com.pack.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.ERole;
import com.pack.models.Panier;
import com.pack.models.Token;
import com.pack.service.PanierService;
import com.pack.service.TokenService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test/tokens")
@RestController
public class TokenController {

	@Autowired
	TokenService tokenService;
	@Autowired
	PanierService panierService;

	@PreAuthorize("hasRole('ROLE_CLIENT')")


	@RequestMapping("/getToken/{id}")
	public Optional<Token> getSingleToken(@PathVariable Long id) {
		return tokenService.getSingleToken(id);
	}

	@PutMapping( value = "/updateToken")
	public void updateToken(@RequestBody Token token) {
		tokenService.updateToken( token);
	}

	@GetMapping( value = "/getUserTokens")
	public List<Token> getTokenByUser(Authentication authentication ) {
		return tokenService.getTokensByUser(authentication);
	}
	
	@DeleteMapping( value = "/deleteToken/{id}")
	public void deleteToken(@PathVariable Long id) {
		//determiner l'id du panier a supprimer a partir de l'id du token
		panierService.deletePanier(panierService.retournerIdPanier(id));
		tokenService.deleteToken(id);
		
	}
	
	//ajouter token 
	@PostMapping( value = "/createToken")
	public void createToken(@RequestBody Token token, Authentication authentication) {
		
		Panier panier=new Panier();

		token.setActive(true);
		//parametres du nemero serie
		
		tokenService.saveToken(token ,authentication);

		//vreation du panier
		panier.setToken(token);
		panier.setUser(token.getUser());
		panier.setActive(true);
		panierService.addPanier(panier);
		
	
	}
	
}
