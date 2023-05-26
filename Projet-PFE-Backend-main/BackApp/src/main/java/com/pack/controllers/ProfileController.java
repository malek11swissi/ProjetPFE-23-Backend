package com.pack.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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

	/*
	 * @Autowired ProfileService profileService;
	 */
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


	// @RequestMapping("/profiles")
	// @PreAuthorize("hasRole('ADMIN')")
	@PreAuthorize("hasRole('ROLE_USER')")

	/*
	 * @RequestMapping(method = RequestMethod.GET, value = "/profiles") public
	 * List<Profile> getProfile()
	 * 
	 * { profileService.getAllProfile().forEach(t -> {
	 * System.out.println(t.toString()); }); return (List<Profile>)
	 * profileService.getAllProfile();
	 * 
	 * }
	 */

	/*
	 * @RequestMapping(method = RequestMethod.POST, value = "/profiles") public void
	 * addProfile(@RequestBody Profile profile) {
	 * System.out.println(profile.toString()); profileService.addProfile(profile); }
	 * 
	 * @RequestMapping("/profiles/{id}") public Optional<Profile>
	 * getSingleProfile(@PathVariable Long id) { return
	 * profileService.getSingleProfile(id); }
	 */

	@RequestMapping(method = RequestMethod.POST, value = "/profiles/{username}")
	public void updateLoggedProfile(@PathVariable String username,@RequestBody Profileform profileform) {
		String password=profileform.getPassword(),cryptedPassword="";
		System.out.println("je suis dans profile");
		System.out.println("username "+username);
		System.out.println("profileform "+ profileform.toString());
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
//		if(profileform.getTelephone()!=null) {
			if(profileform.getTelephone()!="") {
			user.setTelephone(profileform.getTelephone());
		}
		cryptedPassword = encoder.encode(password);
		user.setPassword(cryptedPassword);
		userService.updateUser(user.getId(), user);
		//System.out.println(user.toString());
		//profileService.updateProfile(id, profile);
	}
	
	
	/*
	 * @RequestMapping(method = RequestMethod.GET, value = "/profiles/{username}")
	 * public List<Profile> getProfileByUser(@PathVariable String username) { //
	 * public int getCompteurByUser(@PathVariable Long iduser) {
	 * System.out.println("username:= " + username);
	 * System.out.println("profile de l'utilistauer");
	 * profileService.getProfilesByUser(username).forEach(p -> {
	 * System.out.println(p.toString()); }); return
	 * profileService.getProfilesByUser(username);
	 * 
	 * }
	 */

}
