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
import com.pack.models.Commande;
import com.pack.service.CommandeService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class CommandeController {

	@Autowired
	CommandeService commandeService;


	@PreAuthorize("hasRole('ROLE_ADMIN')")
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/commandes") 
	public List<Commande> getCommande() 
	
	{
		commandeService.getAllCommande().forEach(t->{
			System.out.println(t.toString());
		});
		return (List<Commande>) commandeService.getAllCommande();

	}

	


	@RequestMapping("/commandes/{id}")
	public Optional<Commande> getSingleCommande(@PathVariable Long id) {
		return commandeService.getSingleCommande(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/commandes/{id}")
	public void updateCommande(@RequestBody Commande commande, @PathVariable Long id) {
		System.out.println(commande.toString());
		commandeService.updateCommande(id, commande);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/commandes/{id}")
	public void deleteCommande(@PathVariable Long id) {
		commandeService.deleteCommande(id);
	}
	

}
