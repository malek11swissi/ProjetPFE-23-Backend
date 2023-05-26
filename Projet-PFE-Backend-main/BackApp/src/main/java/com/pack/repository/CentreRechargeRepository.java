package com.pack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.CentreRecharge;
import com.pack.models.CentreRecharge;


@RestController
public interface CentreRechargeRepository extends JpaRepository<CentreRecharge, Long> {
/*	@Query("FROM CentreRecharge where user.username = ?1")
//	@Query("FROM CentreRecharge where user.username = 'dha'")
	List<CentreRecharge> getCentreRechargesByUsername(String Username);
*/
}
