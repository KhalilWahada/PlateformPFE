package org.demo.controller;

import javax.validation.Valid;

import org.demo.models.EncadrantSociete;
import org.demo.models.Etudiant;
import org.demo.models.FichePFE;
import org.demo.repository.EncadrantSocieteRepository;
import org.demo.repository.EtudiantRepository;
import org.demo.repository.FichePFERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ensoc")
public class EncadrantSocieteController {
	@Autowired
	private EtudiantRepository etudiantrep;
	@Autowired
	private EncadrantSocieteRepository esocrep;
	@Autowired
	private FichePFERepository ficherep;
	@PostMapping("/create")
	public Object createESoc(@Valid @RequestBody EncadrantSociete esoc ) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Etudiant et = etudiantrep.findByCode(auth.getName());

    FichePFE f= et.getFiche();
    //List<FichePFE> hh=new ArrayList<FichePFE>();
    //hh.add(f);
    esoc.setFiche(f);
    esocrep.save(esoc);
    f.setEsoc(esoc);
    ficherep.save(f);    
    return esoc;	
	}
	@PutMapping("/update")
	public Object updateES(@Valid @RequestBody EncadrantSociete esoc) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Etudiant et = etudiantrep.findByCode(auth.getName());
		EncadrantSociete es = et.getFiche().getEsoc();
		es.setEmail(esoc.getEmail());
		es.setName(esoc.getName());
		es.setLast_name(esoc.getLast_name());
		esocrep.save(es);
		return es;
	}
	
	
}
