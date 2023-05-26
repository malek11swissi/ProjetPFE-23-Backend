package com.pack.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.ERole;
import com.pack.models.StatistiqueMensuel;
import com.pack.models.StatistiqueMensuelDetails;
import com.pack.service.StatistiqueMensuelDetailsService;
import com.pack.service.StatistiqueMensuelService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class StatistiqueMensuelDetailsController {

	@Autowired
	StatistiqueMensuelDetailsService statistiqueMensuelDetailsService;

	//@RequestMapping("/statistiqueMensuels")
	@PreAuthorize("hasRole('ADMIN')")
	//@PreAuthorize("hasRole('ROLE_MODERATOR')")
	
	@RequestMapping(method = RequestMethod.GET, value = "/statistiqueMensuelDetailss") 
	public List<StatistiqueMensuelDetails> getStatistiqueMensuelDetails() 
	{
		statistiqueMensuelDetailsService.getAllStatistiqueMensuelDetails().forEach(t->{
			System.out.println(t.toString());
		});
		return (List<StatistiqueMensuelDetails>) statistiqueMensuelDetailsService.getAllStatistiqueMensuelDetails();
	}

	/*
	 * @RequestMapping(method = RequestMethod.POST, value = "/statistiqueMensuels") public void
	 * addStatistiqueMensuel(@RequestBody StatistiqueMensuel statistiqueMensuel) {
	 * System.out.println("je suis dans ajout statistiqueMensuel");
	 * System.out.println(statistiqueMensuel.toString()); statistiqueMensuelService.addStatistiqueMensuel(statistiqueMensuel); }
	 */
	


	
}
