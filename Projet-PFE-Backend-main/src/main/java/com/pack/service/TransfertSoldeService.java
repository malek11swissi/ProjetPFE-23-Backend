package com.pack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pack.models.Compteur;
import com.pack.models.TransfertSolde;
import com.pack.repository.TransfertSoldeRepository;

@Component
public class TransfertSoldeService {

	@Autowired
	private TransfertSoldeRepository transfertSoldeRepo;

	public List<TransfertSolde> getAllTransfertSolde() {
		return transfertSoldeRepo.findAll();
	}

	public void addTransfertSolde(TransfertSolde transfertSolde) {
		transfertSoldeRepo.save(transfertSolde);
	}

	public Optional<TransfertSolde> getSingleTransfertSolde(Long id) {
		return transfertSoldeRepo.findById(id);
	}

	public void updateTransfertSolde(Long id, TransfertSolde transfertSolde) {
		transfertSoldeRepo.save(transfertSolde);
	}

	public void deleteTransfertSolde(Long id) {
		transfertSoldeRepo.deleteById(id);
	}

	public TransfertSolde getTransfertSoldeById(Long id) {
		TransfertSolde transfertSolde=new TransfertSolde();
		System.out.println("id en parametre:= "+id);
		List<TransfertSolde> listeTransfertSoldes = new ArrayList<TransfertSolde>();
		listeTransfertSoldes=getAllTransfertSolde();
		for(TransfertSolde p:listeTransfertSoldes)
			if (p.getId()==id)
				transfertSolde=p;
		System.out.println("transfertSoldeX:= "+transfertSolde.toString());
		return transfertSolde;
	}

	
	/*
	 * public List<TransfertSolde> getTransfertSoldesByUser(String username) {
	 * List<TransfertSolde> listeTransfertSoldesActives = new
	 * ArrayList<TransfertSolde>();
	 * transfertSoldeRepo.getTransfertSoldesByUsername(username).forEach(p -> { if
	 * (p.getActive()) { listeTransfertSoldesActives.add(p); } });
	 * System.out.println("affichage transfertSolde actifs");
	 * listeTransfertSoldesActives.forEach(p->{ System.out.println(p.toString());
	 * }); return listeTransfertSoldesActives; }
	 */
	
	/*
	 * public double retournermontantTransfertSolde(TransfertSolde transfertSolde) {
	 * double montant=0; montant=transfertSolde.getToken().getTypetoken().getPrix();
	 * return montant; }
	 * 
	 * public long retournerIdTransfertSolde(long idToken) { long
	 * idTransfertSolde=0; for(TransfertSolde transfertSolde:getAllTransfertSolde())
	 * { if(transfertSolde.getToken().getId()==idToken)
	 * idTransfertSolde=transfertSolde.getId(); }
	 * System.out.println("idTransfertSolde:= "+idTransfertSolde); return
	 * idTransfertSolde; }
	 */

}
