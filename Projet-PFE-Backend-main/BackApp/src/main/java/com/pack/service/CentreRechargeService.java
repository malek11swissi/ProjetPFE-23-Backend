package com.pack.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.pack.models.CentreRecharge;
import com.pack.models.CentreRechargeform;
import com.pack.models.ERole;
import com.pack.models.Role;
import com.pack.models.Solde;
import com.pack.models.User;
import com.pack.repository.CentreRechargeRepository;
import com.pack.repository.RoleRepository;
import com.pack.repository.SoldeRepository;
import com.pack.repository.UserRepository;


@Component
public class CentreRechargeService {

	@Autowired
	private CentreRechargeRepository centreRechargeRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	SoldeRepository soldeRepository;
	
	
	public List<CentreRecharge> getAllCentreRecharge() {
		return centreRechargeRepo.findAll();
	}
	
	public void addCentreRecharge(CentreRechargeform centreRechargeform) {
		addUserCentreRecharge(centreRechargeform);
	}
	
	public Optional<CentreRecharge> getSingleCentreRecharge(Long id) {
		return centreRechargeRepo.findById(id);
	}
	
	public void updateCentreRecharge(Long id, CentreRecharge centreRecharge) {
		centreRechargeRepo.save(centreRecharge);
	}
	
	public void deleteCentreRecharge(Long id) {
		centreRechargeRepo.deleteById(id);
	}
	
	public void addUserCentreRecharge(CentreRechargeform centreRechargeform) {
		String cryptedPassword;
		CentreRecharge centreRecharge=new CentreRecharge();
		//initier le solde de centre de recharge
		Solde solde=new Solde();
		Set<Role> rolesmoderator = new HashSet<>();
		Role rolemoderator = roleRepository.findByName(ERole.ROLE_MODERATOR)
				.orElseThrow(() -> new UsernameNotFoundException("Role Not Found with username: " + ERole.ROLE_MODERATOR));
		rolesmoderator.add(rolemoderator);
		System.out.println("roles of moderator: ");
		rolesmoderator.forEach(r->{
			System.out.println(r.toString());
		});
		cryptedPassword = encoder.encode(centreRechargeform.getPassword());
		User user= new User();
		user.setUsername(centreRechargeform.getLogin());
		user.setPassword(cryptedPassword);
		user.setTelephone(centreRechargeform.getTelephone());
		user.setRoles(rolesmoderator);
		System.out.println("new user for centre recharge "+user.toString());
		userRepo.save(user);
		//inserer le solde du nouveau centre de recharge
		solde.setUser(user);
		solde.setValeur(centreRechargeform.getSoldeInitial());
		soldeRepository.save(solde);
		//inserer centre recharge
		centreRecharge.setLibelle(centreRechargeform.getLibelle());
		centreRecharge.setGouvernorat(centreRechargeform.getGouvernorat());
		centreRecharge.setUser(user);
		centreRechargeRepo.save(centreRecharge);
		
	}
	
	
}
