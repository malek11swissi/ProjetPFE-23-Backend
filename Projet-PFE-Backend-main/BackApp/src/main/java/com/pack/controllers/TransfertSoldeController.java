package com.pack.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
import com.pack.models.Solde;
import com.pack.models.TransfertSolde;
import com.pack.models.User;
import com.pack.repository.SoldeRepository;
import com.pack.repository.TransfertSoldeRepository;
import com.pack.repository.UserRepository;
import com.pack.models.StatAnnulleToken;
import com.pack.models.StatistiqueAnnuel;
import com.pack.models.StatistiqueMensuel;
import com.pack.models.Token;
import com.pack.service.CommandeService;
import com.pack.service.RechargeService;
import com.pack.service.TransfertSoldeService;
import com.pack.service.UserService;
import com.pack.service.SoldeService;
import com.pack.service.StatistiqueAnnuelTokenService;
import com.pack.service.StatistiqueMensuelDetailsService;
import com.pack.service.StatistiqueMensuelService;
import com.pack.service.TokenService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class TransfertSoldeController {

	@Autowired
	TransfertSoldeService transfertSoldeService;
	@Autowired
	TransfertSoldeRepository transfertSolderepo;
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
	@Autowired
	UserRepository userrepo;
	@Autowired
	SoldeRepository solderepo;
	@Autowired
	UserService userservice;
	

	@Autowired
	RechargeService rechargeService;

	// @RequestMapping("/transfertSoldes")
	// @PreAuthorize("hasRole('ADMIN')")
	@PreAuthorize("hasRole('ROLE_USER')")

	/*
	 * @RequestMapping(method = RequestMethod.GET, value =
	 * "/transfertSoldes/{username}") public List<TransfertSolde>
	 * getTransfertSolde(@RequestBody TransfertSolde transfertSolde, @PathVariable
	 * String username) { transfertSoldeService.getAllTransfertSolde().forEach(t ->
	 * { System.out.println(t.toString()); }); return (List<TransfertSolde>)
	 * transfertSoldeService.getAllTransfertSolde(); }
	 */

	@RequestMapping(method = RequestMethod.POST, value = "/transfertSoldes")
	public void addTransfertSolde(@RequestBody TransfertSolde transfertSolde,Authentication authentication) {
		String username = authentication.getName();
	
	
		String telephone=transfertSolde.getTelephone();
		User sender=null,receiver = null;
		sender=userservice.getUser(username);

		if(userrepo.existsByTelephone(telephone)){
			receiver = userrepo.findByTelephone(telephone)
					.orElseThrow(() -> new UsernameNotFoundException("user Not Found with telephone: " + telephone));
		}
		transfertSolde.setUser(sender);
	
		Solde soldeSender = solderepo.findByUserId(sender.getId()).get();
		Solde soldeReceiver = solderepo.findByUserId(receiver.getId()).get();
		System.out.println(" solde sender " + soldeSender);
		double valSender = soldeSender.getValeur();
		double valReceiver = soldeReceiver.getValeur();
	
		if(valSender >= transfertSolde.getSomme())
		{
			soldeSender.setValeur(valSender - transfertSolde.getSomme());	
			soldeReceiver.setValeur(valReceiver + transfertSolde.getSomme());
			solderepo.save(soldeSender);
		} else {
				System.out.println("solde isuffisant ");
		}
		transfertSoldeService.addTransfertSolde(transfertSolde);
	}

	@RequestMapping(method = RequestMethod.GET,  value = "/transfertSoldes/{username}")
	public List<TransfertSolde> getTransfertSoldeByUsername(@PathVariable String username) {
		System.out.println(username);
		List<TransfertSolde> transfertsoldes=new ArrayList<>();
		System.out.println("je suis dans liste listetransferts");
		transfertSolderepo.findAll().forEach(t->{
			if(t.getUser().getUsername().equals(username))
				transfertsoldes.add(t);
		});
		transfertsoldes.forEach(t->{
			System.out.println(t.toString());
		});
		return transfertsoldes;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/transfertSoldes/{id}")
	public void updateTransfertSolde(@RequestBody TransfertSolde transfertSolde, @PathVariable Long id) {
		System.out.println(transfertSolde.toString());
		transfertSoldeService.updateTransfertSolde(id, transfertSolde);
	}
	
	/*
	 * @RequestMapping(method = RequestMethod.DELETE, value = "/transfertSoldes/{id}")
	 * public void deleteTransfertSolde(@PathVariable Long id) {
	 * transfertSoldeService.deleteTransfertSolde(id); }
	 */
	/*
	 * @RequestMapping(method = RequestMethod.DELETE, value =
	 * "/transfertSoldes/{id}") public void payerTransfertSolde(@PathVariable Long
	 * id) {
	 * 
	 * Commande commande = new Commande(); StatistiqueAnnuel statistiqueAnnuel=new
	 * StatistiqueAnnuel(); StatistiqueMensuel statistiqueMensuel=new
	 * StatistiqueMensuel(); StatAnnulleToken statistiquetoken=new
	 * StatAnnulleToken(); double montantTransfertSolde = 0; long id_token; Date
	 * date = new Date(); int annee,mois,nb_mois,nb_annee; long idstatAnne; Boolean
	 * anneeExist=false,moisExist=false; TransfertSolde transfertSolde = new
	 * TransfertSolde(); Token token=new Token();
	 * System.out.println("I'm here dans paiement");
	 * System.out.println("idTransfertSolde:= " + id); // Création commande
	 * transfertSolde = transfertSoldeService.getTransfertSoldeById(id);
	 * token=transfertSolde.getToken(); id_token=token.getId(); String type_token=
	 * transfertSolde.getToken().getTypetoken().getNom(); //extraction annee et mois
	 * ConvertDate c = new ConvertDate(); annee=c.retournerAnnee(date);
	 * mois=c.retournerMois(date);
	 * System.out.println("annee:= "+annee+" mois:= "+mois );
	 * 
	 * 
	 * // verifier solde if (soldeService.verifierSolde(transfertSolde)) {
	 * //insertion statistiques System.out.println("on commence statistiques");
	 * //statistiqueAnnuelService.ajouterStatAnnuel(date);
	 * //statistiqueMensuelService.ajouterStatMensuel(date);
	 * statistiqueMensuelDetailsService.ajouterStatMensuelDetails(date);
	 * 
	 * 
	 * 
	 * System.out.println("transfertSolde à payer " + transfertSolde.toString());
	 * commande.setDate(convertDate.cenvertirDate(date));
	 * commande.setTransfertSolde(transfertSolde);
	 * commande.setUser(transfertSolde.getUser());
	 * System.out.println("nouvelle commande");
	 * System.out.println(commande.toString());
	 * commandeService.addCommande(commande); transfertSolde.setActive(false);
	 * token.setActive(false);
	 * transfertSoldeService.addTransfertSolde(transfertSolde);
	 * soldeService.soustraire(transfertSolde); //suppression du token
	 * tokenService.updateToken(id_token, token);
	 * System.out.println("**********************Type Token "+ type_token);
	 * statistiqueAnnuelServiceToken.ajouterStatAnnuel(date, type_token); } else {
	 * System.out.println("solde insuffisant"); } // soustraire montant //
	 * montantTransfertSolde=transfertSoldeService.retournermontantTransfertSolde(
	 * transfertSolde); System.out.println("montant TransfertSolde:= " +
	 * montantTransfertSolde);
	 * 
	 * }
	 */
	/*
	 * @RequestMapping(method = RequestMethod.GET, value =
	 * "/transfertSoldes/{username}") public List<TransfertSolde>
	 * getTransfertSoldeByUser(@PathVariable String username) { // public int
	 * getCompteurByUser(@PathVariable Long iduser) {
	 * System.out.println("username:= " + username);
	 * System.out.println("transfertSolde de l'utilisateur");
	 * transfertSoldeService.getTransfertSoldesByUser(username).forEach(p -> {
	 * System.out.println(p.toString()); }); return
	 * transfertSoldeService.getTransfertSoldesByUser(username);
	 * 
	 * }
	 */
}
