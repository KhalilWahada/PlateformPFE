package org.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.demo.models.AnnulationModifFiche;
import org.demo.models.Convention;
import org.demo.models.Etudiant;
import org.demo.models.FichePFE;
import org.demo.models.Fonctionalite;
import org.demo.models.Problematique;
import org.demo.models.Societe;
import org.demo.models.Technologies;
import org.demo.repository.AnnulationModifFicheRepository;
import org.demo.repository.ConventionRepository;
import org.demo.repository.EtudiantRepository;
import org.demo.repository.FichePFERepository;
import org.demo.repository.FonctionaliteRepository;
import org.demo.repository.ProblematiqueRepository;
import org.demo.repository.SocieteRepository;
import org.demo.repository.TechnologiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")
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
	    return ficherep.save(fiche);	
		}
		@GetMapping("/listfiche")
		public List<FichePFE> listFiche() {
	    return ficherep.findAll();	
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
		 
	///////////////AnnulationModifFiche/////////////////////
		 
		 
		 @PostMapping("/createamf")
			public Object createAnn(@Valid @RequestBody AnnulationModifFiche amf ) {
		    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Etudiant et = etudiantrep.findByCode(auth.getName());
		    amf.setFiche(et.getFiche());		    
		    return amfrep.save(amf);	
			}
		 
		 
		 
		 
		 
		 
}

