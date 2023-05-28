package com.pack.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pack.ConvertDate;
import com.pack.models.BarChart;
import com.pack.models.Chart;
import com.pack.models.StatAnnulleToken;

import com.pack.repository.StatAnnulleTokenRepository;


@Component
public class StatistiqueAnnuelTokenService {

	@Autowired
	private StatAnnulleTokenRepository StatAnnulleTokenRepo;

	public ArrayList<StatAnnulleToken> getAllStatAnnulleToken() {
		return (ArrayList<StatAnnulleToken>) StatAnnulleTokenRepo.findAll();
	}
	
	public ArrayList<StatAnnulleToken> getAllStatAnnulleTokenTriee() {
		return (ArrayList<StatAnnulleToken>) StatAnnulleTokenRepo.findAllTrie();
	}
	
	

	public void addStatistiqueAnnuelToken(StatAnnulleToken statistiqueAnnuel) {
		StatAnnulleTokenRepo.save(statistiqueAnnuel);
	}

	public Optional<StatAnnulleToken> getSingleStatistiqueAnnuel(Long id) {
		return StatAnnulleTokenRepo.findById(id);
	}
	
	public StatAnnulleToken getSingleStatistiqueAnnuelType(int annee, String typeToken) {
		return StatAnnulleTokenRepo.findByTypeTokenAnnee(typeToken, annee);
	}

	public void updateStatistiqueAnnuel(Long id, StatAnnulleToken statistiqueAnnuel) {
		StatAnnulleTokenRepo.save(statistiqueAnnuel);
	}

	public void deleteStatistiqueAnnuel(Long id) {
		StatAnnulleTokenRepo.deleteById(id);
	}

	public void ajouterStatAnnuel(Date date, String typeToken) {
		int annee = 0, mois, nb_mois, nb_annee,nbenreg=0;
		long idstatAnne;
		Boolean anneeExist = false, moisExist = false;
		ConvertDate c = new ConvertDate();
		annee = c.retournerAnnee(date);
		if(StatAnnulleTokenRepo.findByTypeTokenAnnee(typeToken, annee)==null)
		{
			StatAnnulleToken saa = new StatAnnulleToken(annee, 1,typeToken);
			addStatistiqueAnnuelToken(saa);
		}
		else
			
		
		{
			StatAnnulleToken st =StatAnnulleTokenRepo.findByTypeTokenAnnee(typeToken, annee);
				st.setNb(st.getNb()+1);	
			updateStatistiqueAnnuel(st.getId(), st);
					
			}
			
		}

		public Chart getStatsChart()
		{
			Chart chart = new Chart();
			List<StatAnnulleToken> stats = StatAnnulleTokenRepo.findAll();
			List<String> annee = new ArrayList<String>();
			List<String> typeTokens = new ArrayList<String>();
			for(StatAnnulleToken s : stats)
			{
				annee.add(""+s.getAnnee());
				
				typeTokens.add(s.getTypeToken());
			}		
			// remove duplications
			HashSet<String> set = new HashSet<>(annee);
			List<String> anneeList = new ArrayList<>(set);
			chart.setAxisXData(anneeList);
			List<BarChart> series = new ArrayList<>()	;
			for(String item:typeTokens)
			{
				BarChart barChart = new BarChart();
				barChart.setName(item);
				barChart.setType("bar");
				List<Integer> values = new ArrayList<>();
				// count 
				for(String year:anneeList)
				{
				
					if(StatAnnulleTokenRepo.existsByTypeTokenAndAnnee(item, Integer.parseInt(year)))
					{
						StatAnnulleToken statAnnulleToken =  StatAnnulleTokenRepo.findByTypeTokenAnnee(item, Integer.parseInt(year));
					
						values.add(statAnnulleToken.getNb());
					} else {
						values.add(0);
					}

				
				}
				barChart.setData(values);
				series.add(barChart);
			}
			chart.setSeries(series);
			return chart ;
		}
	}

	

