package org.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.xml.ws.Response;

import org.demo.exception.ResourceNotFoundException;
import org.demo.models.Enseignant;
import org.demo.models.Etudiant;
import org.demo.models.FichePFE;
import org.demo.repository.EnseignantRepository;
import org.demo.repository.EtudiantRepository;
import org.demo.repository.FichePFERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;






@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/dds")
public class DDSController {
	@Autowired
	private FichePFERepository ficherep;
	@Autowired
	private EtudiantRepository etudiantrep;
	@Autowired
	private EnseignantRepository enseignantrep;
	
	///////////////////creation de test
	@PostMapping("/test")
	public Object test() {
		FichePFE fiche=new FichePFE();
		fiche.setStatus("NON TRAITER");
		Enseignant E=new Enseignant();
		E.setName("TEST");
		enseignantrep.save(E);
    return ficherep.save(fiche);	
	}
	/*////////////////
	@PutMapping("/validation/{idfiche}")
	public void validationfiche(@PathVariable(value = "idfiche") Long ficheId)
			throws ResourceNotFoundException  {
			FichePFE fiche = ficherep.findById(ficheId).orElseThrow(() -> new ResourceNotFoundException("not found "));
			fiche.setMotifEnseignant("VALIDER PAR DDS");
			ficherep.save(fiche);
	}
		/////*/
	/////////mailing
	@Autowired
    public JavaMailSender emailSender;
	@GetMapping("/send")

    public void sendSimpleMessage( String to) {
        
    	//String to = "irad.amri@esprit.tn";
    	String subject="test";
    	String text ="test";
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);
        
    }
	////////validation et affectation
	@PutMapping("/validation/{idfiche}/affectation/{idprof}")
	public Object affectationprof (@PathVariable(value = "idfiche") Long ficheId,@PathVariable(value = "idprof") Long profId)throws ResourceNotFoundException  {
			FichePFE fiche = ficherep.findById(ficheId).orElseThrow(() -> new ResourceNotFoundException("not found "));
			Enseignant enseignant = enseignantrep.findById(profId).orElseThrow(() -> new ResourceNotFoundException("not found "));
			fiche.setEnseignant(enseignant);
			fiche.setStatus("VALIDER_PAR_DDS");
			//this.sendSimpleMessage("khalil.wahada@esprit.tn");
			this.sendSimpleMessage("irad.amri@esprit.tn");
			return ficherep.save(fiche);						
	}
	////////////////////refus
	@PutMapping("/refus/{idfiche}")
	public Object refus (@PathVariable(value = "idfiche") Long ficheId)throws ResourceNotFoundException  {
			FichePFE fiche = ficherep.findById(ficheId).orElseThrow(() -> new ResourceNotFoundException("not found "));
			fiche.setStatus("REFUSER_PAR_DDS");
			//this.sendSimpleMessage("khalil.wahada@esprit.tn");
			this.sendSimpleMessage("irad.amri@esprit.tn");
			return ficherep.save(fiche);						
	}
	////////////////////refus
	@PutMapping("/anuulation/{idfiche}")
	public Object Annulation (@PathVariable(value = "idfiche") Long ficheId)throws ResourceNotFoundException  {
			FichePFE fiche = ficherep.findById(ficheId).orElseThrow(() -> new ResourceNotFoundException("not found "));
			fiche.setStatus("PFE_ANNULER");
			//this.sendSimpleMessage("khalil.wahada@esprit.tn");
			this.sendSimpleMessage("irad.amri@esprit.tn");
			return ficherep.save(fiche);	
	}
	////////////////////suppression
	@DeleteMapping("/delete/{idfiche}")
	public Map<String, Boolean> deletefiche (@PathVariable(value = "idfiche") Long ficheId)throws ResourceNotFoundException  {
		FichePFE fiche = ficherep.findById(ficheId).orElseThrow(() -> new ResourceNotFoundException("not found "));
		ficherep.delete(fiche);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	}
