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

import com.pack.models.Compteur;
import com.pack.models.ERole;
import com.pack.models.Solde;
import com.pack.service.SoldeService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class SoldeController {

	@Autowired
	SoldeService soldeService;

	// @RequestMapping("/soldes")
	// @PreAuthorize("hasRole('ADMIN')")
	@PreAuthorize("hasRole('ROLE_USER')")

	@RequestMapping(method = RequestMethod.GET, value = "/soldes")
	public List<Solde> getSolde()

	{
		soldeService.getAllSolde().forEach(t -> {
			System.out.println(t.toString());
		});
		return (List<Solde>) soldeService.getAllSolde();

	}

	@RequestMapping(method = RequestMethod.POST, value = "/soldes")
	public void addSolde(@RequestBody Solde solde) {
		System.out.println(solde.toString());
		soldeService.addSolde(solde);
	}

	@RequestMapping("/soldes/{id}")
	public Optional<Solde> getSingleSolde(@PathVariable Long id) {
		return soldeService.getSingleSolde(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/soldes/{id}")
	public void updateSolde(@RequestBody Solde solde, @PathVariable Long id) {
		System.out.println(solde.toString());
		soldeService.updateSolde(id, solde);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/soldes/{id}")
	public void deleteSolde(@PathVariable Long id) {
		soldeService.deleteSolde(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/soldes/{username}/{listSolde}")
	public List<Solde> getSoldeByUser(@PathVariable String username) { // public
		// int getCompteurByUser(@PathVariable Long iduser) {
		/*
		 * System.out.println("username:= " + username);
		 * System.out.println("solde de l'utilistauer");
		 * soldeService.getSoldesByUser(username).forEach(p -> {
		 * System.out.println(p.toString()); });
		 */
		return soldeService.getSoldesByUser(username);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/soldes/{utilisateur}")
	public Solde getSoldeOfUser(@PathVariable String utilisateur, @PathVariable Boolean listSolde) {
//	public int getCompteurByUser(@PathVariable Long iduser) {
		System.out.println("je suis dans solde de centre de recharge");
		System.out.println("username:= " + utilisateur);
		System.out.println("solde de l'utilistauer");
		soldeService.getSoldesByUser(utilisateur).forEach(p -> {
			System.out.println(p.toString());
		});
		return soldeService.getSoldesByUser(utilisateur).get(0);

	}

}
