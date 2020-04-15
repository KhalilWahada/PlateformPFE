package org.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FichePfe")
public class FichePFE {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFiche;
	
	private boolean asoutenir;
	private String motifEnseignant;
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
	
	

}
