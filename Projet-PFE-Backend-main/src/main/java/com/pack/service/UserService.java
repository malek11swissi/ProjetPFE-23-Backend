package com.pack.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.pack.models.*;
import com.pack.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.pack.repository.SoldeRepository;
import com.pack.repository.UserRepository;


@Component
public class UserService {
	
	User user;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	SoldeRepository soldeRepository;
	

	/* Gérer User  */

	
	public List<User> getAllUser() {
		return userRepo.findAllByRoleNot(ERole.ROLE_ADMIN);
	}
	  

	public User getUserByUsername(String username) {
	
		return userRepo.findByUsername(username).get();
	}
   // Désactiver User

	public String blockUser(Long id)
	{
		User user = userRepo.findById(id).orElse(null);
		user.setActive(false);
		userRepo.save(user);
		return "User Blocked";
	}

   // Activer User 
	public String unblockUser(Long id)
	{
		User user = userRepo.findById(id).orElse(null);
		user.setActive(true);
		userRepo.save(user);
		return "User Unblocked";
		
	}

    





/* Gérer Marchand   */

	public List<Marchand> getAllMarchands()
	{
			List<User> marchands = userRepo.findAllByRole(ERole.ROLE_MARCHAND);
			return marchands.stream().map(Marchand::fromEntity).collect(Collectors.toList());

	}

	public MarchandInfo getMarchandInfos(Long marchandId)
	{
		User user = userRepo.findById(marchandId).orElse(null);
		return MarchandInfo.fromEntity(user);
	}


	public ResponseEntity<String> addMarchand(MarchandInformations marchandInformations) {
		String cryptedPassword;
		User marchand = new User();
		// Initiate the recharge center balance
		Solde solde = new Solde();
		cryptedPassword = encoder.encode(marchandInformations.getPassword());
		User user = new User();
		user.setUsername(marchandInformations.getLogin());
		// Prend Role Marchand 
		user.setPassword(cryptedPassword);
		user.setTelephone(marchandInformations.getTelephone());
		user.setRole(ERole.ROLE_MARCHAND);
		user.setLibelle(marchandInformations.getLibelle());
		user.setGouvernorat(marchandInformations.getGouvernorat());
		user.setActive(true);
		userRepo.save(user);
		// Insert the balance for the new recharge center
		solde.setUser(user);
		solde.setValeur(0);
		soldeRepository.save(solde);
		String message = "Marchand ajouté avec succès!";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}


   // Modifier Marchand 
	public MarchandInfo updateMarchand(MarchandInfo marchandInfo)
	{
		User user = userRepo.findById(marchandInfo.getId()).orElse(null);
		user.setUsername(marchandInfo.getUsername());
		user.setLibelle(marchandInfo.getLibelle());
		user.setTelephone(marchandInfo.getTelephone());
		user.setGouvernorat(marchandInfo.getGouvernorat());
		userRepo.save(user);
		return MarchandInfo.fromEntity(user);
	}

	

	 /* Gérer Profil */

	public Profile getUserProfile(Authentication authentication)
	{
		String username = authentication.getName();
	
		User user=this.getUserByUsername(username);

		return Profile.fromEntity(user);

	}


	public void  updateUserProfile(Profile profile )
	{
		
		User user = getUserByUsername(profile.getUsername());
		
		user.setUsername(profile.getUsername());
        user.setTelephone(profile.getTelephone());
		user.setLibelle(profile.getLibelle());
		user.setGouvernorat(profile.getGouvernorat());
	
		 userRepo.save(user);


	}


	public void addUser(User user) {
		userRepo.save(user);
	}


	public void updateUser(Long id, User user) {
		userRepo.save(user);
	}
	
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}
	
	public User getUser(String username) {
		userRepo.findAll().forEach(u->{
			if(u.getUsername().equals(username))
				user=u;
		});
		return user;
	}
    
	public Optional<User> getSingleUser(Long id) {
		return userRepo.findById(id);
	}




	

	/*
	 * public User findUserbyUsername(String username) { User user= new User();
	 * userRepo.findAll().forEach(u->{ if(u.getUsername().equals(username)) {
	 * user.setId(u.getId()); user.se } }); return user; }
	 */
	

	/*  public void addMarchand(MarchandInformations marchandInformations) {
		String cryptedPassword;
		User marchand=new User();
		//initier le solde de centre de recharge
		Solde solde=new Solde();
		
	
		cryptedPassword = encoder.encode(marchandInformations.getPassword());
		User user= new User();
		user.setUsername(marchandInformations.getLogin());
		user.setPassword(cryptedPassword);
		user.setTelephone(marchandInformations.getTelephone());
		user.setRole(ERole.ROLE_MARCHAND);
		user.setLibelle(marchandInformations.getLibelle());
		user.setGouvernorat(marchandInformations.getGouvernorat());
		userRepo.save(user);
		//inserer le solde du nouveau centre de recharge
		solde.setUser(user);
		solde.setValeur(0);
		soldeRepository.save(solde);
	
		
	} */





	
	

	
	

	
	
}
