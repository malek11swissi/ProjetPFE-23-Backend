package com.pack.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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
	@PreAuthorize("hasRole('ROLE_CLIENT')")

	@RequestMapping(method = RequestMethod.GET, value = "/compteurs")
	public List<Compteur> getCompteur() {
		return (List<Compteur>) compteurService.getAllCompteur();

	}

	@GetMapping( value = "/compteurs/getCompteurByClient")
	public List<Compteur> getCompteurByClient(Authentication authentication) {
		return  compteurService.getCompteursByClient(authentication);

	}

	@PostMapping( value = "/compteurs/saveCompteur")
	public ResponseEntity<Compteur> saveCompteur(@RequestBody Compteur compteur, Authentication authentication) {
		return compteurService.saveCompteur(compteur,authentication);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/compteurs/deleteCompteur/{id}")
	public void deleteCompteur(@PathVariable Long id) {
		compteurService.deleteCompteur(id);
	}

	@GetMapping( value="/compteurs/getCompteur/{id}")
	public Optional<Compteur> getCompteurById(@PathVariable Long id) {
		return compteurService.getSingleCompteur(id);
	}

}