package org.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.demo.models.AnnulationModifFiche;
import org.demo.models.Convention;
import org.demo.models.Etudiant;
import org.demo.models.FichePFE;
import org.demo.models.Fonctionalite;
import org.demo.models.Problematique;
import org.demo.models.Societe;
import org.demo.models.Technologies;
import org.demo.models.Test;
import org.demo.models.User;
import org.demo.repository.AnnulationModifFicheRepository;
import org.demo.repository.ConventionRepository;
import org.demo.repository.EtudiantRepository;
import org.demo.repository.FichePFERepository;
import org.demo.repository.FonctionaliteRepository;
import org.demo.repository.ProblematiqueRepository;
import org.demo.repository.SocieteRepository;
import org.demo.repository.TechnologiesRepository;
import org.demo.repository.UserRepository;
import org.demo.security.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.demo.exception.ResourceNotFoundException;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/etud")
public class EtudiantController {

	@Autowired
	private FichePFERepository ficherep;

	@Autowired
	private EtudiantRepository etudiantrep;
	
	@Autowired
	private FonctionaliteRepository fonrep;
	
	@Autowired
	private ProblematiqueRepository prorep;
	
	@Autowired
	private SocieteRepository socrep;
	
	@Autowired
	private TechnologiesRepository tecrep;
	
	@Autowired
	private ConventionRepository conrep;
	
	@Autowired
	private AnnulationModifFicheRepository amfrep;
	
	@Autowired
	private UserRepository userrep;
	
	
	
	///////////////etudiant/////////////////////
	
	    @PostMapping("/ajoutetudiant")
	    public Etudiant createEmployee(@Valid @RequestBody Etudiant employee) {
        return etudiantrep.save(employee);
	    }
	    @GetMapping("/test")
	    public String user() {
	        return ("<h1>Welcome User</h1>");
	    }
	    @GetMapping("/listetudiant")
		public List<Etudiant> listEtudiant() {
		return etudiantrep.findAll();	
		}	
	 
	 ///////////////fiche/////////////////////

