package com.pack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.CarteRecharge;
import com.pack.models.CentreRecharge;
import com.pack.models.Panier;

@RestController
public interface CarteRechargeRepository extends JpaRepository<CarteRecharge, Long> {
	@Query("FROM CarteRecharge where user.username = ?1")
//	@Query("FROM Compteur where user.username = 'dha'")
	List<CarteRecharge> getCarteRechargesByCentreRecharge(String centreRechargeusername);

}
