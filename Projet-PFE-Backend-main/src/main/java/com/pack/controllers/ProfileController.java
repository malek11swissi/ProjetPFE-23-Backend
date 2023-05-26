package com.pack.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pack.ConvertDate;
import com.pack.models.Commande;
import com.pack.models.Compteur;
import com.pack.models.ERole;
import com.pack.models.Profileform;
import com.pack.models.User;
import com.pack.repository.UserRepository;
import com.pack.service.CommandeService;
import com.pack.service.SoldeService;
import com.pack.service.UserService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class ProfileController {


	@Autowired
	CommandeService commandeService;
	@Autowired
	SoldeService soldeService;
	@Autowired
	ConvertDate convertDate;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepo;
 
	
	@GetMapping(value = "/profiles/getProfile")
	public Profileform getProfile(Authentication authentication)
	{
		User user =  userRepository.findByUsername(authentication.getName()).get();
		Profileform profile = new Profileform();
		profile.setUsername(user.getUsername());
		profile.setTelephone(user.getTelephone());
		profile.setLibelle(user.getRole() == ERole.ROLE_MARCHAND   ?user.getLibelle():null);
		profile.setGouvernorat(user.getRole() == ERole.ROLE_MARCHAND   ?user.getGouvernorat():null);
		return profile ;
	}

	@PutMapping( value = "/profiles/updateProfile")
	public void updateLoggedProfile(@RequestBody Profileform profileform , Authentication authentication) {
		String username = authentication.getName();
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		
	
	if(profileform.getTelephone()!="") {
			user.setTelephone(profileform.getTelephone());
		}

		if(profileform.getUsername()!="") {
			user.setUsername(profileform.getUsername());
		}
		if(user.getRole() == ERole.ROLE_MARCHAND)
		{
			if(profileform.getLibelle()!="" ) {
				user.setLibelle(profileform.getLibelle());
			}
			if(profileform.getGouvernorat().getId()!=0) {
				user.setGouvernorat(profileform.getGouvernorat());
			}
		}
		
	
		userRepo.save(user);
	
	}
	
	
	

}
