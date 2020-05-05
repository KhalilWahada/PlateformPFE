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
import org.demo.models.Fonctionalite;
import org.demo.models.Session;
import org.demo.models.Soutenance;
import org.demo.repository.EnseignantRepository;
import org.demo.repository.EtudiantRepository;
import org.demo.repository.FichePFERepository;
import org.demo.repository.ProblematiqueRepository;
import org.demo.repository.SessionRepository;
import org.demo.repository.SoutenanceRepository;
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
	private EnseignantRepository enseignantrep;
	@Autowired
	private SessionRepository sessionrep;
	@Autowired
	private SoutenanceRepository soutenancerep;
	
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
			this.sendSimpleMessage("irad.amri@esprit.tn");
			//this.sendSimpleMessage(fiche.getEtudiant().getEmail());
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
	////////////add session///
	@PostMapping("/session/create")
	public Object createsession(@Valid @RequestBody Session session ) {
			return sessionrep.save(session);	
	}
	////////////depot dossier pfe
	@PutMapping("/session/depot/{idfiche}")
	public Object depot (@PathVariable(value = "idfiche") Long ficheId)throws ResourceNotFoundException  {
			FichePFE fiche = ficherep.findById(ficheId).orElseThrow(() -> new ResourceNotFoundException("not found "));
			fiche.setStatus("Dossier_deposer");
			// Sessionrelie a fiche PFE
			//this.sendSimpleMessage("khalil.wahada@esprit.tn");
			this.sendSimpleMessage("irad.amri@esprit.tn");
			return ficherep.save(fiche);						
	}
	////////////affecter soutenace
	@PutMapping("/soutenance/affecter/{idfiche}")
	public Object affecter (@PathVariable(value = "idfiche") Long ficheId,@Valid @RequestBody Soutenance soutenance )throws ResourceNotFoundException  {
			FichePFE fiche = ficherep.findById(ficheId).orElseThrow(() -> new ResourceNotFoundException("not found "));
			//this.sendSimpleMessage("khalil.wahada@esprit.tn");
			this.sendSimpleMessage("irad.amri@esprit.tn");
			soutenancerep.save(soutenance);
			fiche.setSoutenance(soutenance);
			return ficherep.save(fiche); 					
	}
	////////////getfiche
	@GetMapping("/fiches/all")
	public List<FichePFE> listFiche() {
			return ficherep.findAll();
	}	
	///////////update session
	@PutMapping("/session/{idsession}")
	public Object updatesession(@PathVariable(value = "idsession") Long Id,@Valid @RequestBody Session session) throws ResourceNotFoundException {
		
		Session sessionB = sessionrep.findById(Id).orElseThrow(() -> new ResourceNotFoundException("not found "));;
		sessionB.setNom(session.getNom());
		sessionB.setDateDebut(session.getDateDebut());
		return sessionrep.save(sessionB);
	}
	//////////update soutenance
	@PutMapping("/soutenance/{idsoutenance}")
	public Object updatesoutenance(@PathVariable(value = "idsoutenance") Long Id,@Valid @RequestBody Soutenance soutenance) throws ResourceNotFoundException
	{
	
		Soutenance soutenanceB = soutenancerep.findById(Id).orElseThrow(() -> new ResourceNotFoundException("not found "));;
		soutenanceB.setDuree(soutenance.getDuree());
		soutenanceB.setDateSoutenance(soutenance.getDateSoutenance());
		return soutenancerep.save(soutenanceB);
	}	
	}
