package com.pack.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.pack.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.pack.models.Compteur;
import com.pack.models.Typetoken;
import com.pack.models.Pack;
import com.pack.repository.PackRepository;

@Component
public class PackService {
	

	@Autowired
	private PackRepository packRepo;

	@Autowired
	UserService userService;

   /* Gestion Pack  */


	// Consulter Pack Marchand
	public List <Pack> getPacksByMarchand(Authentication authentication) {
		List<Pack> listePacks = new ArrayList<Pack>();
		String username = authentication.getName();
		User marchand =userService.getUserByUsername(username);
		listePacks = packRepo.getPackByUserId(marchand.getId());

		return listePacks;
	}
   

	// Modifier Pack 
	public void updatePack(Authentication authentication,double prixtypetoken) {
		System.out.println("je suis dans updatePackBymarchandName");
		List<Pack> listePacks = new ArrayList<Pack>();
		listePacks = getPacksByMarchand(authentication);
		listePacks.forEach(c->{
			if(c.getTypetoken().getPrix()==prixtypetoken)
			{
				c.setNombre(c.getNombre()-1);
				System.out.println(c.toString());
			}
		});
	}






	public List<Pack> getAllPack() {
		return packRepo.findAll();
	}

	public void addCarteRecharge(Pack pack) {
		packRepo.save(pack);
	}

	public Optional<Pack> getSinglePack(Long id) {
		return packRepo.findById(id);
	}

	public void updatePack(Long id, Pack pack) {
		packRepo.save(pack);
	}

	public void deletePack(Long id) {
		packRepo.deleteById(id);
	}

	

	

	
	
	public long retournerIdPack(long idTypetoken) {
		long idPack = 0;
		for (Pack pack : getAllPack()) {
			if (pack.getTypetoken().getId() == idTypetoken)
				idPack = pack.getId();
		}
		System.out.println("idPack:= " + idPack);
		return idPack;
	}

}
