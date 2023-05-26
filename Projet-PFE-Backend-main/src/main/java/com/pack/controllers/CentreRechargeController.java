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
import com.pack.models.User;

import com.pack.models.MarchandInformations;
import com.pack.service.CentreRechargeService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class CentreRechargeController {

	@Autowired
	CentreRechargeService centreRechargeService;
	@PreAuthorize("hasRole('ADMIN')")


	
	@RequestMapping("/centreRecharges/{id}")
	public Optional<User> getSingleCentreRecharge(@PathVariable Long id) {
		return null ;
	}

	/*@RequestMapping(method = RequestMethod.PUT, value = "/centreRecharges/{id}")
	public void updateCentreRecharge(@RequestBody CentreRecharge centreRecharge, @PathVariable Long id) {
		System.out.println(centreRecharge.toString());
		centreRechargeService.updateCentreRecharge(id, centreRecharge);
	}*/

	/*@RequestMapping(method = RequestMethod.DELETE, value = "/centreRecharges/{id}")
	public void deleteCentreRecharge(@PathVariable Long id) {
		centreRechargeService.deleteCentreRecharge(id);
	}*/

}
