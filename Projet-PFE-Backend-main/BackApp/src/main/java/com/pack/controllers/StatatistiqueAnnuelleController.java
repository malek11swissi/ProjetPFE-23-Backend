package com.pack.controllers;

import java.util.*;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.StatistiqueAnnuel;
import com.pack.service.StatistiqueAnnuelService;


@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class StatatistiqueAnnuelleController {

	@Autowired
	StatistiqueAnnuelService statistiqueAnnuelService;

	@PreAuthorize("hasRole('ADMIN')")
	
	@RequestMapping(method = RequestMethod.GET, value = "/statistiqueAnnuels") 
	public ArrayList<StatistiqueAnnuel> getStatistiqueAnnuel() 
	{
		System.out.println("Affichage statistiques annuels");
		statistiqueAnnuelService.getAllStatistiqueAnnuel().forEach(t->{
			System.out.println(t.toString());
		});
		return (ArrayList<StatistiqueAnnuel>) statistiqueAnnuelService.getAllStatistiqueAnnuel();
	}

	
}
