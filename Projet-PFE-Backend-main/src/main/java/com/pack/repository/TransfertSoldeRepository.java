package com.pack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.Panier;
import com.pack.models.Token;
import com.pack.models.TransfertSolde;

@RestController
public interface TransfertSoldeRepository extends JpaRepository<TransfertSolde, Long> {
	
	  @Query("FROM TransfertSolde where user.username = ?1")
	  // @Query("FROM Compteur where user.username = 'dha'") List<TransfertSolde>
		List<TransfertSolde> getTransfertSoldeByUsername(String Username);

	 
}
