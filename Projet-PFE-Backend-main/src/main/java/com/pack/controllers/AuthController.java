package com.pack.controllers;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pack.models.ERole;
import com.pack.models.Solde;
import com.pack.models.User;
import com.pack.payload.request.LoginRequest;
import com.pack.payload.request.SignupRequest;
import com.pack.payload.response.JwtResponse;
import com.pack.payload.response.MessageResponse;
import com.pack.repository.UserRepository;
import com.pack.security.jwt.JwtUtils;
import com.pack.security.services.UserDetailsImpl;
import com.pack.service.SoldeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;



	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	SoldeService soldeService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid   @RequestBody LoginRequest loginRequest) throws Exception {

		try {
			Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getTelephone(), 
												 roles.get(0))
												 );  
		}  catch(LockedException e)
        {
			return ResponseEntity.ok(new JwtResponse("Votre compte est bloqué"));
		

        }
	

		
	}

	

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByTelephone(signUpRequest.getTelephone()) ) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Telephone is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getTelephone(),
							 encoder.encode(signUpRequest.getPassword()));

		user.setActive(false);

		user.setRole(ERole.ROLE_CLIENT);
		userRepository.save(user);
		//initiation du solde null
		Solde solde=new Solde(user,0);
		soldeService.addSolde(solde);
		

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
