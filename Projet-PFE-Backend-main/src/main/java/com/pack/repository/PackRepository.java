package com.pack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;


import com.pack.models.Pack;
import com.pack.models.Panier;

@RestController
public interface PackRepository extends JpaRepository<Pack, Long> {
	
        List<Pack> getPackByUserId(Long userId);
}
