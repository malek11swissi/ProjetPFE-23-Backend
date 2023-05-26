package com.pack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.Token;
import com.pack.models.Token;


@RestController
public interface TokenRepository extends JpaRepository<Token, Long> {
	@Query("FROM Token where user.username = ?1")
//	@Query("FROM Token where user.username = 'dha'")
	List<Token> getTokensByUsername(String Username);

}
