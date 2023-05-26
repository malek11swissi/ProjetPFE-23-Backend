package com.pack.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import com.pack.repository.SoldeRepository;
import com.pack.repository.UserRepository;


@Component
public class CentreRechargeService {


	@Autowired
	private UserRepository userRepo;
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	SoldeRepository soldeRepository;
	
	
	
	
	
	
	/*public Optional<CentreRecharge> getSingleCentreRecharge(Long id) {
		return centreRechargeRepo.findById(id);
	}*/
	
	/*public void updateCentreRecharge(Long id, CentreRecharge centreRecharge) {
		centreRechargeRepo.save(centreRecharge);
	}*/
	
	/*public void deleteCentreRecharge(Long id) {
		centreRechargeRepo.deleteById(id);
	}*/
	
	
	
	
}
