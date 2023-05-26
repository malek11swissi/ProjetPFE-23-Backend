package com.pack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pack.models.Typetoken;
import com.pack.repository.TypetokenRepository;


@Component
public class TypetokenService {

	@Autowired
	private TypetokenRepository typetokenRepo;
	
	
	public List<Typetoken> getAllTypetoken() {
		return typetokenRepo.findAll();
	}
	
	public void addTypetoken(Typetoken typetoken) {
		typetokenRepo.save(typetoken);
	}
	
	public Optional<Typetoken> getSingleTypetoken(Long id) {
		return typetokenRepo.findById(id);
	}
	
	public void updateTypetoken(Long id, Typetoken typetoken) {
		typetokenRepo.save(typetoken);
	}
	
	public void deleteTypetoken(Long id) {
		typetokenRepo.deleteById(id);
	}
	
	
}
