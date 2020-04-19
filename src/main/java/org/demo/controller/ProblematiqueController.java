package org.demo.controller;

import javax.validation.Valid;

import org.demo.models.Etudiant;
import org.demo.models.Problematique;
import org.demo.repository.EtudiantRepository;
import org.demo.repository.ProblematiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/prob")
public class ProblematiqueController {
	@Autowired
	private EtudiantRepository etudiantrep;
	@Autowired
	private ProblematiqueRepository prorep;
	@PostMapping("/create")
	public Object createPro(@Valid @RequestBody Problematique prob ) {
    Etudiant et = etudiantrep.findById(1);
    prob.setFichep(et.getFiche());
    return prorep.save(prob);	
	}
}
