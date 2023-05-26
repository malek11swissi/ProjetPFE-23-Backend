package com.pack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.Ticket;
import com.pack.models.Ticket;


@RestController
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
}
