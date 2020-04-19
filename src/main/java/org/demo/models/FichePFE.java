package org.demo.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "FichePfe")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class FichePFE {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFiche;
	private String titre;
	private String description;
	private boolean asoutenir;
	private String motifEnseignant;
	@OneToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Technology_Fiche", 
      joinColumns = @JoinColumn(name = "id_fiche", referencedColumnName = "idFiche"), 
      inverseJoinColumns = @JoinColumn(name = "id_techno", 
      referencedColumnName = "id"))
	private List<Technologies> technologies;
	@OneToMany(mappedBy = "fichef")
	private List<Fonctionalite> fonctionalities;
	@OneToMany(mappedBy = "fichep")
	private List <Problematique> problematiques;
	 @ManyToOne
	 @JoinColumn(name="societe")
	 private Societe soc;
	 @ManyToOne
	 @JoinColumn(name="enc_soc")
	 private EncadrantSociete esoc;
	 @ManyToOne
	 @JoinColumn(name="session")
	 private Session session;
	 @OneToOne(mappedBy = "fichesoutenance")
	 private Soutenance soutenance;
	
	
	
	
	public FichePFE(String titre, String description) {
		super();
		this.titre = titre;
		this.description = description;
		this.technologies=new ArrayList<Technologies>();
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public Soutenance getSoutenance() {
		return soutenance;
	}
	public void setSoutenance(Soutenance soutenance) {
		this.soutenance = soutenance;
	}
	public FichePFE(boolean asoutenir, String motifEnseignant, Etudiant etudiant) {
		super();
		this.asoutenir = asoutenir;
		this.motifEnseignant = motifEnseignant;
		this.etudiant = etudiant;
		this.technologies=new ArrayList<Technologies>();
	}
	
	public FichePFE(String titre, String description, Etudiant etudiant) {
		super();
		this.titre = titre;
		this.description = description;
		this.etudiant = etudiant;
		this.technologies=new ArrayList<Technologies>();
	}
	public FichePFE(boolean asoutenir, String motifEnseignant) {
		super();
		this.asoutenir = asoutenir;
		this.motifEnseignant = motifEnseignant;
		this.technologies=new ArrayList<Technologies>();
	}
	public Long getIdFiche() {
		return idFiche;
	}
	public void setIdFiche(Long idFiche) {
		this.idFiche = idFiche;
	}
	public FichePFE() {
	}
	public boolean isAsoutenir() {
		return asoutenir;
	}
	public void setAsoutenir(boolean asoutenir) {
		this.asoutenir = asoutenir;
	}
	public String getMotifEnseignant() {
		return motifEnseignant;
	}
	public void setMotifEnseignant(String motifEnseignant) {
		this.motifEnseignant = motifEnseignant;
	}
	public Etudiant getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	public List<Technologies> getTechnologies() {
		return technologies;
	}
	public void setTechnologies(List<Technologies> technologies) {
		this.technologies = technologies;
	}
	public List<Fonctionalite> getFonctionalities() {
		return fonctionalities;
	}
	public void setFonctionalities(List<Fonctionalite> fonctionalities) {
		this.fonctionalities = fonctionalities;
	}
	public List<Problematique> getProblematiques() {
		return problematiques;
	}
	public void setProblematiques(List<Problematique> problematiques) {
		this.problematiques = problematiques;
	}
	public Societe getSoc() {
		return soc;
	}
	public void setSoc(Societe soc) {
		this.soc = soc;
	}
	public EncadrantSociete getEsoc() {
		return esoc;
	}
	public void setEsoc(EncadrantSociete esoc) {
		this.esoc = esoc;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setTechnologie(Technologies technologie) {
		this.technologies.add(technologie);
	}
	
	

}
