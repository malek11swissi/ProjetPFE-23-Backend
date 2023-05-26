package com.pack.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pack.ConvertDate;
import com.pack.models.StatistiqueAnnuel;
import com.pack.repository.StatistiqueAnnuelRepository;

@Component
public class StatistiqueAnnuelService {

	@Autowired
	private StatistiqueAnnuelRepository statistiqueAnnuelRepo;

	public List<StatistiqueAnnuel> getAllStatistiqueAnnuel() {
		return statistiqueAnnuelRepo.findAll();
	}

	public void addStatistiqueAnnuel(StatistiqueAnnuel statistiqueAnnuel) {
		statistiqueAnnuelRepo.save(statistiqueAnnuel);
	}

	public Optional<StatistiqueAnnuel> getSingleStatistiqueAnnuel(Long id) {
		return statistiqueAnnuelRepo.findById(id);
	}

	public void updateStatistiqueAnnuel(Long id, StatistiqueAnnuel statistiqueAnnuel) {
		statistiqueAnnuelRepo.save(statistiqueAnnuel);
	}

	public void deleteStatistiqueAnnuel(Long id) {
		statistiqueAnnuelRepo.deleteById(id);
	}

	public void ajouterStatAnnuel(Date date, int typeToken) {
		int annee = 0, mois, nb_mois, nb_annee,nbenreg=0;
		long idstatAnne;
		Boolean anneeExist = false, moisExist = false;
		ConvertDate c = new ConvertDate();
		annee = c.retournerAnnee(date);
		if (statistiqueAnnuelRepo.count() == 0) {
			System.out.println("table vide, nouvelle insertion");
			StatistiqueAnnuel saa = new StatistiqueAnnuel(annee, 1);
			addStatistiqueAnnuel(saa);
		}
		else{
			//savoir le nombre d'enreg
			System.out.println("je suis dans count>0");
			for (StatistiqueAnnuel sa : getAllStatistiqueAnnuel()) {
				System.out.println(sa.toString());
				//System.out.println("anneeExist:= "+anneeExist);
				if (sa.getAnnee() == annee) {
					System.out.println("here " + sa.toString());
					idstatAnne = sa.getId();
					sa.setNb(sa.getNb() + 1);
					updateStatistiqueAnnuel(idstatAnne, sa);
					anneeExist = true;
				}
			}
			if (anneeExist == false) {
				System.out.println("nouvelle insertion");
				StatistiqueAnnuel saa = new StatistiqueAnnuel(annee, 1);
				addStatistiqueAnnuel(saa);
			}
		}
	}

}
