package com.pack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.pack.models.Ticket;
import com.pack.models.User;
import com.pack.repository.TicketRepository;
import com.pack.models.Ticket;



@Component
public class TicketService {

	@Autowired
	private TicketRepository ticketRepo;
	
	@Autowired
	private UserService userService;
	
	public List<Ticket> getAllTicket() {
		return ticketRepo.findAll();
	}
	
	public void addTicket(Ticket ticket) {
		ticketRepo.save(ticket);
	}
	
	public Optional<Ticket> getSingleTicket(Long id) {
		return ticketRepo.findById(id);
	}
	
	public void updateTicket(Long id, Ticket ticket) {
		ticketRepo.save(ticket);
	}
	
	public void deleteTicket(Long id) {
		ticketRepo.deleteById(id);
	}
	public String genererNumeroTicket(Long idTicket, Long idTypeTicket, String telephone, Long idCompteur) {
		String numeroTicket;
		System.out.println("je suis dans generation num ticket");
		numeroTicket=idTicket+telephone+idTypeTicket+idCompteur;
		System.out.println("numeroticket:= "+numeroTicket);
		return numeroTicket;
	}

	public List<Ticket> getTicketsMarchand(Authentication authentication) {
		String username = authentication.getName();
		User marchand =userService.getUserByUsername(username);
		return ticketRepo.findAllByUserId(marchand.getId());
	}
	
}
