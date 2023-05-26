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
import com.pack.models.Gouvernorat;
import com.pack.service.GouvernoratService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class GouvernoratController {

	@Autowired
	GouvernoratService gouvernoratService;

	//@RequestMapping("/gouvernorats")
	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_MARCHAND')")
	//@PreAuthorize("hasRole('ROLE_MODERATOR')")

	@RequestMapping(method = RequestMethod.GET, value = "/gouvernorats") 
	public List<Gouvernorat> getGouvernorat() {
		System.out.println("list of gouvernorat");
		 gouvernoratService.getAllGouvernorat().forEach(g->{
			System.out.println(g.toString()); 
		 });
		return (List<Gouvernorat>) gouvernoratService.getAllGouvernorat();

	}

	@RequestMapping(method = RequestMethod.POST, value = "/gouvernorats")
	public void addGouvernorat(@RequestBody Gouvernorat gouvernorat) {
		System.out.println(gouvernorat.toString());
		gouvernoratService.addGouvernorat(gouvernorat);
	}

	@RequestMapping("/gouvernorats/{id}")
	public Optional<Gouvernorat> getSingleGouvernorat(@PathVariable Long id) {
		return gouvernoratService.getSingleGouvernorat(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/gouvernorats/{id}")
	public void updateGouvernorat(@RequestBody Gouvernorat gouvernorat, @PathVariable Long id) {
		System.out.println(gouvernorat.toString());
		gouvernoratService.updateGouvernorat(id, gouvernorat);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/gouvernorats/{id}")
	public void deleteGouvernorat(@PathVariable Long id) {
		gouvernoratService.deleteGouvernorat(id);
	}

}
