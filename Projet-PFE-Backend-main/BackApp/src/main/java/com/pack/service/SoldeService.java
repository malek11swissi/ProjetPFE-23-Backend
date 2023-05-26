package com.pack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pack.models.Compteur;
import com.pack.models.Panier;
import com.pack.models.Solde;
import com.pack.models.User;
import com.pack.repository.SoldeRepository;


@Component
public class SoldeService {

	@Autowired
	private SoldeRepository soldeRepo;
	
	
	public List<Solde> getAllSolde() {
		return soldeRepo.findAll();
	}
	
	public void addSolde(Solde solde) {
		soldeRepo.save(solde);
	}
	
	public Optional<Solde> getSingleSolde(Long id) {
		return soldeRepo.findById(id);
	}
	
	public void updateSolde(Long id, Solde solde) {
		soldeRepo.save(solde);
	}
	
	public void deleteSolde(Long id) {
		soldeRepo.deleteById(id);
	}
	public List<Solde> getSoldesByUser(String username) {
		return soldeRepo.getSoldesByUsername(username);
	}

	public Solde getSoldesByTelephone(String telephone) {
		return soldeRepo.getSoldesByTelephone(telephone);
	}

	
	public Boolean verifierSolde(Panier panier) {
		double soldeUser=0,montantPanier=0;
		Boolean transaction=false;
		long idUser;
		List<Solde> listeSoldes=soldeRepo.findAll();
		idUser=panier.getUser().getId();
		montantPanier=panier.getToken().getTypetoken().getPrix();
		for(Solde s:listeSoldes) {
			if(s.getUser().getId()==idUser) {
				soldeUser=s.getValeur();
			}
		}
		System.out.println("solde client "+soldeUser);
		if(soldeUser>=montantPanier) {
			transaction=true;
		}
		return transaction;
	}
	public void soustraire(Panier panier) {
		double soldeUser=0,montantPanier=0,nouveauSolde=0;
		long idUser;
		List<Solde> listeSoldes=soldeRepo.findAll();
		idUser=panier.getUser().getId();
		montantPanier=panier.getToken().getTypetoken().getPrix();
		for(Solde s:listeSoldes) {
			if(s.getUser().getId()==idUser) {
				soldeUser=s.getValeur();
				nouveauSolde=soldeUser-montantPanier;
				s.setValeur(nouveauSolde);
				soldeRepo.save(s);
			}
		}
		
		
	}

	
}
