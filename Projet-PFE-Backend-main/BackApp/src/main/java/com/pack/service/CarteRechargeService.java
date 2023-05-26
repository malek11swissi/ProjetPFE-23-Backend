package com.pack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pack.models.Compteur;
import com.pack.models.Typetoken;
import com.pack.models.CarteRecharge;
import com.pack.repository.CarteRechargeRepository;

@Component
public class CarteRechargeService {

	@Autowired
	private CarteRechargeRepository carteRechargeRepo;

	public List<CarteRecharge> getAllCarteRecharge() {
		return carteRechargeRepo.findAll();
	}

	public void addCarteRecharge(CarteRecharge carteRecharge) {
		carteRechargeRepo.save(carteRecharge);
	}

	public Optional<CarteRecharge> getSingleCarteRecharge(Long id) {
		return carteRechargeRepo.findById(id);
	}

	public void updateCarteRecharge(Long id, CarteRecharge carteRecharge) {
		carteRechargeRepo.save(carteRecharge);
	}

	public void deleteCarteRecharge(Long id) {
		carteRechargeRepo.deleteById(id);
	}

	public List <CarteRecharge> getCarteRechargeBycentreName(String centreRechargeusername) {
		List<CarteRecharge> listeCarteRecharges = new ArrayList<CarteRecharge>();
		listeCarteRecharges = carteRechargeRepo.getCarteRechargesByCentreRecharge(centreRechargeusername);
		listeCarteRecharges.forEach(c->{
			System.out.println(c.toString());
		});
		return listeCarteRecharges;
	}

	public void updateCarteRechargeBycentreName(String centreRechargeusername,double prixtypetoken) {
		System.out.println("je suis dans updateCarteRechargeBycentreName");
		List<CarteRecharge> listeCarteRecharges = new ArrayList<CarteRecharge>();
		listeCarteRecharges = carteRechargeRepo.getCarteRechargesByCentreRecharge(centreRechargeusername);
		listeCarteRecharges.forEach(c->{
			if(c.getTypetoken().getPrix()==prixtypetoken)
			{
				c.setNombre(c.getNombre()-1);
				System.out.println(c.toString());
			}
		});
	}

	
	
	public long retournerIdCarteRecharge(long idTypetoken) {
		long idCarteRecharge = 0;
		for (CarteRecharge carteRecharge : getAllCarteRecharge()) {
			if (carteRecharge.getTypetoken().getId() == idTypetoken)
				idCarteRecharge = carteRecharge.getId();
		}
		System.out.println("idCarteRecharge:= " + idCarteRecharge);
		return idCarteRecharge;
	}

}
