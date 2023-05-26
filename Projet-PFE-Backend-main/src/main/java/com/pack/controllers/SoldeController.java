package com.pack.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.pack.models.Solde;
import com.pack.models.SoldeTransfertRequest;
import com.pack.models.TransfertSolde;
import com.pack.models.User;
import com.pack.repository.SoldeRepository;
import com.pack.repository.UserRepository;
import com.pack.service.SoldeService;
import com.pack.service.TransfertSoldeService;
import com.pack.service.UserService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test/soldes")
@RestController
public class SoldeController {

	@Autowired
	SoldeService soldeService;

	@Autowired
	UserService userService ; 

	@Autowired
	UserRepository userRepo;

	@Autowired
	SoldeRepository solderepo ; 

	@Autowired
	TransfertSoldeService transfertSoldeService;
	


	// @RequestMapping("/soldes")
	// @PreAuthorize("hasRole('ADMIN')")
	@PreAuthorize("hasRole('ROLE_CLIENT' || 'ROLE_MARCHAND')")
	
//liste soldes 

	@RequestMapping(method = RequestMethod.GET, value = "/soldes")
	public List<Solde> getSolde()

	{
		soldeService.getAllSolde().forEach(t -> {
			System.out.println(t.toString());
		});
		return (List<Solde>) soldeService.getAllSolde();

	}

	

	@RequestMapping("/{id}")
	public Optional<Solde> getSingleSolde(@PathVariable Long id) {
		return soldeService.getSingleSolde(id);
	}



	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deleteSolde(@PathVariable Long id) {
		soldeService.deleteSolde(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{username}/{listSolde}")
	public List<Solde> getSoldeByUser(@PathVariable String username) { // public
	
		return soldeService.getSoldesByUser(username);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/{utilisateur}")
	public Solde getSoldeOfUser(@PathVariable String utilisateur, @PathVariable Boolean listSolde) {
		System.out.println("je suis dans solde de marchand");
		System.out.println("username:= " + utilisateur);
		System.out.println("solde de l'utilistauer");
		soldeService.getSoldesByUser(utilisateur).forEach(p -> {
			System.out.println(p.toString());
		});
		return soldeService.getSoldesByUser(utilisateur).get(0);

	}

	@PostMapping( value = "/transfertSolde")
	public void transfertSolde(@RequestBody SoldeTransfertRequest request,Authentication authentication) {
		String username = authentication.getName();
	
	
		String telephone=request.getTelephone();
		User sender=null,receiver = null;
		sender=userService.getUser(username);

		if(userRepo.existsByTelephone(telephone)){
			receiver = userRepo.findByTelephone(telephone)
					.orElseThrow(() -> new UsernameNotFoundException("user Not Found with telephone: " + telephone));
		}
	
	
		Solde soldeSender = solderepo.findByUserId(sender.getId()).get();
		Solde soldeReceiver = solderepo.findByUserId(receiver.getId()).get();
		System.out.println(" solde sender " + soldeSender);
		double valSender = soldeSender.getValeur();
		double valReceiver = soldeReceiver.getValeur();
	
		if(valSender >= request.getSomme())
		{
			soldeSender.setValeur(valSender - request.getSomme());	
			soldeReceiver.setValeur(valReceiver + request.getSomme());
			solderepo.save(soldeSender);
		} else {
				System.out.println("solde isuffisant ");
		}
		TransfertSolde transfertSolde = new TransfertSolde();
		transfertSolde.setUser(sender);
		transfertSolde.setTelephone(request.getTelephone());
		transfertSolde.setSomme(request.getSomme());
		transfertSoldeService.addTransfertSolde(transfertSolde);
	}
//alimenter solde 
	@PostMapping( value = "/alimenterCompte")
	public void alimenterCompte(@RequestBody double montant , Authentication authentication) {
		String username = authentication.getName();
		User user=null;
		user=userService.getUserByUsername(username);
		Solde monSolde= solderepo.findByUserId(user.getId()).get();
		double oldValue = monSolde.getValeur();

		monSolde.setValeur(oldValue + montant);	
		solderepo.save(monSolde);
	}


	@GetMapping(value = "/getCurrentSolde")
	public double getCurrentSolde(Authentication authentication)
	{
		String username = authentication.getName();
		User user=null;
		user=userRepo.findByUsername(username).get();
		Solde monSolde= solderepo.findByUserId(user.getId()).get();
		return monSolde.getValeur();

	}
}
