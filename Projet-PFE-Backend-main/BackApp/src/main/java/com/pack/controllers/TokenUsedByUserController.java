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

import com.pack.models.Token;
import com.pack.models.ERole;
import com.pack.models.Panier;
import com.pack.models.Token;
import com.pack.service.PanierService;
import com.pack.service.TokenService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class TokenUsedByUserController {

	@Autowired
	TokenService tokenService;
	
	@Autowired
	PanierService panierService;
	

	//@RequestMapping("/tokens")
	//@PreAuthorize("hasRole('ADMIN')")
	@PreAuthorize("hasRole('ROLE_USER')")
	
	/*
	 * @RequestMapping(method = RequestMethod.GET, value = "/tokensByUser") public
	 * List<Token> getTokenByUser()
	 * 
	 * { tokenService.getAllToken().forEach(t->{ System.out.println(t.toString());
	 * }); return (List<Token>) tokenService.getAllToken();
	 * 
	 * }
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/tokensByUser/{username}")
	public List<Token> getTokenByUser(@PathVariable String username) {
//	public int getTokenByUser(@PathVariable Long iduser) {
		System.out.println("username:= " + username);
		return tokenService.getTokensByUser(username);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/tokensByUser/{id}")
	public void deleteToken(@PathVariable Long id) {
		//determiner l'id du panier a supprimer a partir de l'id du token
		System.out.println("id token "+id);
		panierService.deletePanier(panierService.retournerIdPanier(id));
		tokenService.deleteToken(id);
		
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/tokens")
	public void addTokenUsername(@RequestBody Token token) {
		System.out.println("methode addTokenUsername");
		System.out.println("token in parameter "+token.toString());
		Panier panier=new Panier();
		System.out.println("je suis dans ajout token with username");
		//token.setCompteur(token.getCompteur());
		System.out.println("token compteur:= "+token.getCompteur());
		//token.setTypetoken(token.getTypetoken());
		System.out.println("token typetoken:= "+token.getTypetoken());
		//token.setUser(token.getCompteur().getUser());
		System.out.println("token user:= "+token.getUser());
		token.setUser(token.getCompteur().getUser());
		System.out.println("je suis dans ajout token with username");
		//token.setCompteur(token.getCompteur());
		token.setActive(true);
		System.out.println("token to add "+token.toString());
		tokenService.addToken(token);
		//parametres du nemero serie
		System.out.println("token id:= "+token.getId());
		System.out.println("compteur id:= "+token.getCompteur().getId());
		System.out.println("user phone:= "+token.getCompteur().getUser().getTelephone());
		System.out.println("typetoken id:= "+token.getTypetoken().getId());
		token.setNumerotoken(tokenService.genererNumeroToken(token.getId(),token.getTypetoken().getId(),token.getCompteur().getUser().getTelephone(),token.getCompteur().getId()));
		tokenService.updateToken(token.getId(), token);
		System.out.println("new token:= "+token.toString());
		//vreation du panier
		panier.setToken(token);
		panier.setUser(token.getUser());
		panier.setActive(true);
		panierService.addPanier(panier);
		
		
	}
	
}
