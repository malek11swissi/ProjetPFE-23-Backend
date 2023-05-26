package com.pack.controllers;

import java.util.ArrayList;
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

import com.pack.models.ERole;
import com.pack.models.Ticket;
import com.pack.models.Token;
import com.pack.models.TransfertSolde;
import com.pack.models.Typetoken;
import com.pack.repository.CarteRechargeRepository;
import com.pack.repository.UserRepository;
import com.pack.service.CarteRechargeService;
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
	private CarteRechargeService carteRechargeService;

	

	@PreAuthorize("hasRole('ROLE_MODERATOR')")

	@RequestMapping(method = RequestMethod.GET, value = "/tickets")
	public List<Ticket> getTicket() {
		ticketService.getAllTicket().forEach(t -> {
			System.out.println(t.toString());
		});
		return (List<Ticket>) ticketService.getAllTicket();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/tickets/{username}/{serial}")
	public void addTicket(@PathVariable String username, @PathVariable long serial, @RequestBody Ticket ticket) {
//	public int getTokenByUser(@PathVariable Long iduser) {
		System.out.println("username:= " + username);
		System.out.println("serial:= " + serial);
		ticket.setUser(userservice.getUser(username));
		ticket.setNumeroSerie(serial);
		System.out.println("ticket" + ticket.toString());
		System.out.println("je suis dans ajout ticket");
		carteRechargeService.updateCarteRechargeBycentreName(username, ticket.getTypetoken().getPrix());

		/*
		 * Randomize randomize=new Randomize();
		 * 
		 * ticket.setNumeroSerie(randomize.retournerrandomserialnumber());
		 */
		System.out.println(ticket.toString());

		ticketService.addTicket(ticket);
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
		Randomize randomize = new Randomize();

		ticket.setNumeroSerie(randomize.retournerrandomserialnumber());
		System.out.println(ticket.toString());

		ticketService.addTicket(ticket);
	}
	@RequestMapping(method = RequestMethod.GET,  value = "/ticketsByUsername/{username}")
	public List<Ticket> getTicketByUsername(@PathVariable String username) {
		System.out.println(username);
		List<Ticket> tickets=new ArrayList<>();
		System.out.println("je suis dans liste listeTicket");
		ticketService.getAllTicket().forEach(t->{
			if(t.getUser().getUsername().equals(username))
				tickets.add(t);
		});
		tickets.forEach(t->{
			System.out.println(t.toString());
		});
		return tickets;
	/*
	 * @RequestMapping("/tickets/{id}") public Optional<Ticket>
	 * getSingleTicket(@PathVariable Long id) { return
	 * ticketService.getSingleTicket(id); }
	 * 
	 * @RequestMapping(method = RequestMethod.PUT, value = "/tickets/{id}") public
	 * void updateTicket(@RequestBody Ticket ticket, @PathVariable Long id) {
	 * System.out.println(ticket.toString()); ticketService.updateTicket(id,
	 * ticket); }
	 */
	}
}
