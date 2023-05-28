package com.pack.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.ERole;
import com.pack.models.Typetoken;
import com.pack.service.TypetokenService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@RestController
public class TypetokenUsedbyUserController {
	

	@Autowired
	TypetokenService typetokenService;

 

	@RequestMapping(method = RequestMethod.GET, value = "/typetokensByUser") 
	public List<Typetoken> getTypetokenByUser() {
		return (List<Typetoken>) typetokenService.getAllTypetoken();

	}


}
