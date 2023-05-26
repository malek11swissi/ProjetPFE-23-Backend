package com.pack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pack.models.Bonus;
import com.pack.repository.BonusRepository;


@Component
public class BonusService {

	@Autowired
	private BonusRepository bonusRepo;
	
	
	public List<Bonus> getAllBonus() {
		return bonusRepo.findAll();
	}
	
	public void addBonus(Bonus bonus) {
		bonusRepo.save(bonus);
	}
	
	public Optional<Bonus> getSingleBonus(Long id) {
		return bonusRepo.findById(id);
	}
	
	public void updateBonus(Long id, Bonus bonus) {
		bonusRepo.save(bonus);
	}
	
	public void deleteBonus(Long id) {
		bonusRepo.deleteById(id);
	}
	
	
}
