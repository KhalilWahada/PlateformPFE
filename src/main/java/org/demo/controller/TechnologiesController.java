package org.demo.controller;

import javax.validation.Valid;

import org.demo.models.Etudiant;
import org.demo.models.FichePFE;
import org.demo.models.Technologies;
import org.demo.repository.EtudiantRepository;
import org.demo.repository.FichePFERepository;
import org.demo.repository.TechnologiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tech")
public class TechnologiesController {
	@Autowired
	private EtudiantRepository etudiantrep;
	@Autowired
	private TechnologiesRepository tecrep;
	@Autowired
	private FichePFERepository ficherep;
	@PostMapping("/create")
	public Object createtech(@Valid @RequestBody Technologies tec ) {
    Etudiant et = etudiantrep.findById(1);
    FichePFE f= et.getFiche();
    //List<FichePFE> hh=new ArrayList<FichePFE>();
    //hh.add(f);
    tec.setFiche(f);
    tecrep.save(tec);
    f.setTechnologie(tec);
    ficherep.save(f);    
    return tec;	
	}

}
