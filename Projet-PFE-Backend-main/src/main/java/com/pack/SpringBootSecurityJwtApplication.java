package com.pack;



import com.pack.models.Typetoken;
import com.pack.models.User;

import com.pack.models.Commande;
import com.pack.models.Compteur;
import com.pack.models.ERole;
import com.pack.models.Gouvernorat;
import com.pack.models.Pack;
import com.pack.models.Panier;
import com.pack.models.Solde;
import com.pack.models.Ticket;
import com.pack.models.Token;

import com.pack.repository.TypetokenRepository;
import com.pack.repository.UserRepository;
import com.pack.repository.TokenRepository;
import com.pack.repository.PackRepository;

import com.pack.repository.CommandeRepository;
import com.pack.repository.CompteurRepository;
import com.pack.repository.GouvernoratRepository;
import com.pack.repository.PanierRepository;
import com.pack.repository.SoldeRepository;
import com.pack.repository.TicketRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;



@SpringBootApplication
public class SpringBootSecurityJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	}

	

	@Autowired
	TypetokenRepository typetokenrepository;

	@Autowired
	TokenRepository tokenrepository;

	@Autowired
	TicketRepository ticketrepository;

	@Autowired
	CompteurRepository compteurrepository;

	@Autowired
	UserRepository userrepository;

	@Autowired
	PasswordEncoder encoder;



	@Autowired
	PanierRepository panierRepository;

	@Autowired
	CommandeRepository commandeRepository;

	@Autowired
	SoldeRepository soldeRepository;

	@Autowired
	ConvertDate convertDate;

	@Autowired
	GouvernoratRepository gouvernoratrepository;

	

	@Autowired
	PackRepository packRepository;


	ArrayList<Gouvernorat> gouvernorats = new ArrayList<Gouvernorat>();

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

