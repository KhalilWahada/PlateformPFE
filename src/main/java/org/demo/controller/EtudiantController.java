package org.demo.controller;

import javax.validation.Valid;

import org.demo.models.Etudiant;
import org.demo.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/etud")
public class EtudiantController {

	 @GetMapping("/user")
	    public String user() {
	        return ("<h1>Welcome User</h1>");
	    }

	@Autowired
	private EtudiantRepository etudiantrep;
	@PostMapping("/add")
	public Etudiant createEmployee(@Valid @RequestBody Etudiant employee) {
		return etudiantrep.save(employee);
	}
}
