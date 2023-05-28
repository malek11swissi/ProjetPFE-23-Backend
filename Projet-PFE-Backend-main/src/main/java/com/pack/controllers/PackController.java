package com.pack.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.pack.models.User;
import com.pack.service.*;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import com.pack.models.Pack;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test/packs")
@RestController
public class PackController {

	
	@Autowired
	CommandeService commandeService;
	@Autowired
	PackService packService;
	@Autowired
	SoldeService soldeService;
	@Autowired
	TokenService tokenService;
	@Autowired
	StatistiqueAnnuelTokenService statistiqueAnnuelServiceToken;


	
	
	@PreAuthorize("hasRole('ROLE_MARCHAND')")


	@RequestMapping(method = RequestMethod.GET, value = "/packs")
	public List<Pack> getPack()

	{
		packService.getAllPack().forEach(t -> {
			System.out.println(t.toString());
		});
		return (List<Pack>) packService.getAllPack();

	}

	@RequestMapping(method = RequestMethod.POST, value = "/carteRecharges")
	public void addPack(@RequestBody Pack pack) {
		System.out.println(pack.toString());
		packService.addCarteRecharge(pack);
	}

	@RequestMapping("/pack/{id}")
	public Optional<Pack> getSingleCarteRecharge(@PathVariable Long id) {
		return packService.getSinglePack(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/packs/{id}")
	public void updatePack(@RequestBody Pack packs, @PathVariable Long id) {
		System.out.println(packs.toString());
		packService.updatePack(id, packs);
	}


	@GetMapping( value = "/getPackMarchand")
	public List<Pack> getPackMarchand(Authentication authentication) {
	return packService.getPacksByMarchand(authentication);

	}


}