/* 
			
			Typetoken typetoken5 = new Typetoken("5 dinars", 5.7);
			typetokenrepository.save(typetoken5);
			Typetoken typetoken10 = new Typetoken("10 dinars", 11.4);
			typetokenrepository.save(typetoken10);
			Typetoken typetoken20 = new Typetoken("20 dinars", 22.1);
			typetokenrepository.save(typetoken20);
*/
			Gouvernorat tunis = new Gouvernorat("Tunis");
			gouvernorats.add(tunis);
			Gouvernorat mennouba = new Gouvernorat("Mennouba");
			gouvernorats.add(mennouba);
			Gouvernorat benarous = new Gouvernorat("Ben Arous");
			gouvernorats.add(benarous);
			Gouvernorat ariana = new Gouvernorat("Ariana");
			gouvernorats.add(ariana);
			Gouvernorat nabeul = new Gouvernorat("Nabeul");
			gouvernorats.add(nabeul);
			Gouvernorat zaghouan = new Gouvernorat("Zaghouan");
			gouvernorats.add(zaghouan);
			Gouvernorat bizerte = new Gouvernorat("Bizerte");
			gouvernorats.add(bizerte);
			Gouvernorat beja = new Gouvernorat("Béja");
			gouvernorats.add(beja);
			Gouvernorat jendouba = new Gouvernorat("Jendouba");
			gouvernorats.add(jendouba);
			Gouvernorat kef = new Gouvernorat("Le Kef");
			gouvernorats.add(kef);
			Gouvernorat siliana = new Gouvernorat("Siliana");
			gouvernorats.add(siliana);
			Gouvernorat sousse = new Gouvernorat("Sousse");
			gouvernorats.add(sousse);
			Gouvernorat monastir = new Gouvernorat("Monastir");
			gouvernorats.add(monastir);
			Gouvernorat mahdia = new Gouvernorat("Mahdia");
			gouvernorats.add(mahdia);
			Gouvernorat sfax = new Gouvernorat("Sfax");
			gouvernorats.add(sfax);
			Gouvernorat kairouan = new Gouvernorat("Kairouan");
			gouvernorats.add(kairouan);
			Gouvernorat bouZid = new Gouvernorat("Sidi BouZid");
			gouvernorats.add(bouZid);
			Gouvernorat kasserine = new Gouvernorat("Kasserine");
			gouvernorats.add(kasserine);
			Gouvernorat gabes = new Gouvernorat("Gabès");
			gouvernorats.add(gabes);
			Gouvernorat medenine = new Gouvernorat("Medenine");
			gouvernorats.add(medenine);
			Gouvernorat tataouine = new Gouvernorat("Tataouine");
			gouvernorats.add(tataouine);
			Gouvernorat gafsa = new Gouvernorat("Gafsa");
			gouvernorats.add(gafsa);
			Gouvernorat tozeur = new Gouvernorat("Tozeur");
			gouvernorats.add(tozeur);
			Gouvernorat kebili = new Gouvernorat("Kebili");
			gouvernorats.add(kebili);
			gouvernoratrepository.saveAll(gouvernorats);

			// Create users with BCrypt encoded password (user/user, admin/admin)
			// ajout user
			// cryptage de mot de passe
			String password1 = "helloworld", password2 = "hitunisia", passwordadmin = "administrator",
					passwordlafayette = "lafayette", cryptedPassword1 = "", cryptedPassword2 = "",
					cryptedPasswordadmin = "", cryptedPasswordlafayette = "";
			cryptedPassword1 = encoder.encode(password1);
			cryptedPassword2 = encoder.encode(password2);
			cryptedPasswordadmin = encoder.encode(passwordadmin);
			cryptedPasswordlafayette = encoder.encode(passwordlafayette);
			// setting user role
			User admin = new User("admin", "55123456", cryptedPasswordadmin);
			User mohamed = new User("mohamed", "22123456", cryptedPassword2);
			User malek = new User("malek", "98123456", cryptedPassword1);
			User lafayette = new User("lafayette", "71123456", cryptedPasswordlafayette,"centre LaFayette", tunis);
			User boujaafar = new User("boujaafar", "73123456", cryptedPasswordlafayette,"centre Boujaafar", sousse);
			User cmetres = new User("cmetres", "74123456", cryptedPasswordlafayette, "centre centmetres", sfax);
			User lamedina = new User("lamedina", "78123456", cryptedPasswordlafayette,"centre Elmedina", siliana);
            	admin.setActive(true);
				userrepository.save(admin);

				
			admin.setRole(ERole.ROLE_ADMIN);
			lafayette.setRole(ERole.ROLE_MARCHAND);
			boujaafar.setRole(ERole.ROLE_MARCHAND);
			cmetres.setRole(ERole.ROLE_MARCHAND);
			lamedina.setRole(ERole.ROLE_MARCHAND);
			mohamed.setRole(ERole.ROLE_CLIENT);
			malek.setRole(ERole.ROLE_CLIENT);

		
			lafayette.setActive(true);
			boujaafar.setActive(true);
			cmetres.setActive(true);
			lamedina.setActive(true);
			malek.setActive(true);

			
			userrepository.save(mohamed);
			userrepository.save(malek);
			userrepository.save(lafayette);
			userrepository.save(boujaafar);
			userrepository.save(cmetres);
			userrepository.save(lamedina);

			// Ajout compteur
			Compteur compteur1 = new Compteur();
			Compteur compteur11 = new Compteur();
			compteur1.setLibelle("C-124578");
			compteur11.setLibelle("C-1444578");
			compteur1.setUser(mohamed);
			compteur11.setUser(mohamed);

			Compteur compteur2 = new Compteur();
			Compteur compteur22 = new Compteur();
			compteur2.setLibelle("C-104578");
			compteur22.setLibelle("C-154578");
			compteur2.setUser(malek);
			compteur22.setUser(malek);
			compteurrepository.save(compteur1);
			compteurrepository.save(compteur11);
			compteurrepository.save(compteur2);
			compteurrepository.save(compteur22);

			Solde soldemohamed = new Solde(mohamed, 50);
			soldeRepository.save(soldemohamed);
			Solde soldemalek = new Solde(malek, 40);
			soldeRepository.save(soldemalek);
