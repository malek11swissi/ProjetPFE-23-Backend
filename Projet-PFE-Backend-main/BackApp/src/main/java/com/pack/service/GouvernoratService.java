package com.pack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pack.models.Gouvernorat;
import com.pack.repository.GouvernoratRepository;


@Component
public class GouvernoratService {

	@Autowired
	private GouvernoratRepository gouvernoratRepo;
	
	
	public List<Gouvernorat> getAllGouvernorat() {
		return gouvernoratRepo.findAll();
	}
	
	public void addGouvernorat(Gouvernorat gouvernorat) {
		gouvernoratRepo.save(gouvernorat);
	}
	
	public Optional<Gouvernorat> getSingleGouvernorat(Long id) {
		return gouvernoratRepo.findById(id);
	}
	
	public void updateGouvernorat(Long id, Gouvernorat gouvernorat) {
		gouvernoratRepo.save(gouvernorat);
	}
	
	public void deleteGouvernorat(Long id) {
		gouvernoratRepo.deleteById(id);
	}
	
	
}
