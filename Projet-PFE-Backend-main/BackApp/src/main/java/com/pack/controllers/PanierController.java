package com.pack.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pack.ConvertDate;
import com.pack.models.Commande;
import com.pack.models.Compteur;
import com.pack.models.ERole;
import com.pack.models.Panier;
import com.pack.models.StatAnnulleToken;
import com.pack.models.StatistiqueAnnuel;
import com.pack.models.StatistiqueMensuel;
import com.pack.models.Token;
import com.pack.service.CommandeService;
import com.pack.service.PanierService;
import com.pack.service.SoldeService;
import com.pack.service.StatistiqueAnnuelTokenService;
import com.pack.service.StatistiqueMensuelDetailsService;
import com.pack.service.StatistiqueMensuelService;
import com.pack.service.TokenService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class PanierController {

	@Autowired
	PanierService panierService;
	@Autowired
	CommandeService commandeService;
	@Autowired
	SoldeService soldeService;
	@Autowired
	ConvertDate convertDate;
	@Autowired
	TokenService tokenService;
	@Autowired
	StatistiqueAnnuelTokenService statistiqueAnnuelServiceToken;
	@Autowired
	StatistiqueMensuelService statistiqueMensuelService;
	@Autowired
	StatistiqueMensuelDetailsService statistiqueMensuelDetailsService;

	// @RequestMapping("/paniers")
	// @PreAuthorize("hasRole('ADMIN')")
	@PreAuthorize("hasRole('ROLE_USER')")

	@RequestMapping(method = RequestMethod.GET, value = "/paniers")
	public List<Panier> getPanier()

	{
		panierService.getAllPanier().forEach(t -> {
			System.out.println(t.toString());
		});
		return (List<Panier>) panierService.getAllPanier();

	}

	@RequestMapping(method = RequestMethod.POST, value = "/paniers")
	public void addPanier(@RequestBody Panier panier) {
		System.out.println(panier.toString());
		panierService.addPanier(panier);
	}

	@RequestMapping("/paniers/{id}")
	public Optional<Panier> getSinglePanier(@PathVariable Long id) {
		return panierService.getSinglePanier(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/paniers/{id}")
	public void updatePanier(@RequestBody Panier panier, @PathVariable Long id) {
		System.out.println(panier.toString());
		panierService.updatePanier(id, panier);
	}
	
	/*
	 * @RequestMapping(method = RequestMethod.DELETE, value = "/paniers/{id}")
	 * public void deletePanier(@PathVariable Long id) {
	 * panierService.deletePanier(id); }
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/paniers/{id}")
	public void payerPanier(@PathVariable Long id) {

		Commande commande = new Commande();
		StatistiqueAnnuel statistiqueAnnuel=new StatistiqueAnnuel();
		StatistiqueMensuel statistiqueMensuel=new StatistiqueMensuel();
		StatAnnulleToken statistiquetoken=new StatAnnulleToken();
		double montantPanier = 0;
		long id_token;
		Date date = new Date();
		int annee,mois,nb_mois,nb_annee;
		long idstatAnne;
		Boolean anneeExist=false,moisExist=false;
		Panier panier = new Panier();
		Token token=new Token();
		System.out.println("I'm here dans paiement");
		System.out.println("idPanier:= " + id);
		// Création commande
		panier = panierService.getPanierById(id);
		token=panier.getToken();
		id_token=token.getId();
		String type_token= panier.getToken().getTypetoken().getNom();
		//extraction annee et mois
		ConvertDate c = new ConvertDate();
		annee=c.retournerAnnee(date);
		mois=c.retournerMois(date);
		System.out.println("annee:= "+annee+" mois:= "+mois );
		
			
			// verifier solde
		if (soldeService.verifierSolde(panier)) {
			//insertion statistiques
			System.out.println("on commence statistiques");
			//statistiqueAnnuelService.ajouterStatAnnuel(date);
			//statistiqueMensuelService.ajouterStatMensuel(date);
			statistiqueMensuelDetailsService.ajouterStatMensuelDetails(date);
			
			
			
			System.out.println("panier à payer " + panier.toString());
			commande.setDate(convertDate.cenvertirDate(date));
			commande.setPanier(panier);
			commande.setUser(panier.getUser());
			System.out.println("nouvelle commande");
			System.out.println(commande.toString());
			commandeService.addCommande(commande);
			panier.setActive(false);
			token.setActive(false);
			panierService.addPanier(panier);
			soldeService.soustraire(panier);
			//suppression du token
			tokenService.updateToken(id_token, token);
			System.out.println("**********************Type Token "+ type_token);
			statistiqueAnnuelServiceToken.ajouterStatAnnuel(date, type_token);
		} 
		else {
			System.out.println("solde insuffisant");
		}
		// soustraire montant
		// montantPanier=panierService.retournermontantPanier(panier);
		System.out.println("montant Panier:= " + montantPanier);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/paniers/{username}")
	public List<Panier> getPanierByUser(@PathVariable String username) {
//	public int getCompteurByUser(@PathVariable Long iduser) {
		System.out.println("username:= " + username);
		System.out.println("panier de l'utilisateur");
		panierService.getPaniersByUser(username).forEach(p -> {
			System.out.println(p.toString());
		});
		return panierService.getPaniersByUser(username);

	}

}
