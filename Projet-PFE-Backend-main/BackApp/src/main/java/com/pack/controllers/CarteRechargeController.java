package com.pack.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pack.ConvertDate;
import com.pack.models.Commande;
import com.pack.models.Compteur;
import com.pack.models.ERole;
import com.pack.models.CarteRecharge;
import com.pack.models.StatAnnulleToken;
import com.pack.models.StatistiqueAnnuel;
import com.pack.models.StatistiqueMensuel;
import com.pack.models.Token;
import com.pack.service.CommandeService;
import com.pack.service.CarteRechargeService;
import com.pack.service.SoldeService;
import com.pack.service.StatistiqueAnnuelTokenService;
import com.pack.service.StatistiqueMensuelDetailsService;
import com.pack.service.StatistiqueMensuelService;
import com.pack.service.TokenService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class CarteRechargeController {

	@Autowired
	CarteRechargeService carteRechargeService;
	@Autowired
	CommandeService commandeService;
	@Autowired
	SoldeService soldeService;
	@Autowired
	ConvertDate convertDate;
	@Autowired
	TokenService tokenService;
	@Autowired
	StatistiqueAnnuelTokenService statistiqueAnnuelServiceToken;
	@Autowired
	StatistiqueMensuelService statistiqueMensuelService;
	@Autowired
	StatistiqueMensuelDetailsService statistiqueMensuelDetailsService;

	// @RequestMapping("/carteRecharges")
	// @PreAuthorize("hasRole('ADMIN')")
	@PreAuthorize("hasRole('ROLE_MODERATOR')")

	@RequestMapping(method = RequestMethod.GET, value = "/carteRecharges")
	public List<CarteRecharge> getCarteRecharge()

	{
		carteRechargeService.getAllCarteRecharge().forEach(t -> {
			System.out.println(t.toString());
		});
		return (List<CarteRecharge>) carteRechargeService.getAllCarteRecharge();

	}

	@RequestMapping(method = RequestMethod.POST, value = "/carteRecharges")
	public void addCarteRecharge(@RequestBody CarteRecharge carteRecharge) {
		System.out.println(carteRecharge.toString());
		carteRechargeService.addCarteRecharge(carteRecharge);
	}

	@RequestMapping("/carteRecharges/{id}")
	public Optional<CarteRecharge> getSingleCarteRecharge(@PathVariable Long id) {
		return carteRechargeService.getSingleCarteRecharge(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/carteRecharges/{id}")
	public void updateCarteRecharge(@RequestBody CarteRecharge carteRecharge, @PathVariable Long id) {
		System.out.println(carteRecharge.toString());
		carteRechargeService.updateCarteRecharge(id, carteRecharge);
	}

	/*
	 * @RequestMapping(method = RequestMethod.DELETE, value =
	 * "/carteRecharges/{id}") public void deleteCarteRecharge(@PathVariable Long
	 * id) { carteRechargeService.deleteCarteRecharge(id); }
	 */

	@RequestMapping(method = RequestMethod.GET, value = "/carteRecharges/{centreRechargeusername}")
	public List<CarteRecharge> getCarteRechargeByCentreRecharge(@PathVariable String centreRechargeusername) {
System.out.println("je suis dans getCarteRechargeByCentreRecharge");
System.out.println("centreRechargeusername:= "+centreRechargeusername);
List<CarteRecharge> listeCartes=new ArrayList<>();
listeCartes=carteRechargeService.getCarteRechargeBycentreName(centreRechargeusername);
System.out.println(listeCartes.size());
/*for(int i=0;i<listeCartes.size();i++)
	System.out.println(listeCartes.get(i).getTypetoken().getPrix());
/*
 * listeCartes.forEach(p -> { System.out.println(p.getId()+" "+p.toString());
 * });
 */
		return (listeCartes);

	}

}
