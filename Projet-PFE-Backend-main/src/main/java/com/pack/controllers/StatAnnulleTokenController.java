package com.pack.controllers;

import java.util.*;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.Chart;
import com.pack.models.StatAnnulleToken;
import com.pack.service.StatistiqueAnnuelTokenService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class StatAnnulleTokenController {

	@Autowired
	StatistiqueAnnuelTokenService statAnnulleTokenService;

	
	
	@RequestMapping(method = RequestMethod.GET, value = "/statistiqueAnnuelsToken") 
	public ArrayList<StatAnnulleToken> getStatistiqueAnnuelToken() 
	{
		System.out.println("Affichage statistiques annuels");
		this.statAnnulleTokenService.getAllStatAnnulleTokenTriee().forEach(t->{
			System.out.println(t.toString());
		});
		return (ArrayList<StatAnnulleToken>) statAnnulleTokenService.getAllStatAnnulleTokenTriee();
	}

	@GetMapping(value = "/statistiqueAnnuelsToken/getStatsVenteToken")
	public Chart getStatsVenteToken()
	{
		return statAnnulleTokenService.getStatsChart();
	}
}
