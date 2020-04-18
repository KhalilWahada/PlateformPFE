package org.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.demo.models.Etudiant;
import org.demo.models.FichePFE;
import org.demo.repository.EtudiantRepository;
import org.demo.repository.FichePFERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class FichePFEController {
	@Autowired
	private FichePFERepository ficherep;
	@Autowired
	private EtudiantRepository etudiantrep;
	
	///////////////Post/////////////////////
	@PostMapping("/create")
	public Object createFiche(@Valid @RequestBody FichePFE fiche ) {
    Etudiant et = etudiantrep.findById(1);
    fiche.setEtudiant(et);
    return ficherep.save(fiche);	
	}
	///////////////Get/////////////////////
	@GetMapping("/etudiant/fiche")
	public List<FichePFE> listFiche() {
    return ficherep.findAll();	
	}	
	@GetMapping("/etudiant/all")
	public List<Etudiant> listEtudiant() {
	return etudiantrep.findAll();	
	}	
	
}
