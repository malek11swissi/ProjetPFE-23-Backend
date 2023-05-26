package com.pack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.Commande;

@RestController
public interface CommandeRepository extends JpaRepository<Commande, Long> {
	@Query("FROM Commande where user.username = ?1")
//	@Query("FROM Compteur where user.username = 'dha'")
	List<Commande> getCommandesByUsername(String Username);

}
