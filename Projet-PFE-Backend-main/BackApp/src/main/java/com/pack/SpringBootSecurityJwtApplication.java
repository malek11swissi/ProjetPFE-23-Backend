package com.pack;

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

import com.pack.models.Typetoken;
import com.pack.models.User;
import com.pack.models.Bonus;
import com.pack.models.CarteRecharge;
import com.pack.models.CentreRecharge;
import com.pack.models.Commande;
import com.pack.models.Compteur;
import com.pack.models.ERole;
import com.pack.models.Gouvernorat;
import com.pack.models.Panier;
import com.pack.models.Role;
import com.pack.models.Solde;
import com.pack.models.Ticket;
import com.pack.models.Token;
import com.pack.repository.TypetokenRepository;
import com.pack.repository.UserRepository;
import com.pack.repository.TokenRepository;
import com.pack.repository.BonusRepository;
import com.pack.repository.CarteRechargeRepository;
import com.pack.repository.CentreRechargeRepository;
import com.pack.repository.CommandeRepository;
import com.pack.repository.CompteurRepository;
import com.pack.repository.GouvernoratRepository;
import com.pack.repository.PanierRepository;
import com.pack.repository.RoleRepository;
import com.pack.repository.SoldeRepository;
import com.pack.repository.TicketRepository;

