package org.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.demo.models.Etudiant;
import org.demo.models.FichePFE;
import org.demo.repository.EtudiantRepository;
import org.demo.repository.FichePFERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/fiche")
public class FichePFEController {
	@Autowired
	private FichePFERepository ficherep;
	@Autowired
	private EtudiantRepository etudiantrep;
	
	///////////////Post/////////////////////
	@PostMapping("/create")
	public Object createFiche(@Valid @RequestBody FichePFE fiche ) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Etudiant et = etudiantrep.findByCode(auth.getName());
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
	@PutMapping("/update")
	public Object updatef(@Valid @RequestBody FichePFE fiche) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Etudiant et = etudiantrep.findByCode(auth.getName());

		FichePFE f = et.getFiche();
		f.setTitre(fiche.getTitre());
		f.setDescription(fiche.getDescription());
		ficherep.save(f);
		return f;
	}
	
	
}
