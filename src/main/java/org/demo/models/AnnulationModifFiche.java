package org.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class AnnulationModifFiche {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cause;
	private String etat;
	@OneToOne
	@JoinColumn(name = "fiche_id")
	private FichePFE fiche;
	
	
	
	
	public AnnulationModifFiche(String cause) {
		super();
		this.cause = cause;
	}
	public AnnulationModifFiche() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public FichePFE getFiche() {
		return fiche;
	}
	public void setFiche(FichePFE fiche) {
		this.fiche = fiche;
	}
	
}
