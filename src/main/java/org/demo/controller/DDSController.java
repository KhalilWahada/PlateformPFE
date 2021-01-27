package org.demo.controller;


import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.xml.ws.Response;

import org.demo.exception.ResourceNotFoundException;
import org.demo.models.AnnulationModifFiche;
import org.demo.models.Convention;
import org.demo.models.Directiondesstages;
import org.demo.models.Emploidujour;
import org.demo.models.Enseignant;
import org.demo.models.Etudiant;
import org.demo.models.FichePFE;
import org.demo.models.Fonctionalite;
import org.demo.models.History;
import org.demo.models.Session;
import org.demo.models.Soutenance;
import org.demo.repository.AnnulationModifFicheRepository;
import org.demo.repository.ConventionRepository;
import org.demo.repository.DDSRepository;
import org.demo.repository.EnseignantRepository;
import org.demo.repository.EtudiantRepository;
import org.demo.repository.FichePFERepository;
import org.demo.repository.HistoryRepository;
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






@CrossOrigin(origins = "*", allowedHeaders = "*")
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
	@Autowired
	private EtudiantRepository etrep;
	@Autowired
	private ConventionRepository convrep;
	@Autowired
	private DDSRepository Drep;
	@Autowired
	private HistoryRepository historyrep;
	@Autowired
	private AnnulationModifFicheRepository amfrep;
	///////////////////creation de test
	@PostMapping("/test")
	public Object test() {
		FichePFE fiche=new FichePFE();
		fiche.setStatus("NON TRAITER");
		Directiondesstages E=new Directiondesstages();
		E.setName("DDS");
		Drep.save(E);
/*		Etudiant ET= new Etudiant();
		etrep.save(ET);
    */return ficherep.save(fiche);	
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

    public void sendSimpleMessage() {
        
    	String to = "irad.amri@esprit.tn";
    	String subject="test";
    	String text ="test";
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);
        
    }
	
	////////validation et affectation
	@PutMapping("/fiche/validation/{idfiche}/affectation/{idprof}")
	public Object affectationprof (@PathVariable(value = "idfiche") Long ficheId,@PathVariable(value = "idprof") Long profId)throws ResourceNotFoundException  {
			FichePFE fiche = ficherep.findById(ficheId).orElseThrow(() -> new ResourceNotFoundException("not found "));
			Enseignant enseignant = enseignantrep.findById(profId).orElseThrow(() -> new ResourceNotFoundException("not found "));
			enseignant.setNumberEncadrement(enseignant.getNumberEncadrement() + 1);
			fiche.setEnseignant(enseignant);
			enseignantrep.save(enseignant);
			fiche.setStatus("VALIDER_PAR_DDS_e_a");
			
			//this.sendSimpleMessage("irad.amri@esprit.tn");
			//this.sendSimpleMessage(fiche.getEtudiant().getEmail());
			
			return ficherep.save(fiche);						
	}
	////////////////////validation
	@PutMapping("/fiche/validation/{idfiche}")
	public Object valider (@PathVariable(value = "idfiche") Long ficheId)throws ResourceNotFoundException  {
			FichePFE fiche = ficherep.findById(ficheId).orElseThrow(() -> new ResourceNotFoundException("not found "));
			fiche.setStatus("VALIDER_PAR_DDS");
			//Modification mnew = new Modification();
			History Hnew =new History();
			Hnew.setDate(new Date());
			Hnew.setAction("validation");
			Hnew.setNote("test");
			Hnew.setUser("user");
			Hnew.setFichehistory(fiche);
			
			
			
			historyrep.save(Hnew);
			//this.sendSimpleMessage("khalil.wahada@esprit.tn");
			//this.sendSimpleMessage("irad.amri@esprit.tn");
			return ficherep.save(fiche);						
	}
	////////////////////ouvririu modification
	@PutMapping("/fiche/demande/{idfiche}")
	public Object admodification (@PathVariable(value = "idfiche") Long ficheId)throws ResourceNotFoundException  {
			FichePFE fiche = ficherep.findById(ficheId).orElseThrow(() -> new ResourceNotFoundException("not found "));
			fiche.setStatus("deposed");
			History Hnew =new History();
			Hnew.setDate(new Date());
			Hnew.setAction(fiche.getAmf().getEtat());
			Hnew.setNote(fiche.getAmf().getCause());
			Hnew.setUser("DDS");
			Hnew.setFichehistory(fiche);
			AnnulationModifFiche  amf= new AnnulationModifFiche();
			amf=fiche.getAmf();fiche.setAmf(null);

			amfrep.deleteById(amf.getId());
			
			
			
			historyrep.save(Hnew);
			//this.sendSimpleMessage("khalil.wahada@esprit.tn");
			//this.sendSimpleMessage("irad.amri@esprit.tn");
			return ficherep.save(fiche);						
	}
	////////////////////refus
	@PutMapping("/fiche/refus/{idfiche}")
	public Object refus (@PathVariable(value = "idfiche") Long ficheId)throws ResourceNotFoundException  {
			FichePFE fiche = ficherep.findById(ficheId).orElseThrow(() -> new ResourceNotFoundException("not found "));
			fiche.setStatus("saved");
			//this.sendSimpleMessage("khalil.wahada@esprit.tn");
			//this.sendSimpleMessage("irad.amri@esprit.tn");
			return ficherep.save(fiche);						
	}
	////////////////////annulation
	@PutMapping("/fiche/annulation/{idfiche}")
	public Object Annulation (@PathVariable(value = "idfiche") Long ficheId)throws ResourceNotFoundException  {
			FichePFE fiche = ficherep.findById(ficheId).orElseThrow(() -> new ResourceNotFoundException("not found "));
			fiche.setStatus("PFE_ANNULER");
			fiche.getEnseignant().setNumberEncadrement(fiche.getEnseignant().getNumberEncadrement()-1);
			History Hnew =new History();
			Hnew.setDate(new Date());
			Hnew.setAction(fiche.getAmf().getEtat());
			Hnew.setNote(fiche.getAmf().getCause());
			Hnew.setUser("DDS");
			Hnew.setFichehistory(fiche);
			AnnulationModifFiche  amf= new AnnulationModifFiche();
			amf=fiche.getAmf();fiche.setAmf(null);

			amfrep.deleteById(amf.getId());
			
			
			
			historyrep.save(Hnew);
			//this.sendSimpleMessage("khalil.wahada@esprit.tn");
			//this.sendSimpleMessage("irad.amri@esprit.tn");
			return ficherep.save(fiche);	
	}
	////////////////////suppression
	@DeleteMapping("/fiche/delete/{idfiche}")
	public Map<String, Boolean> deletefiche (@PathVariable(value = "idfiche") Long ficheId)throws ResourceNotFoundException  {
		FichePFE fiche = ficherep.findById(ficheId).orElseThrow(() -> new ResourceNotFoundException("not found "));
		ficherep.delete(fiche);
		Enseignant enseignant =fiche.getEnseignant();
		enseignant.setNumberEncadrement(enseignant.getNumberEncadrement() - 1);
		enseignantrep.save(enseignant);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	////////////add session///
	@PostMapping("/session/create")
	public Object createsession(@Valid @RequestBody Session session ) {
			Session S=new Session();
			S.setNom(session.getNom());
			S.setDateDebut(session.getDateDebut());
			S.setDateFin(session.getDateFin());
			
			return sessionrep.save(S);	
	}
	////////////depot dossier pfe
	@PutMapping("/session/depot/{idfiche}")
	public Object depot (@PathVariable(value = "idfiche") Long ficheId)throws ResourceNotFoundException  {
			FichePFE fiche = ficherep.findById(ficheId).orElseThrow(() -> new ResourceNotFoundException("not found "));
			fiche.setStatus("Dossier_deposer");
			// Sessionrelie a fiche PFE affecter session 
			//this.sendSimpleMessage("khalil.wahada@esprit.tn");
			//this.sendSimpleMessage("irad.amri@esprit.tn");
			return ficherep.save(fiche);						
	}
	////////////affecter soutenace
	@PostMapping("/soutenance/affecter")
	public Object affecter (@Valid @RequestBody Soutenance soutenance )  {
			//FichePFE fiche = ficherep.findById(ficheId).orElseThrow(() -> new ResourceNotFoundException("not found "));
			//this.sendSimpleMessage("khalil.wahada@esprit.tn");
			//this.sendSimpleMessage("irad.amri@esprit.tn");
			//fiche.setSoutenance(soutenance);
			return 	soutenancerep.save(soutenance);
 					
	}
	////////////getconventions
	@GetMapping("/conventions/all")
	public List<Convention> listConventions() {
	List<Convention> all = convrep.findAll();
//	all.removeIf(fiche -> fiche.getStatus().contains( "saved"));
	return all;
}	
	////////////getfiche
	@GetMapping("/fiches/all")
	public List<FichePFE> listFiche() {
		List<FichePFE> all = ficherep.findAll();
			all.removeIf(fiche -> fiche.getStatus().contains( "saved"));
			return all;
	}	
	////////////getfichenontraiter
	@GetMapping("/fiches/nontraiter")
	public List<FichePFE> listFichenontrailer() {
	List<FichePFE> nontraiter=ficherep.findAll();
	nontraiter.removeIf(fiche -> !fiche.getStatus().contains( "deposed"));
	return nontraiter;
	}	
	////////////getficheencoursdupfe
	@GetMapping("/fiches/traiter")
	public List<FichePFE> listFichetrailer() {
	List<FichePFE> traiter=ficherep.findAll();
	traiter.removeIf(fiche -> fiche.getStatus().contains( "saved"));
	traiter.removeIf(fiche -> fiche.getStatus().contains( "deposed"));
	traiter.removeIf(fiche -> fiche.getStatus().contains( "PFE_ANNULER"));
	return traiter;
	}	
	////////////annuler soutenance
	@DeleteMapping("/soutenance/annuler/{id}")
	public void annulersoutenance (@PathVariable(value = "id") Long Id)throws ResourceNotFoundException  {
		Soutenance soutenance = soutenancerep.findById(Id).orElseThrow(() -> new ResourceNotFoundException("not found "));
		soutenancerep.delete(soutenance);
		
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
	////////////getsoutenance
	@GetMapping("/soutenance/find/all")
	public List<Soutenance> listSoutenance() {
	return soutenancerep.findAll();
	}	
	////////////getsoutenance
		@GetMapping("/soutenance/find/{idsoutenance}")
		public Soutenance findsoutenance(@PathVariable(value = "idsoutenance") Long Id) throws ResourceNotFoundException
		{ 
			Soutenance soutenanceB = soutenancerep.findById(Id).orElseThrow(() -> new ResourceNotFoundException("not found "));

			return soutenanceB;
		}	
////////////getsoutenance
	@GetMapping("/soutenance/find/fiche/{idfiche}")
	public Soutenance findbyfichesoutenance(@PathVariable(value = "idfiche") Long ficheId) throws ResourceNotFoundException
	{ 
		FichePFE fiche = ficherep.findById(ficheId).orElseThrow(() -> new ResourceNotFoundException("not found "));

		Soutenance soutenanceB =fiche.getSoutenance();
		return soutenanceB;
	}	
		////////////getsession
	@GetMapping("/soutenance/date/fiche/{idfiche}")
	public List<Timestamp> finddatefichesoutenance(@PathVariable(value = "idfiche") Long ficheId) throws ResourceNotFoundException
	{ 
		FichePFE fiche = ficherep.findById(ficheId).orElseThrow(() -> new ResourceNotFoundException("not found "));
		List<Emploidujour> EMJ = fiche.getEnseignant().getEmploidutemp();
		List<Timestamp> dates =new ArrayList<Timestamp>();
		//Date d =new Date() ;
		Timestamp ts0 = new Timestamp(new Date().getTime());
		Timestamp ts1 = new Timestamp(new Date().getTime());
		Timestamp ts2 = new Timestamp(new Date().getTime());

		EMJ.forEach(emploi-> { 
			Calendar cal = 	Calendar.getInstance();	
					cal.setTime(emploi.getDate());

			if (emploi.getSeance1().contains("libre"))	{
				cal.set(Calendar.HOUR_OF_DAY, 8);
				cal.set(Calendar.MINUTE, 0);
			ts0.setTime(cal.getTime().getTime());
				dates.add(ts0);
				cal.add(Calendar.MINUTE, 30);
				ts1.setTime(cal.getTime().getTime());
					dates.add(ts1);
					cal.add(Calendar.MINUTE, 30);
					ts2.setTime(cal.getTime().getTime());
						dates.add(ts2);
				
				
			}
		});
		
		return dates ;
	}	
	////////////getsession
	@GetMapping("/session/al")
		public List<Session> listSession() {
		return sessionrep.findAll();
	}	
	@GetMapping("/session/all")
	public ResponseEntity<Session> getSession() throws ResourceNotFoundException {
		
		Session c = sessionrep.findById((long) 1).orElseThrow(() -> new ResourceNotFoundException("not found "));
		return ResponseEntity.ok().body(c);
	}

		////////////////////suppression session
		@DeleteMapping("/session/delete/{id}")
		public Map<String, Boolean> deletesession (@PathVariable(value = "id") Long ficheId)throws ResourceNotFoundException  {
			Session session = sessionrep.findById(ficheId).orElseThrow(() -> new ResourceNotFoundException("not found "));
			sessionrep.delete(session);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
	}
		
			
	}
