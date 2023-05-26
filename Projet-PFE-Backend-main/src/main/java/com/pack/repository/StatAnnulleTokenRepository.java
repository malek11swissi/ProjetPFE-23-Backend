package com.pack.repository;

import java.util.ArrayList;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.StatAnnulleToken;
import com.pack.models.StatistiqueMensuel;
import com.pack.models.Typetoken;


@RestController
public interface StatAnnulleTokenRepository extends JpaRepository<StatAnnulleToken, Long> {

	@Query("FROM StatAnnulleToken WHERE typeToken = ?1 and annee=?2")
	StatAnnulleToken findByTypeTokenAnnee(String typeToken, int annee);

	@Query("FROM StatAnnulleToken order by annee ")
	ArrayList<StatAnnulleToken> findAllTrie();

	boolean  existsByTypeTokenAndAnnee(String typeToken , int annee);

}
