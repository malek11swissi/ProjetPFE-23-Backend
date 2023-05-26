package com.pack;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pack.repository.CommandeRepository;
import com.pack.service.CommandeService;
import java.util.*;

@Service
public class ConvertDate {
	@Autowired
	CommandeRepository commandeRepo;

	public String cenvertirDate(Date datee) {
//		String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
		String pattern = "EEEEE; dd-MM-yyyy; HH:mm:ss";
		String pattern2 = "dd-MM-yyyy; HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("fr", "FR"));
		// String date = simpleDateFormat.format(new Date());
		String formatteddate = simpleDateFormat.format(datee);
		System.out.println(formatteddate);
		return formatteddate;
	}

	public void retournerListeCommande() {
		commandeRepo.findAll().forEach(c -> {
			System.out.println(c.toString());
		});
	}

	/*
	 * public String retournerAnnee(Date date) { SimpleDateFormat getYearFormat =
	 * new SimpleDateFormat("yyyy"); String currentYear =
	 * getYearFormat.format(date); return currentYear; }
	 * 
	 * public String retournerMois(Date date) { SimpleDateFormat getYearFormat = new
	 * SimpleDateFormat("mm"); String currentMois = getYearFormat.format(date);
	 * return currentMois; }
	 */

	public int retournerAnnee(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dateYear = calendar.get(Calendar.YEAR);
		System.out.println(dateYear);
		return dateYear;
	}

	public int retournerMois(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int datemonth= calendar.get(Calendar.MONTH)+1;
		System.out.println(datemonth);
		return datemonth;
	}

	public String extraireAnnee(String date) {
		// System.out.println("date length " + date.length());
		return date.substring(6, 10);
	}

	public String extraireMois(String date) {
		// System.out.println("date length " + date.length());
		return date.substring(10, 12);
	}

	public void gereMap() {
		HashMap<Integer, String> map = new HashMap<Integer, String>();// Creating HashMap
		map.put(1, "Mango"); // Put elements in Map
		map.put(2, "Apple");
		map.put(3, "Banana");
		map.put(4, "Grapes");
		map.put(3, "Orange");
		// map.remove(2);
		map.put(5, "Figue");
		System.out.println(map.containsKey(6));
		if (map.replace(4, "Grapes", "newGrapes"))
			System.out.println("ok");
		System.out.println("Iterating Hashmap...");
		for (Entry<Integer, String> m : map.entrySet()) {
			System.out.println(m.getKey() + " " + m.getValue());
		}
	}

	public void remplirMapPerAnnee() {
		String[] dates = { "22-09-2022; 15:06:20", "22-09-2022; 15:06:20", "22-10-2021; 15:06:20",
				"02-02-2021; 15:06:20", "22-09-2022; 15:06:20", "10-01-2020; 15:06:20", "15-09-2020; 15:06:20" };
		int ancienValeur = 0;
		String annee = "";
		HashMap<String, Integer> map = new HashMap<String, Integer>();// Creating HashMap
		for (String date : dates) {
			annee = extraireAnnee(date);
			System.out.println(annee);
			if (map.containsKey(annee)) {
				System.out.println("ok");
				// recuperer l'ancien valeur
				ancienValeur = map.get(annee);
				System.out.println("ancien valeur " + ancienValeur);
				map.replace(annee, ancienValeur, ancienValeur + 1);
			} else
				map.put(annee, 1);
			ancienValeur = 0;
		}
		System.out.println("affichage statistiques");
		afficherstatistiquesMapPerAnnee(map);
	}

	public void afficherstatistiquesMapPerAnnee(HashMap<String, Integer> map) {
		for (Entry<String, Integer> m : map.entrySet()) {
			System.out.println(m.getKey() + " " + m.getValue());
		}

	}

	public static void main(String[] args) {
		ConvertDate c = new ConvertDate();
		System.out.println("date convertis " + c.cenvertirDate(new Date()));
		c.retournerAnnee(new Date());
		/*
		 * System.out.println(c.extraireAnnee(c.cenvertirDate(new Date())));
		 * System.out.println(c.extraireMois(c.cenvertirDate(new Date()))); c.gereMap();
		 * c.remplirMapPerAnnee();
		 */
	}

}
