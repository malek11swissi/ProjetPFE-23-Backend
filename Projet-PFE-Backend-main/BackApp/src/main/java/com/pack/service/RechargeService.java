package com.pack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.pack.models.Bonus;
import com.pack.models.Compteur;
import com.pack.models.ERole;
import com.pack.models.Panier;
import com.pack.models.Recharge;
import com.pack.models.Rechargeform;
import com.pack.models.Role;
import com.pack.models.Solde;
import com.pack.models.User;
import com.pack.repository.BonusRepository;
import com.pack.repository.CentreRechargeRepository;
import com.pack.repository.RechargeRepository;
import com.pack.repository.SoldeRepository;
import com.pack.repository.UserRepository;

@Component
public class RechargeService {

	@Autowired
	private RechargeRepository rechargeRepo;
	@Autowired
	private SoldeRepository soldeRepo;
	@Autowired
	UserRepository userrepo;
	@Autowired
	private CentreRechargeRepository centrerechargeRepo;
	@Autowired
	private BonusRepository bonusRepo;

	public List<Recharge> getAllRecharge() {
		return rechargeRepo.findAll();
	}

	public void addRecharge(Recharge recharge) {

		rechargeRepo.save(recharge);
	}

	public Optional<Recharge> getSingleRecharge(Long id) {
		return rechargeRepo.findById(id);
	}

	public void updateRecharge(Long id, Recharge recharge) {
		rechargeRepo.save(recharge);
	}

	public void deleteRecharge(Long id) {
		rechargeRepo.deleteById(id);
	}

	public void updateSolde(String telephone, double prix) {
		double newSolde = prix;
		Solde solde = soldeRepo.getSoldesByTelephone(telephone);
		if (solde != null) {
			System.out.println("ancien solde de telephone " + telephone + " est " + solde.toString());
			newSolde += solde.getValeur();
			solde.setValeur(newSolde);
			soldeRepo.save(solde);
		} else
			System.out.println("utilisateur inexistant");
	}

	public Boolean verifierRecharge(Rechargeform rechargeform) {
		Boolean ok = false;
		String telephone = "";
		double prix;
		System.out.println(rechargeform.toString());
		telephone = rechargeform.getTelephone();
		System.out.println("telephone exist " + userrepo.existsByTelephone(telephone));
		prix = rechargeform.getPrix();
		//verifier 
		
		// verifier telephone et montant
		if (prix <= 0 || prix > 50) {
			System.out.println("recharge impossible");
			System.out.println("montant errone");
		} else if (!userrepo.existsByTelephone(telephone)) {
			System.out.println("recharge impossible");
			System.out.println("numero telephone non valide");
		}
		// if (userrepo.existsByTelephone(telephone) && prix > 0 && prix<=50) {
		else {
			System.out.println(soldeRepo.getSoldesByTelephone(telephone));
			/*
			 * updateSolde(telephone, prix); addRecharge(recharge);
			 */
			ok = true;
		}
		return ok;
	}
	
	public Boolean verifierSoldeCentreRecharge(Rechargeform rechargeform,String username) {
		Solde solde = soldeRepo.getSoldesByUsername(username).get(0);
		Boolean ok = false;
		double prix;
		System.out.println(rechargeform.toString());
		prix = rechargeform.getPrix();
		// verifier telephone et montant
		if (solde.getValeur()<prix) {
			System.out.println("recharge impossible, solde centre recharge epuisé");
		}
		else
			ok = true;
		return ok;
	}

	public double ajouterBonus0(double montant) {
		double newmontant=montant;
		if(montant>=50)
			newmontant=montant*1.2;
		else
			if(montant>=20)
				newmontant=montant*1.1;

		System.out.println("newmontant:= "+newmontant);
		return newmontant;
	}
	
	public double ajouterBonus2(double montant) {
		double pourcentage=0;
		double newmontant=montant;
		for(Bonus b:bonusRepo.findAll()) {
			if(montant>=b.getMin()&&montant<b.getMax()) {
				pourcentage=b.getValeur();
			}
		}
		System.out.println("pourcentage:= "+pourcentage);
		newmontant=((montant*(pourcentage+100))/100);
		System.out.println("newmontant:= "+newmontant);
		return newmontant;
	}

	public void reduireSoldeCentreRecharge(String centreRechargeusername,double prix) {
		Solde solde = soldeRepo.getSoldesByUsername(centreRechargeusername).get(0);
		System.out.println("solde centre "+solde.toString());
		solde.setValeur(solde.getValeur()-prix);
		soldeRepo.save(solde);
		System.out.println("new solde "+solde.toString());
	}
}
