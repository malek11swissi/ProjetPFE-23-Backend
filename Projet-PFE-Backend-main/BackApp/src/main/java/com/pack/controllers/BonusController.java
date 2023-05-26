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
import com.pack.models.Bonus;
import com.pack.service.BonusService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class BonusController {

	@Autowired
	BonusService bonusService;

	//@RequestMapping("/bonuss")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	//@PreAuthorize("hasRole('ROLE_MODERATOR')")

	@RequestMapping(method = RequestMethod.GET, value = "/bonuss") 
	public List<Bonus> getBonus() {
		return (List<Bonus>) bonusService.getAllBonus();

	}

	@RequestMapping(method = RequestMethod.POST, value = "/bonuss")
	public void addBonus(@RequestBody Bonus bonus) {
		System.out.println(bonus.toString());
		bonusService.addBonus(bonus);
	}

	@RequestMapping("/bonuss/{id}")
	public Optional<Bonus> getSingleBonus(@PathVariable Long id) {
		return bonusService.getSingleBonus(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/bonuss/{id}")
	public void updateBonus(@RequestBody Bonus bonus, @PathVariable Long id) {
		System.out.println(bonus.toString());
		bonusService.updateBonus(id, bonus);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/bonuss/{id}")
	public void deleteBonus(@PathVariable Long id) {
		bonusService.deleteBonus(id);
	}

}
