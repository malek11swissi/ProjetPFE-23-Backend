package com.pack.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.ERole;
import com.pack.models.Pack;
import com.pack.models.Ticket;
import com.pack.models.Token;

import com.pack.models.Typetoken;
import com.pack.payload.response.PaiementRetour;
import com.pack.repository.PackRepository;
import com.pack.repository.UserRepository;
import com.pack.service.PackService;
import com.pack.service.PanierService;
import com.pack.service.TicketService;
import com.pack.service.UserService;
import com.pack.Randomize;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class TicketController {
	Typetoken typetoken;

	@Autowired
	TicketService ticketService;
	@Autowired
	private UserService userservice;
	@Autowired
	PanierService panierService;

	@Autowired
	private PackService packService;

	@Autowired
	PackRepository packRepository ;
	

	@PreAuthorize("hasRole('ROLE_MARCHAND')")



	@RequestMapping(method = RequestMethod.GET, value = "/tickets")
	public List<Ticket> getTicket() {
		ticketService.getAllTicket().forEach(t -> {
			System.out.println(t.toString());
		});
		return (List<Ticket>) ticketService.getAllTicket();
	}



// Crée Ticket  et Modifier Pack 
	@RequestMapping(method = RequestMethod.POST, value = "/tickets/addTicket/{serial}")
	public PaiementRetour  addTicket( @PathVariable long serial, @RequestBody Ticket ticket , Authentication authentication) {
		
		PaiementRetour paiementRetour = new PaiementRetour();
		// get Marchands Packs 
		List<Pack> packs = packService.getPacksByMarchand(authentication);
		// trouver le pack coresspondant au ticket et diminuer le nombre 
		Pack packTicketSelled = packs.stream().filter(elem -> elem.getTypetoken().getId() == ticket.getTypetoken().getId()).findFirst().get();
		if(packTicketSelled.getNombre() == 0)
		{
			paiementRetour.setSuccess(false);
			paiementRetour.setMessage("Vente ticket a échoué , Nombre typetoken issufisant");
			return paiementRetour;
		} else {
			String username = authentication.getName();
			System.out.println("username:= " + username);
			System.out.println("serial:= " + serial);
			ticket.setUser(userservice.getUserByUsername(username));
			ticket.setNumeroSerie(serial);
			System.out.println("ticket" + ticket.toString());
			System.out.println("je suis dans ajout ticket");
			//carteRechargeService.updateCarteRechargeBycentreName(username, ticket.getTypetoken().getPrix());
			System.out.println(ticket.toString());
			Ticket ticketSaved = ticketService.addTicket(ticket);
				packTicketSelled.setNombre(packTicketSelled.getNombre() - 1);
			packRepository.save(packTicketSelled);
			paiementRetour.setSuccess(true);
			paiementRetour.setMessage("Ticket vendu avec succès");
			return paiementRetour;
		}

		
		

		// 
	}

	@RequestMapping(method = RequestMethod.GET, value = "/tickets/{serial}")
	public Typetoken getTypetokenfromSerial(@PathVariable long serial) {
		System.out.println("je suis dans get typetoken");
		System.out.println("serial:= " + serial);
		ticketService.getAllTicket().forEach(t -> {
			if (t.getNumeroSerie() == serial) {
				typetoken = t.getTypetoken();
			}
		});
		System.out.println(typetoken.toString());
		return typetoken;
	}




	@RequestMapping(method = RequestMethod.POST, value = "/tickets")
	public void addTicket(@RequestBody Ticket ticket) {
		System.out.println("je suis dans ajout ticket");

		// format numero serie plus court 
		Randomize randomize = new Randomize();
         ticket.setNumeroSerie(randomize.retournerrandomserialnumber());
		System.out.println(ticket.toString());
         ticketService.addTicket(ticket);
}



	@GetMapping( value = "/tickets/getTicketsMarchand")
	public List<Ticket> getTicketByUsername(Authentication authentication) {
	return ticketService.getTicketsMarchand(authentication);
	
	}
}
