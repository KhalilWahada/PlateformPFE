package org.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.demo.models.AnnulationModifFiche;
import org.demo.models.Convention;
import org.demo.models.Etudiant;
import org.demo.models.FichePFE;
import org.demo.models.Fichetest;
import org.demo.models.Fonctionalite;
import org.demo.models.Problematique;
import org.demo.models.Societe;
import org.demo.models.Technologies;
import org.demo.models.Test;
import org.demo.repository.AnnulationModifFicheRepository;
import org.demo.repository.ConventionRepository;
import org.demo.repository.EncadrantSocieteRepository;
import org.demo.repository.EtudiantRepository;
import org.demo.repository.FichePFERepository;
import org.demo.repository.FonctionaliteRepository;
import org.demo.repository.ProblematiqueRepository;
import org.demo.repository.SocieteRepository;
import org.demo.repository.TechnologiesRepository;
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
	private EncadrantSocieteRepository ensocrep;
	
	@Autowired
	private TechnologiesRepository tecrep;
	
	@Autowired
	private ConventionRepository conrep;
	
	@Autowired
	private AnnulationModifFicheRepository amfrep;
	

	
	
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

		@PostMapping("/savefichefull")
		public Object saveFiched(@Valid @RequestBody Fichetest fiche) {			 
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();	   
		    Etudiant et = etudiantrep.findByCode(auth.getName());	    
		    fiche.getF().setEtudiant(et);    
		    fiche.getF().setStatus("saved");   
		    et.setFiche(fiche.getF());
		    socrep.save(fiche.getSoc());
		    fiche.getF().setSoc(fiche.getSoc());
		    ensocrep.save(fiche.getEs());
		    fiche.getF().setEsoc(fiche.getEs());
		    ficherep.save(fiche.getF());
		    for(Fonctionalite f:fiche.getFoncts()) {
		    	f.setFichef(et.getFiche());
		    	fonrep.save(f);
		    }
		    for(Problematique p:fiche.getProbs()) {
		    	p.setFichep(et.getFiche());
		    	prorep.save(p);
		    }
		    
		    for(Technologies t:fiche.getTech()) {	    	 
		    	t.setFiche(et.getFiche());	    	  
		    	tecrep.save(t);
		    	  	    }
		   
		    return et.getFiche();	
		}
		@PostMapping("/diposefichefull")
		public Object saveFicheddispose(@Valid @RequestBody Fichetest fiche) {			 
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();	   
	    Etudiant et = etudiantrep.findByCode(auth.getName());	    
	    fiche.getF().setEtudiant(et);    
	    fiche.getF().setStatus("diposed");   
	    et.setFiche(fiche.getF());
	    socrep.save(fiche.getSoc());
	    fiche.getF().setSoc(fiche.getSoc());
	    ensocrep.save(fiche.getEs());
	    fiche.getF().setEsoc(fiche.getEs());
	    ficherep.save(fiche.getF());
	    for(Fonctionalite f:fiche.getFoncts()) {
	    	f.setFichef(et.getFiche());
	    	fonrep.save(f);
	    }
	    for(Problematique p:fiche.getProbs()) {
	    	p.setFichep(et.getFiche());
	    	prorep.save(p);
	    }
	    
	    for(Technologies t:fiche.getTech()) {	    	 
	    	t.setFiche(et.getFiche());	    	  
	    	tecrep.save(t);
	    	  	    }
	   
	    return et.getFiche();	
		}
		@PutMapping("/savefichefull2")
		public Object saveFichedd(@Valid @RequestBody Fichetest fiche) {			 
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();	   
	    Etudiant et = etudiantrep.findByCode(auth.getName());	    
	  //  fiche.getF().setEtudiant(et);    
	  //  fiche.getF().setStatus("deposit");   
	  //  et.setFiche(fiche.getF());
	    socrep.save(fiche.getSoc());
	    fiche.getF().setSoc(fiche.getSoc());
	  
	    ensocrep.save(fiche.getEs());
	    fiche.getF().setEsoc(fiche.getEs());
	    
	    ficherep.save(fiche.getF());
	    for(Fonctionalite f:et.getFiche().getFonctionalities()) {
	    	fonrep.delete(f);
	    }
	    for(Fonctionalite f:fiche.getFoncts()) {
	    	f.setFichef(et.getFiche());
	    	fonrep.save(f);
	    }
	    for(Problematique p:et.getFiche().getProblematiques()) {
	    	prorep.delete(p);
	    }
	    for(Problematique p:fiche.getProbs()) {
	    	p.setFichep(et.getFiche());
	    	prorep.save(p);
	    }
	    for(Technologies ts:fiche.getTech()) {	  
	    	//if(!ts.getFiches().contains(et.getFiche())) {
	    	ts.setFiche(et.getFiche());	    	  
	    	tecrep.save(ts);
	    	}  // }
	
	    for(Technologies t:tecrep.findAll()) {	
	    	if(fiche.getTech().contains(t)) {
	    	System.out.println("test");
	    	}
	    	else if(t.getFiches().contains(et.getFiche())) 
	    	{
	    	System.out.println("test2");
	    	t.getFiches().remove(et.getFiche());    	  
	    	tecrep.save(t);
	    	}
	    	  	    }
	/*for(Technologies ts:fiche.getTech()) {
	    	if(ts.getFiches().contains(et.getFiche())) {
	    		System.out.println("exist");
	    	}
	    	else {
	    		ts.setFiche(et.getFiche());	    	  
		    	tecrep.save(ts);
	    	}
	    	for(Technologies t:tecrep.findAll()) {
	    		if(t!=ts)
	    		{
	    			t.getFiches().remove(et.getFiche());
	    			tecrep.save(t);
	    		}
	    	}
	    }
*/
	    return et.getFiche();	
		}

		@PutMapping("/deposefichefull2")
		public Object saveFichedds(@Valid @RequestBody Fichetest fiche) {			 
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();	   
	    Etudiant et = etudiantrep.findByCode(auth.getName());	    
	  //  fiche.getF().setEtudiant(et);    
	      fiche.getF().setStatus("deposed");   
	  //  et.setFiche(fiche.getF());
	    socrep.save(fiche.getSoc());
	    fiche.getF().setSoc(fiche.getSoc());
	  
	    ensocrep.save(fiche.getEs());
	    fiche.getF().setEsoc(fiche.getEs());
	    
	    ficherep.save(fiche.getF());
	    for(Fonctionalite f:et.getFiche().getFonctionalities()) {
	    	fonrep.delete(f);
	    }
	    for(Fonctionalite f:fiche.getFoncts()) {
	    	f.setFichef(et.getFiche());
	    	fonrep.save(f);
	    }
	    for(Problematique p:et.getFiche().getProblematiques()) {
	    	prorep.delete(p);
	    }
	    for(Problematique p:fiche.getProbs()) {
	    	p.setFichep(et.getFiche());
	    	prorep.save(p);
	    }
	    
	 
	    for(Technologies ts:fiche.getTech()) {	  
	    	//if(!ts.getFiches().contains(et.getFiche())) {
	    	ts.setFiche(et.getFiche());	    	  
	    	tecrep.save(ts);
	    	}  // }
	    
	    for(Technologies t:tecrep.findAll()) {	
	    	if(fiche.getTech().contains(t)) {
	    		System.out.println("test");
	    	}
	    	else if(t.getFiches().contains(et.getFiche())) 
	    	{
	    		System.out.println("test2");
	    	t.getFiches().remove(et.getFiche());    	  
	    	tecrep.save(t);
	    	}
	    	  	    }
	    
	/*    for(Technologies ts:fiche.getTech()) {
	    	if(ts.getFiches().contains(et.getFiche())) {
	    		System.out.println("exist");
	    	}
	    	else {
	    		ts.setFiche(et.getFiche());	    	  
		    	tecrep.save(ts);
	    	}
	    	for(Technologies t:tecrep.findAll()) {
	    		if(t!=ts)
	    		{
	    			t.getFiches().remove(et.getFiche());
	    			tecrep.save(t);
	    		}
	    	}
	    }*/
	    
	    return et.getFiche();	
		}

		@GetMapping("/getfichetest")
		public Fichetest Fichetest() {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Etudiant et = etudiantrep.findByCode(auth.getName());
		    

			Fichetest f = new Fichetest();
			if(et.getFiche()!=null) {
			f.setF(et.getFiche());
			f.setEs(et.getFiche().getEsoc());
			f.setFoncts(et.getFiche().getFonctionalities());
			f.setProbs(et.getFiche().getProblematiques());
			f.setSoc(et.getFiche().getSoc());
			f.setTech(et.getFiche().getTechnologies());}
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
		@GetMapping("/gettech")
		public List<Technologies> get(){
			return tecrep.findAll();
		}
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
		@PostMapping("/testgg")
		public void createtechdd(@Valid @RequestBody Technologies tec ) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Etudiant et = etudiantrep.findByCode(auth.getName());
		    
		    Technologies t = tecrep.findById(1);
	    FichePFE f= et.getFiche();
	    //List<FichePFE> hh=new ArrayList<FichePFE>();
	    //hh.add(f);
	    //tec.setFiche(f);
	    //tecrep.save(tec);
	    f.setTechnologie(t);
	    ficherep.save(f);    
	    	
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
		 

		 
		 
		 
		 
}
