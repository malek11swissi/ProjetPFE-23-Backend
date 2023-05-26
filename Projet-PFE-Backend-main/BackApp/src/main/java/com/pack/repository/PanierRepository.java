package com.pack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.Panier;

@RestController
public interface PanierRepository extends JpaRepository<Panier, Long> {
	@Query("FROM Panier where user.username = ?1")
//	@Query("FROM Compteur where user.username = 'dha'")
	List<Panier> getPaniersByUsername(String Username);

}