/* 
			//initiation pack
			Pack pack5lafayette = new Pack(lafayette,typetoken5,100);
			Pack pack10lafayette = new Pack(lafayette,typetoken10,100);
			Pack pack20lafayette = new Pack(lafayette,typetoken20,100);
			packRepository.save(pack5lafayette);
			packRepository.save(pack10lafayette);
			packRepository.save(pack20lafayette);
			Pack pack5boujaafar = new Pack(boujaafar,typetoken5,100);
			Pack pack10boujaafar = new Pack(boujaafar,typetoken10,100);
			Pack pack20boujaafar = new Pack(boujaafar,typetoken20,100);
			packRepository.save(pack5boujaafar);
			packRepository.save(pack10boujaafar);
			packRepository.save(pack20boujaafar);
			Pack pack5lamedina = new Pack(lamedina,typetoken5,100);
			Pack pack10lamedina = new Pack(lamedina,typetoken10,100);
			Pack pack20lamedina = new Pack(lamedina,typetoken20,100);
			packRepository.save(pack5lamedina);
			packRepository.save(pack10lamedina);
			packRepository.save(pack20lamedina);
			Pack pack5cmetres = new Pack(cmetres,typetoken5,100);
			Pack pack10cmetres = new Pack(cmetres,typetoken10,100);
			Pack pack20cmetres = new Pack(cmetres,typetoken20,100);
			packRepository.save(pack5cmetres);
			packRepository.save(pack10cmetres);
			packRepository.save(pack20cmetres);

			admin.setRole(ERole.ROLE_ADMIN);
			lafayette.setRole(ERole.ROLE_MARCHAND);
			boujaafar.setRole(ERole.ROLE_MARCHAND);
			cmetres.setRole(ERole.ROLE_MARCHAND);
			lamedina.setRole(ERole.ROLE_MARCHAND);
			mohamed.setRole(ERole.ROLE_CLIENT);
			malek.setRole(ERole.ROLE_CLIENT);
			
			Typetoken typetoken5 = new Typetoken("5 dinars", 5.7);
			typetokenrepository.save(typetoken5);
			Typetoken typetoken10 = new Typetoken("10 dinars", 11.4);
			typetokenrepository.save(typetoken10);
			Typetoken typetoken20 = new Typetoken("20 dinars", 22.1);
			typetokenrepository.save(typetoken20);

			Pack pack5lafayette = new Pack(lafayette,typetoken5,100);
			Pack pack10lafayette = new Pack(lafayette,typetoken10,100);
			Pack pack20lafayette = new Pack(lafayette,typetoken20,100);
			packRepository.save(pack5lafayette);
			packRepository.save(pack10lafayette);
			packRepository.save(pack20lafayette);
			Pack pack5boujaafar = new Pack(boujaafar,typetoken5,100);
			Pack pack10boujaafar = new Pack(boujaafar,typetoken10,100);
			Pack pack20boujaafar = new Pack(boujaafar,typetoken20,100);
			packRepository.save(pack5boujaafar);
			packRepository.save(pack10boujaafar);
			packRepository.save(pack20boujaafar);
			Pack pack5lamedina = new Pack(lamedina,typetoken5,100);
			Pack pack10lamedina = new Pack(lamedina,typetoken10,100);
			Pack pack20lamedina = new Pack(lamedina,typetoken20,100);
			packRepository.save(pack5lamedina);
			packRepository.save(pack10lamedina);
			packRepository.save(pack20lamedina);
			Pack pack5cmetres = new Pack(cmetres,typetoken5,100);
			Pack pack10cmetres = new Pack(cmetres,typetoken10,100);
			Pack pack20cmetres = new Pack(cmetres,typetoken20,100);
			packRepository.save(pack5cmetres);
			packRepository.save(pack10cmetres);
			packRepository.save(pack20cmetres);*/
		}; 
	} 

} 
