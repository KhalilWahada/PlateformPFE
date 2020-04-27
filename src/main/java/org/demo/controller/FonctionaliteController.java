package org.demo.controller;

import javax.validation.Valid;

import org.demo.models.Etudiant;
import org.demo.models.Fonctionalite;
import org.demo.repository.EtudiantRepository;
import org.demo.repository.FonctionaliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/fonc")
public class FonctionaliteController {
	@Autowired
	private EtudiantRepository etudiantrep;
	@Autowired
	private FonctionaliteRepository fonrep;
	@PostMapping("/create")
	public Object createFoct(@Valid @RequestBody Fonctionalite fonc ) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Etudiant et = etudiantrep.findByCode(auth.getName());

    fonc.setFichef(et.getFiche());
    return fonrep.save(fonc);	
	}
	

	
	
}
