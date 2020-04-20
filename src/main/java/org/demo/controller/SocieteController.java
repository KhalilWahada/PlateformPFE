package org.demo.controller;



import javax.validation.Valid;

import org.demo.models.Etudiant;
import org.demo.models.FichePFE;
import org.demo.models.Societe;
import org.demo.repository.EtudiantRepository;
import org.demo.repository.FichePFERepository;
import org.demo.repository.SocieteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/soc")
public class SocieteController {
	@Autowired
	private EtudiantRepository etudiantrep;
	@Autowired
	private SocieteRepository socrep;
	@Autowired
	private FichePFERepository ficherep;
	@PostMapping("/create")
	public Object createSoc(@Valid @RequestBody Societe soc ) {
    Etudiant et = etudiantrep.findById(1);
    FichePFE f= et.getFiche();
    //List<FichePFE> hh=new ArrayList<FichePFE>();
    //hh.add(f);
    soc.setFiche(f);
    socrep.save(soc);
    f.setSoc(soc);
    ficherep.save(f);    
    return soc;	
	}
	@PutMapping("/update")
	public Object updatef(@Valid @RequestBody Societe soc) {
		Societe s = etudiantrep.findById(1).getFiche().getSoc();
		s.setNomSociete(soc.getNomSociete());
		s.setEmailSociete(soc.getEmailSociete());
		s.setAdresseSociete(soc.getAdresseSociete());
		socrep.save(s);
		return s;
	}

}
