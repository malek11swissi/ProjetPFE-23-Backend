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
	
List<Compteur> findAllByUserId(Long userId);


	

	

}
