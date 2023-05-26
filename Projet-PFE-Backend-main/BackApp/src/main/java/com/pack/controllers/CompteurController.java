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
public class CompteurController {

	@Autowired
	CompteurService compteurService;

	@Autowired
	UserService userService;
	@Autowired
	private UserRepository userRepo;

	// @RequestMapping("/compteurs")
//	@PreAuthorize("hasRole('ADMIN')")
	@PreAuthorize("hasRole('ROLE_USER')")

	@RequestMapping(method = RequestMethod.GET, value = "/compteurs")
	public List<Compteur> getCompteur() {
		return (List<Compteur>) compteurService.getAllCompteur();

	}

	@RequestMapping(method = RequestMethod.GET, value = "/compteurByUsers")
	public List<Compteur> getCompteurByUser() {
		System.out.println("je suis dans compteur By User");
		return (List<Compteur>) compteurService.getAllCompteur();

	}

	@RequestMapping(method = RequestMethod.POST, value = "/compteurs")
	public void addCompteur(@RequestBody Compteur compteur) {
		System.out.println("addCompteur");
		System.out.println("compteur added "+compteur.toString());
		compteurService.addCompteur(compteur);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/compteurs//{username}")
	public void addCompteur(@RequestBody Compteur compteur, @PathVariable String username) {
		// System.out.println(compteur.toString());
		System.out.println("here");
		System.out.println("username:= " + username);
		System.out.println("libelle:= " + compteur.getLibelle());
		//System.out.println("user:= "+userRepo.findByUsername(username).toString());
		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		compteur.setUser(user);
		System.out.println("compteur to add "+compteur.toString());
		compteurService.addCompteur(compteur);
	}

	/*
	 * @RequestMapping(method = RequestMethod.GET, value =
	 * "/tokensByUser/{username}") public List<Token> getTokenByUser(@PathVariable
	 * String username) { // public int getTokenByUser(@PathVariable Long iduser) {
	 * System.out.println("username:= " + username); return
	 * tokenService.getTokensByUser(username); }
	 */


	

	/*
	 * @RequestMapping(method = RequestMethod.PUT, value = "/compteurs/{id}") public
	 * void updateCompteur(@RequestBody Compteur compteur, @PathVariable Long id) {
	 * System.out.println(compteur.toString()); compteurService.updateCompteur(id,
	 * compteur); }
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/compteurs/{id}")
	public void deleteCompteur(@PathVariable Long id) {
		compteurService.deleteCompteur(id);
	}

	

}