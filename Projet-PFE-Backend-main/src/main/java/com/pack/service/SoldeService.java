package com.pack.service;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.pack.models.Compteur;
import com.pack.models.Panier;
import com.pack.models.Solde;
import com.pack.models.SoldeTransfertRequest;
import com.pack.models.User;
import com.pack.payload.response.PaiementRetour;
import com.pack.repository.SoldeRepository;
import com.pack.repository.UserRepository;


@Component
public class SoldeService {


	@Autowired
	UserService userService ; 

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private SoldeRepository soldeRepo;
	
	/* Gérer solde  */
   
	public List<Solde> getSoldesByUser(String username) {
		return soldeRepo.getSoldesByUsername(username);
	}

     // ajouter solde 
	public void addSolde(Solde solde) {
		soldeRepo.save(solde);
	}






    //Verfication solde existe  pour un panier
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
		// transaction true 
		if(soldeUser>=montantPanier) {
			transaction=true;
		}
		return transaction;
	}


	//debiter le solde pour un panier
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


















	public List<Solde> getAllSolde() {
		return soldeRepo.findAll();
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


	public Solde getSoldesByTelephone(String telephone) {
		return soldeRepo.getSoldesByTelephone(telephone);
	}

	

	

	
}
