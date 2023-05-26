package com.pack.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pack.ConvertDate;
import com.pack.models.StatistiqueAnnuel;
import com.pack.models.StatistiqueMensuel;
import com.pack.models.StatistiqueMensuelDetails;
import com.pack.repository.StatistiqueMensuelDetailsRepository;
import com.pack.repository.StatistiqueMensuelRepository;


@Component
public class StatistiqueMensuelDetailsService {

	@Autowired
	private StatistiqueMensuelDetailsRepository statistiqueMensuelDetailsRepo;
	
	
	public List<StatistiqueMensuelDetails> getAllStatistiqueMensuelDetails() {
		return statistiqueMensuelDetailsRepo.findAll();
	}
	
	public void addStatistiqueMensuelDetails(StatistiqueMensuelDetails statistiqueMensuelDetails) {
		statistiqueMensuelDetailsRepo.save(statistiqueMensuelDetails);
	}
	
	public Optional<StatistiqueMensuelDetails> getSingleStatistiqueMensuelDetails(Long id) {
		return statistiqueMensuelDetailsRepo.findById(id);
	}
	
	public void updateStatistiqueMensuelDetails(Long id, StatistiqueMensuelDetails statistiqueMensuelDetails, int mois) {
		switch(mois) {
		  case 1:
			  statistiqueMensuelDetails.setNbmois1(statistiqueMensuelDetails.getNbmois1()+1);
			  	break;
		  case 2:
			  statistiqueMensuelDetails.setNbmois2(statistiqueMensuelDetails.getNbmois2()+1);
			  	break;
		  case 3:
			  statistiqueMensuelDetails.setNbmois3(statistiqueMensuelDetails.getNbmois3()+1);
			  	break;
		  case 4:
			  statistiqueMensuelDetails.setNbmois4(statistiqueMensuelDetails.getNbmois4()+1);
			  	break;
		  case 5:
			  statistiqueMensuelDetails.setNbmois5(statistiqueMensuelDetails.getNbmois5()+1);
			  	break;
		  case 6:
			  statistiqueMensuelDetails.setNbmois6(statistiqueMensuelDetails.getNbmois6()+1);
			  	break;
		  case 7:
			  statistiqueMensuelDetails.setNbmois7(statistiqueMensuelDetails.getNbmois7()+1);
			  	break;
		  case 8:
			  statistiqueMensuelDetails.setNbmois8(statistiqueMensuelDetails.getNbmois8()+1);
			  	break;
		  case 9:
			  statistiqueMensuelDetails.setNbmois9(statistiqueMensuelDetails.getNbmois9()+1);
			  	break;
		  case 10:
			  statistiqueMensuelDetails.setNbmois10(statistiqueMensuelDetails.getNbmois10()+1);
			  	break;
		  case 11:
			  statistiqueMensuelDetails.setNbmois11(statistiqueMensuelDetails.getNbmois11()+1);
			  	break;
		  case 12:
			  statistiqueMensuelDetails.setNbmois12(statistiqueMensuelDetails.getNbmois12()+1);
			  	break;
		}
	   statistiqueMensuelDetailsRepo.save(statistiqueMensuelDetails);
	}
	
	
	
	
	
	public void ajouterStatMensuelDetails(Date date) {
		int annee = 0, mois, nb_mois, nb_annee,nbenreg=0;
		long idstatMoisD;
		Boolean anneeExist = false, moisExist = false;
		ConvertDate c = new ConvertDate();
		annee = c.retournerAnnee(date);
		mois=c.retournerMois(date);
		System.out.println("annee:= "+annee+" ,mois:= "+mois);
		System.out.println("ajouterStatMensuelDetails");
		if (statistiqueMensuelDetailsRepo.count() == 0) {
			System.out.println("table vide, nouveau enregistrement");
			StatistiqueMensuelDetails saa = new StatistiqueMensuelDetails(annee, mois,1,1);
			statistiqueMensuelDetailsRepo.save(saa);
		}
		else{
			System.out.println("je suis dans initiation d'un nouveau enregistrement");
			for (StatistiqueMensuelDetails sa : getAllStatistiqueMensuelDetails()) {
			System.out.println(sa.toString());
			//System.out.println("anneeExist:= "+anneeExist);
			if (sa.getAnnee() == annee) {
				System.out.println("je suis dans update mois");
				idstatMoisD = sa.getId();
				sa.setId(idstatMoisD);
				sa.setTotal(sa.getTotal()+1);
				updateStatistiqueMensuelDetails(idstatMoisD, sa,mois);
				moisExist = true;
				}
			}
			if (moisExist == false) {
			System.out.println("je suis une nouvelle annee ");
			StatistiqueMensuelDetails newsa = new StatistiqueMensuelDetails(annee, mois,1,1);
			statistiqueMensuelDetailsRepo.save(newsa);
			}
		}
		/*else{
			//savoir le nombre d'enreg
			System.out.println("je suis dans count>0");
			for (StatistiqueMensuel sa : getAllStatistiqueMensuel()) {
				System.out.println(sa.toString());
				//System.out.println("anneeExist:= "+anneeExist);
				if (sa.getAnnee() == annee && sa.getMois()==sa.getMois()) {
					System.out.println("here " + sa.toString());
					idstatMois = sa.getId();
					sa.setNb(sa.getNb() + 1);
					updateStatistiqueMensuel(idstatMois, sa);
					moisExist = true;
				}
			}
			if (moisExist == false) {
				System.out.println("nouvelle insertion");
				StatistiqueMensuel saa = new StatistiqueMensuel(annee,mois, 1);
				addStatistiqueMensuel(saa);
			}
		}*/
	}
	
	
}
