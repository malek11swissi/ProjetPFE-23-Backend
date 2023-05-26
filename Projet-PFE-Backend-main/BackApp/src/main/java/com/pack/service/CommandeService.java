package com.pack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pack.models.Compteur;
import com.pack.models.Commande;
import com.pack.repository.CommandeRepository;

@Component
public class CommandeService {

	@Autowired
	private CommandeRepository commandeRepo;

	public List<Commande> getAllCommande() {
		return commandeRepo.findAll();
	}

	public void addCommande(Commande commande) {
		commandeRepo.save(commande);
	}

	public Optional<Commande> getSingleCommande(Long id) {
		return commandeRepo.findById(id);
	}

	public void updateCommande(Long id, Commande commande) {
		commandeRepo.save(commande);
	}

	public void deleteCommande(Long id) {
		commandeRepo.deleteById(id);
	}

	public List<Commande> getCommandesByUser(String username) {
		/*
		 * commandeRepo.getCommandesByUsername(username).forEach(p -> { if
		 * (p.getActive()) { listeCommandesActives.add(p); } });
		 * System.out.println("affichage commande actifs");
		 * listeCommandesActives.forEach(p->{ System.out.println(p.toString()); });
		 */		
		return commandeRepo.getCommandesByUsername(username);
	}

}
