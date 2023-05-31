package com.pack.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.pack.models.Compteur;
import com.pack.models.Typetoken;
import com.pack.models.User;
import com.pack.repository.CompteurRepository;
import com.pack.repository.UserRepository;


@Component
public class CompteurService {

	@Autowired
	private CompteurRepository compteurRepo;
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserService userService;



	/* liste des compteurs par client */
	public List<Compteur> getCompteursByClient(Authentication authentication) {
		String username = authentication.getName();
		System.out.println("username" + username);
	// Définir ce user 
		User client=userService.getUserByUsername(username);
		return compteurRepo.findAllByUserId(client.getId());
	}

     
	public Optional<Compteur> getSingleCompteur(Long id) {
		return compteurRepo.findById(id);
	}
	


/* ajouter compteur dans la liste du client  */
	public ResponseEntity<Compteur> saveCompteur(Compteur compteur, Authentication authentication) {
		String username = authentication.getName();
		System.out.println("username" + username);
		User client=userService.getUserByUsername(username);
		// definir le compteur de ce user (client)
		compteur.setUser(client);
		Compteur compteurSaved = compteurRepo.save(compteur);
		return ResponseEntity.ok(compteurSaved);
	
	}
	
	
	//modifier compteur
	public void updateCompteur(Long id, Compteur compteur) {
		compteurRepo.save(compteur);
	}
	

	
	//supprimer compteur
	public void deleteCompteur(Long id) {
		compteurRepo.deleteById(id);
	}
	

	public List<Compteur> getAllCompteur()
	{
		return compteurRepo.findAll();

	}

	
}
