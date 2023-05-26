package com.pack.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.Compteur;
import com.pack.models.ERole;
import com.pack.models.Recharge;
import com.pack.models.Rechargeform;
import com.pack.models.Role;
import com.pack.models.User;
import com.pack.repository.SoldeRepository;
import com.pack.repository.UserRepository;
import com.pack.service.RechargeService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class RechargeController {

	@Autowired
	RechargeService rechargeService;
	@Autowired
	SoldeRepository solderrepo;
	@Autowired
	private UserRepository userRepository;

	// @RequestMapping("/recharges")
	// @PreAuthorize("hasRole('ADMIN')")
	@PreAuthorize("hasRole('ROLE_MODERATOR')")

	@RequestMapping(method = RequestMethod.GET, value = "/recharges")
	public List<Recharge> getRecharge()

	{
		rechargeService.getAllRecharge().forEach(t -> {
			System.out.println(t.toString());
		});
		return (List<Recharge>) rechargeService.getAllRecharge();

	}

/*ancien code
	@RequestMapping(method = RequestMethod.POST, value = "/recharges")
	public void addRecharge(@RequestBody Recharge recharge) {
		if (rechargeService.verifierRecharge(recharge)) {
			//ajout de bonus
			recharge.setPrix(rechargeService.ajouterBonus(recharge));
			//System.out.println(solderrepo.getSoldesByTelephone(recharge.getTelephone()));
			rechargeService.updateSolde(recharge.getTelephone(), recharge.getPrix());
			rechargeService.addRecharge(recharge);
		}
	}
*/
	@RequestMapping(method = RequestMethod.POST, value = "/recharges")
	public void addRecharge(@RequestBody Rechargeform rechargeform) {
		Recharge recharge=new Recharge();
		if (rechargeService.verifierRecharge(rechargeform)) {
			//ajout de bonus
			recharge.setPrix(rechargeService.ajouterBonus0(rechargeform.getPrix()));
			User user = userRepository.findByTelephone(rechargeform.getTelephone())
					.orElseThrow(() -> new UsernameNotFoundException("Role Not Found with telephone: " + rechargeform.getTelephone()));
			System.out.println("user:= "+user.toString());
			recharge.setUser(user);
			rechargeService.updateSolde(rechargeform.getTelephone(),rechargeService.ajouterBonus0(rechargeform.getPrix()));
			rechargeService.addRecharge(recharge);
		}
	}
	
//recharge par centre de recharge
	@RequestMapping(method = RequestMethod.POST, value = "/recharges/{username}")
	public void addRechargeparCentreRecharge(@RequestBody Rechargeform rechargeform, @PathVariable String username) {
		System.out.println("centrerecharge "+username);
		System.out.println("rechageform "+rechargeform.toString());
		Recharge recharge=new Recharge();
		if (rechargeService.verifierRecharge(rechargeform)&&rechargeService.verifierSoldeCentreRecharge(rechargeform, username)) {
			//verifier solde centre recharge
			//ajout de bonus
			recharge.setPrix(rechargeService.ajouterBonus2(rechargeform.getPrix()));
			User user = userRepository.findByTelephone(rechargeform.getTelephone())
					.orElseThrow(() -> new UsernameNotFoundException("Role Not Found with telephone: " + rechargeform.getTelephone()));
			System.out.println("user:= "+user.toString());
			recharge.setUser(user);
			rechargeService.updateSolde(rechargeform.getTelephone(),rechargeService.ajouterBonus2(rechargeform.getPrix()));
			rechargeService.addRecharge(recharge);
			rechargeService.reduireSoldeCentreRecharge(username,rechargeform.getPrix());
		}
	}

	
	
	
	@RequestMapping("/recharges/{id}")
	public Optional<Recharge> getSingleRecharge(@PathVariable Long id) {
		return rechargeService.getSingleRecharge(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/recharges/{id}")
	public void updateRecharge(@RequestBody Recharge recharge, @PathVariable Long id) {
		System.out.println(recharge.toString());
		rechargeService.updateRecharge(id, recharge);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/recharges/{id}")
	public void deleteRecharge(@PathVariable Long id) {
		rechargeService.deleteRecharge(id);
	}

	/*
	 * @RequestMapping(method = RequestMethod.GET, value = "/recharges/{username}")
	 * public List<Recharge> getRechargeByUser(@PathVariable String username) { //
	 * public int getCompteurByUser(@PathVariable Long iduser) {
	 * System.out.println("username:= " + username);
	 * System.out.println("recharge de l'utilistauer");
	 * rechargeService.getRechargesByUser(username).forEach(p->{
	 * System.out.println(p.toString()); }); return
	 * rechargeService.getRechargesByUser(username);
	 * 
	 * }
	 */
}
