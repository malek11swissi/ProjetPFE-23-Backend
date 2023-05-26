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
import com.pack.service.StatistiqueMensuelService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class StatistiqueMensuelController {

	@Autowired
	StatistiqueMensuelService statistiqueMensuelService;

	//@RequestMapping("/statistiqueMensuels")
	@PreAuthorize("hasRole('ADMIN')")
	//@PreAuthorize("hasRole('ROLE_MODERATOR')")
	
	@RequestMapping(method = RequestMethod.GET, value = "/statistiqueMensuels") 
	public List<StatistiqueMensuel> getStatistiqueMensuel() 
	{
		statistiqueMensuelService.getAllStatistiqueMensuel().forEach(t->{
			System.out.println(t.toString());
		});
		return (List<StatistiqueMensuel>) statistiqueMensuelService.getAllStatistiqueMensuel();
	}

	/*
	 * @RequestMapping(method = RequestMethod.POST, value = "/statistiqueMensuels") public void
	 * addStatistiqueMensuel(@RequestBody StatistiqueMensuel statistiqueMensuel) {
	 * System.out.println("je suis dans ajout statistiqueMensuel");
	 * System.out.println(statistiqueMensuel.toString()); statistiqueMensuelService.addStatistiqueMensuel(statistiqueMensuel); }
	 */
	

	@RequestMapping("/statistiqueMensuels/{id}")
	public Optional<StatistiqueMensuel> getSingleStatistiqueMensuel(@PathVariable Long id) {
		return statistiqueMensuelService.getSingleStatistiqueMensuel(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/statistiqueMensuels/{id}")
	public void updateStatistiqueMensuel(@RequestBody StatistiqueMensuel statistiqueMensuel, @PathVariable Long id) {
		System.out.println(statistiqueMensuel.toString());
		statistiqueMensuelService.updateStatistiqueMensuel(id, statistiqueMensuel);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/statistiqueMensuels/{id}")
	public void deleteStatistiqueMensuel(@PathVariable Long id) {
		statistiqueMensuelService.deleteStatistiqueMensuel(id);
	}

	
}
