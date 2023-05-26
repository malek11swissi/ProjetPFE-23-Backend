package com.pack.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pack.ConvertDate;
import com.pack.models.StatistiqueAnnuel;
import com.pack.models.StatistiqueMensuel;
import com.pack.repository.StatistiqueMensuelRepository;


@Component
public class StatistiqueMensuelService {

	@Autowired
	private StatistiqueMensuelRepository statistiqueMensuelRepo;
	
	
	public List<StatistiqueMensuel> getAllStatistiqueMensuel() {
		return statistiqueMensuelRepo.findAll();
	}
	
	public void addStatistiqueMensuel(StatistiqueMensuel statistiqueMensuel) {
		statistiqueMensuelRepo.save(statistiqueMensuel);
	}
	
	public Optional<StatistiqueMensuel> getSingleStatistiqueMensuel(Long id) {
		return statistiqueMensuelRepo.findById(id);
	}
	
	public void updateStatistiqueMensuel(Long id, StatistiqueMensuel statistiqueMensuel) {
		statistiqueMensuelRepo.save(statistiqueMensuel);
	}
	
	public void deleteStatistiqueMensuel(Long id) {
		statistiqueMensuelRepo.deleteById(id);
	}
	
	public void ajouterStatMensuel(Date date) {
		int annee = 0, mois, nb_mois, nb_annee,nbenreg=0;
		long idstatMois;
		Boolean anneeExist = false, moisExist = false;
		ConvertDate c = new ConvertDate();
		annee = c.retournerAnnee(date);
		mois=c.retournerMois(date);
		System.out.println("annee:= "+annee+" ,mois:= "+mois);
		if (statistiqueMensuelRepo.count() == 0) {
			System.out.println("table vide, nouvelle insertion");
			StatistiqueMensuel saa = new StatistiqueMensuel(annee,mois, 1);
			addStatistiqueMensuel(saa);
		}
		else{
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
		}
	}
	
	
}
