package com.pack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.Solde;

@RestController
public interface SoldeRepository extends JpaRepository<Solde, Long> {
	@Query("FROM Solde where user.username = ?1")
//	@Query("FROM Compteur where user.username = 'dha'")
	List<Solde> getSoldesByUsername(String Username);

	@Query("FROM Solde where user.telephone = ?1")
//	@Query("FROM Compteur where user.username = 'dha'")
	Solde getSoldesByTelephone(String telephone);

	Optional<Solde> findByUserId(Long userId);
}
