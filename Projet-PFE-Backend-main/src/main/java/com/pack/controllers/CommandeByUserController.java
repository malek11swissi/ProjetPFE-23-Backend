package com.pack.controllers;

import java.util.Date;
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
import com.pack.ConvertDate;
import com.pack.models.Commande;
import com.pack.service.CommandeService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class CommandeByUserController {

	@Autowired
	CommandeService commandeService;


	@PreAuthorize("hasRole('ROLE_CLIENT')")

	@RequestMapping(method = RequestMethod.POST, value = "/commandes")
	public void addCommande(@RequestBody Commande commande) {
		System.out.println(commande.toString());
		commandeService.addCommande(commande);
	}


	// list commnade By User

	@RequestMapping(method = RequestMethod.GET, value = "/commandes/{username}")
	public List<Commande> getCommandeByUser(@PathVariable String username) {
		ConvertDate con = new ConvertDate();
		System.out.println("username:= " + username);
		System.out.println("commande de l'utilisateur");
		commandeService.getCommandesByUser(username).forEach(p->{
			System.out.println(p.toString());
		});
            // Année du commande By user 
		System.out.println("commande par Année");
		commandeService.getCommandesByUser(username).forEach(c->{
			System.out.println(con.extraireAnnee(c.getDate()));
		});
		//   Mois du commande
		System.out.println("commande par Mois");
		commandeService.getCommandesByUser(username).forEach(c->{
			System.out.println(con.extraireMois(c.getDate()));
		});
		return commandeService.getCommandesByUser(username);

	}

}