@SpringBootApplication
public class SpringBootSecurityJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	}

	@Autowired
	RoleRepository roleRepo;

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
	RoleRepository roleRepository;

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
	CentreRechargeRepository centrerechargerepository;

	@Autowired
	CarteRechargeRepository carterechargerepository;

	@Autowired
	BonusRepository bonusRepository;

	
	ArrayList<Gouvernorat> gouvernorats = new ArrayList<Gouvernorat>();

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			/*Typetoken typetoken5 = new Typetoken("5 dinars", 5.7);
			typetokenrepository.save(typetoken5);

			Typetoken typetoken10 = new Typetoken("10 dinars", 11.4);
			typetokenrepository.save(typetoken10);

			Typetoken typetoken20 = new Typetoken("20 dinars", 22.1);
			typetokenrepository.save(typetoken20);

			typetokenrepository.findAll().forEach(t -> {
				System.out.println(t.toString());
			});

			// Create users with BCrypt encoded password (user/user, admin/admin)
			Role role1 = new Role(ERole.ROLE_USER);
			Role role2 = new Role(ERole.ROLE_MODERATOR);
			Role role3 = new Role(ERole.ROLE_ADMIN);
			roleRepo.save(role1);
			roleRepo.save(role2);
			roleRepo.save(role3);

			// Affichage
			roleRepo.findAll().forEach(r -> {
				System.out.println(r.toString());

			});

			// ajout user
			// cryptage de mot de passe
			String password1 = "helloworld", password2 = "hitunisia", passwordadmin = "administrator",
					passwordlafayette = "lafayette", cryptedPassword1 = "", cryptedPassword2 = "",
					cryptedPasswordadmin = "", cryptedPasswordlafayette = "";
			cryptedPassword1 = encoder.encode(password1);
			cryptedPassword2 = encoder.encode(password2);
			cryptedPasswordadmin = encoder.encode(passwordadmin);
			cryptedPasswordlafayette = encoder.encode(passwordlafayette);
			System.out.println("cryptedPassword1 " + cryptedPassword1 + " &cryptedPassword2 " + cryptedPassword2);
			// setting user role
			User admin = new User("admin", "55123456", cryptedPasswordadmin);
			User mohamed = new User("mohamed", "22123456", cryptedPassword2);
			User siwar = new User("siwar", "98123456", cryptedPassword1);
			User lafayette = new User("lafayette", "71123456", cryptedPasswordlafayette);
			User boujaafar = new User("boujaafar", "73123456", cryptedPasswordlafayette);
			User cmetres = new User("cmetres", "74123456", cryptedPasswordlafayette);
			User lamedina = new User("lamedina", "78123456", cryptedPasswordlafayette);

			Set<Role> rolesadmin = new HashSet<>();
			Set<Role> rolesmoderator = new HashSet<>();
			Set<Role> rolesuser = new HashSet<>();
			rolesadmin.add(role3);
			rolesmoderator.add(role2);
			rolesuser.add(role1);
			
			admin.setRoles(rolesadmin);
			System.out.println("roles admin");
			admin.getRoles().forEach(r -> {
				System.out.println(r.toString());
			});

			lafayette.setRoles(rolesmoderator);
			boujaafar.setRoles(rolesmoderator);
			cmetres.setRoles(rolesmoderator);
			lamedina.setRoles(rolesmoderator);
			mohamed.setRoles(rolesuser);
			siwar.setRoles(rolesuser);
			userrepository.save(admin);
			userrepository.save(mohamed);
			userrepository.save(siwar);
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
			compteur2.setUser(siwar);
			compteur22.setUser(siwar);
			compteurrepository.save(compteur1);
			compteurrepository.save(compteur11);
			compteurrepository.save(compteur2);
			compteurrepository.save(compteur22);

			// affichage des compteurs d'un utilisateur x
			compteurrepository.getCompteursByUsername(mohamed.getUsername()).forEach(c -> {
				System.out.println(c.toString());
			});

			// affichage des compteurs de tous les utilisateurs
			userrepository.findAll().forEach(u -> {
				System.out.println("compteurs de user " + u.getUsername());
				compteurrepository.getCompteursByUsername(u.getUsername()).forEach(cu -> {
					System.out.println(cu.toString());
				});
			});

			
			Solde soldemohamed = new Solde(mohamed, 50);
			soldeRepository.save(soldemohamed);
			Solde soldesiwar = new Solde(siwar, 40);
			soldeRepository.save(soldesiwar);
			soldeRepository.findAll().forEach(p -> {
				System.out.println(p.toString());
			});
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
			gouvernorats.forEach(g -> {
				gouvernoratrepository.save(g);
			});

			// Initatiation des centres de recharge
			gouvernoratrepository.save(tunis);
			gouvernoratrepository.save(sousse);
			gouvernoratrepository.save(sfax);
			gouvernoratrepository.save(siliana);
			CentreRecharge clafayette=new CentreRecharge(lafayette,"centre LaFayette", tunis);
			CentreRecharge cboujaafar=new CentreRecharge(boujaafar,"centre Boujaafar", sousse);
			CentreRecharge ccmetres=new CentreRecharge(cmetres,"centre centmetres", sfax);
			CentreRecharge clamedina=new CentreRecharge(lamedina,"centre Elmedina", siliana);
			centrerechargerepository.save(clafayette);
			centrerechargerepository.save(cboujaafar);
			centrerechargerepository.save(ccmetres);
			centrerechargerepository.save(clamedina);
			centrerechargerepository.findAll().forEach(c -> {
				System.out.println(c.toString());
			});
			
			
			// Initiation des soldes
			Solde soldeadmin = new Solde(admin, 0000);
			Solde soldeclafayette = new Solde(lafayette, 1050);
			Solde soldecboujaafar = new Solde(boujaafar, 1000);
			Solde soldeccmetres = new Solde(cmetres, 950);
			Solde soldeclamedina = new Solde(lamedina, 850);
			soldeRepository.save(soldeadmin);
			soldeRepository.save(soldeclafayette);
			soldeRepository.save(soldecboujaafar);
			soldeRepository.save(soldeccmetres);
			soldeRepository.save(soldeclamedina);

		

			//initiation Bonus
			// Initiation des soldes
			Bonus bonus = new Bonus(0,20,0);
			Bonus bonus2 = new Bonus(20,50,10);
			Bonus bonus3 = new Bonus(50,100,20);
			bonusRepository.save(bonus);
			bonusRepository.save(bonus2);
			bonusRepository.save(bonus3);

			//initiation Cartes Recharge
			CarteRecharge carte5lafayette = new CarteRecharge(lafayette,typetoken5,100);
			CarteRecharge carte10lafayette = new CarteRecharge(lafayette,typetoken10,100);
			CarteRecharge carte20lafayette = new CarteRecharge(lafayette,typetoken20,100);
			carterechargerepository.save(carte5lafayette);
			carterechargerepository.save(carte10lafayette);
			carterechargerepository.save(carte20lafayette);
			CarteRecharge carte5boujaafar = new CarteRecharge(boujaafar,typetoken5,100);
			CarteRecharge carte10boujaafar = new CarteRecharge(boujaafar,typetoken10,100);
			CarteRecharge carte20boujaafar = new CarteRecharge(boujaafar,typetoken20,100);
			carterechargerepository.save(carte5boujaafar);
			carterechargerepository.save(carte10boujaafar);
			carterechargerepository.save(carte20boujaafar);
			CarteRecharge carte5lamedina = new CarteRecharge(lamedina,typetoken5,100);
			CarteRecharge carte10lamedina = new CarteRecharge(lamedina,typetoken10,100);
			CarteRecharge carte20lamedina = new CarteRecharge(lamedina,typetoken20,100);
			carterechargerepository.save(carte5lamedina);
			carterechargerepository.save(carte10lamedina);
			carterechargerepository.save(carte20lamedina);
			CarteRecharge carte5cmetres = new CarteRecharge(cmetres,typetoken5,100);
			CarteRecharge carte10cmetres = new CarteRecharge(cmetres,typetoken10,100);
			CarteRecharge carte20cmetres = new CarteRecharge(cmetres,typetoken20,100);
			carterechargerepository.save(carte5cmetres);
			carterechargerepository.save(carte10cmetres);
			carterechargerepository.save(carte20cmetres);

			*/
		};
	}

}
