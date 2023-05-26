package com.pack.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.ERole;
import com.pack.models.Token;
import com.pack.models.Typetoken;
import com.pack.models.User;
import com.pack.repository.CompteurRepository;
import com.pack.repository.UserRepository;
import com.pack.models.Compteur;
import com.pack.service.CompteurService;
import com.pack.service.UserService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class CompteurLoggedUserController {

	@Autowired
	CompteurService compteurService;

	@Autowired
	UserService userService;
	@Autowired
	private UserRepository userRepo;

	// @RequestMapping("/compteurs")
//	@PreAuthorize("hasRole('ADMIN')")
	@PreAuthorize("hasRole('ROLE_USER')")

	
	/*
	 * @RequestMapping(method = RequestMethod.GET, value = "/compteurLoggedUsers")
	 * public List<Compteur> getCompteurLoggedUser() { return (List<Compteur>)
	 * compteurService.getAllCompteur();
	 * 
	 * }
	 */	 
	@RequestMapping(method = RequestMethod.PUT, value = "/compteurLoggedUsers/{id}")
	public void updateCompteur(@RequestBody Compteur compteur, @PathVariable Long id) {
		System.out.println("je suis dans update logged user");
		System.out.println(compteur.toString());
		compteurService.updateCompteur(id, compteur);
	}
	@RequestMapping(method = RequestMethod.DELETE, value = "/compteurLoggedUsers/{id}")
	public void deleteCompteur(@PathVariable Long id) {
		System.out.println("je suis dans suppression du compteur");
		compteurService.deleteCompteur(id);
	}
	@RequestMapping(method = RequestMethod.GET, value="/compteurofUserx/{id}")
	public Optional<Compteur> getCompteurById(@PathVariable Long id) {
		System.out.println("je suis dans get Compteur");
		return compteurService.getSingleCompteur(id);
	}
	@RequestMapping(method = RequestMethod.GET, value="/compteurLoggedUsers/{username}")
	public List<Compteur> getListCompteur(@PathVariable String username) {
		System.out.println("username " + username);
		return compteurService.getCompteursByUser(username);
	}
	/*
	 * @RequestMapping(method = RequestMethod.GET, value = "/compteurs/{username}")
	 * public List<Compteur> getCompteurByUser(@PathVariable String username) { //
	 * public int getCompteurByUser(@PathVariable Long iduser) {
	 * System.out.println("username:= " + username); return
	 * compteurService.getCompteursByUser(username); }
	 */

}