	    @PostMapping("/createfiche")
		public Object createFiche(@Valid @RequestBody FichePFE fiche ) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Etudiant et = etudiantrep.findByCode(auth.getName());
	    fiche.setEtudiant(et);
	    fiche.setStatus("deposed");
	    return ficherep.save(fiche);	
		}
	    @PostMapping("/savefiche")
		public Object saveFiche(@Valid @RequestBody FichePFE fiche ) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Etudiant et = etudiantrep.findByCode(auth.getName());
	    fiche.setEtudiant(et);
	    fiche.setStatus("saved");
	    return ficherep.save(fiche);	
		}
		@GetMapping("/listfiche")
		public List<FichePFE> listFiche() {
	    return ficherep.findAll();	
		}	
		@GetMapping("/getfiche")
		public FichePFE Fiche() {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Etudiant et = etudiantrep.findByCode(auth.getName());

			FichePFE f = et.getFiche();
			return f;
		}	
		@PutMapping("/updatefiche")
		public Object updatef(@Valid @RequestBody FichePFE fiche) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Etudiant et = etudiantrep.findByCode(auth.getName());

			FichePFE f = et.getFiche();
			f.setTitre(fiche.getTitre());
			f.setDescription(fiche.getDescription());
			ficherep.save(f);
			return f;
		}
		@PutMapping("/updatefichedep")
		public Object updatefdep(@Valid @RequestBody FichePFE fiche) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Etudiant et = etudiantrep.findByCode(auth.getName());

			FichePFE f = et.getFiche();
			f.setTitre(fiche.getTitre());
			f.setDescription(fiche.getDescription());
			f.setStatus("deposed");
			ficherep.save(f);
			return f;
		}
		
     ///////////////fonctionalite/////////////////////
		
		@PostMapping("/createfonc")
		public Object createFoct(@Valid @RequestBody Fonctionalite fonc ) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Etudiant et = etudiantrep.findByCode(auth.getName());

	    fonc.setFichef(et.getFiche());
	    return fonrep.save(fonc);	
		}
		
    ///////////////Problematique/////////////////////
		
		@PostMapping("/createprob")
		public Object createPro(@Valid @RequestBody Problematique prob ) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Etudiant et = etudiantrep.findByCode(auth.getName());

	    prob.setFichep(et.getFiche());
	    return prorep.save(prob);	
		}
		
	///////////////societe/////////////////////
		
		@PostMapping("/createsociete")
		public Object createSoc(@Valid @RequestBody Societe soc ) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Etudiant et = etudiantrep.findByCode(auth.getName());

	    FichePFE f= et.getFiche();
	    //List<FichePFE> hh=new ArrayList<FichePFE>();
	    //hh.add(f);
	    soc.setFiche(f);
	    socrep.save(soc);
	    f.setSoc(soc);
	    ficherep.save(f);    
	    return soc;	
		}
		@PutMapping("/updatesociete")
		public Object updatef(@Valid @RequestBody Societe soc) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Etudiant et = etudiantrep.findByCode(auth.getName());

			Societe s = et.getFiche().getSoc();
			s.setNomSociete(soc.getNomSociete());
			s.setEmailSociete(soc.getEmailSociete());
			s.setAdresseSociete(soc.getAdresseSociete());
			socrep.save(s);
			return s;
		}
	
	///////////////technologies/////////////////////
		
		@PostMapping("/createtech")
		public Object createtech(@Valid @RequestBody Technologies tec ) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Etudiant et = etudiantrep.findByCode(auth.getName());

	    FichePFE f= et.getFiche();
	    //List<FichePFE> hh=new ArrayList<FichePFE>();
	    //hh.add(f);
	    tec.setFiche(f);
	    tecrep.save(tec);
	    f.setTechnologie(tec);
	    ficherep.save(f);    
	    return tec;	
		}
		
	///////////////convention/////////////////////
	
		
		 @PostMapping("/createcon")
			public Object createCon(@Valid @RequestBody Convention conv ) {
		    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Etudiant et = etudiantrep.findByCode(auth.getName());
		    conv.setEtudiant(et);
		    return conrep.save(conv);	
			}
/*		 @PostMapping("/createcontest")
			public Object createCons(@Valid @RequestBody Convention conv ) {
		    //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Etudiant et = etudiantrep.findByCode("173JMT0993");
		    conv.setEtudiant(et);
		    return conrep.save(conv);	
			}*/
		  @GetMapping("/exist")
		    public Object cc() {
			  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			  Etudiant et = etudiantrep.findByCode(auth.getName());
			  Test t = new Test(false,"f");
			  if(et.getConv()!= null) {
				  t.setTest(true);
				  t.setD("t");
			  }
				 
			  return t;
				 
		    }
		  @GetMapping("/getcon")
			public ResponseEntity<Convention> getEmployeeById()
					throws ResourceNotFoundException {
			  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			  Etudiant et = etudiantrep.findByCode(auth.getName());
				Convention c = et.getConv();
				return ResponseEntity.ok().body(c);
			}
		 
	///////////////AnnulationModifFiche/////////////////////
		 
		 
		 @PostMapping("/createamf")
			public Object createAnn(@Valid @RequestBody AnnulationModifFiche amf ) {
		    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Etudiant et = etudiantrep.findByCode(auth.getName());
		    amf.setFiche(et.getFiche());		    
		    return amfrep.save(amf);	
			}

		 
	///////////////Auth/////////////////////
		 
		 @PostMapping("/signin")
			public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
			 Optional<User> et = userrep.findByCode(loginRequest.getUsername());
			 if(et.get().getCIN().equals(loginRequest.getPassword())) {
				return ResponseEntity.ok(new User(et.get().getId(),
												  et.get().getCode(),
											   	  et.get().getName(),
												  et.get().getLastname()));
			}
			 
			 return ResponseEntity.status(400).body("wrong password");		
		 
		 }
		 
		 
		 
		 
}
