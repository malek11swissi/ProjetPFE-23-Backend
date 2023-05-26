package com.pack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.Compteur;
import com.pack.models.User;


@RestController
public interface CompteurRepository extends JpaRepository<Compteur, Long> {
	
	@Modifying
//	@Query("FROM Compteur")
	//@Query("delete from Intervenant u where u.idIntervenant = ?1")

	@Query("FROM Compteur where user.username = ?1")
//	@Query("FROM Compteur where user.username = 'dha'")
	List<Compteur> getCompteursByUsername(String Username);
	
	/*
	 * @Query("FROM Users where user.username = ?1")
	 * // @Query("FROM Compteur where user.username = 'dha'") User
	 * getUserFromUsername(String Username);
	 */
	

}
