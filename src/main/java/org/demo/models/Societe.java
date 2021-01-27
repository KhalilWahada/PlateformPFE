package org.demo.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Societe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nomSociete;
	private String emailSociete;
	private String adresseSociete;
	@JsonBackReference
	@OneToMany(mappedBy = "soc")
	private List<FichePFE> fiches;
	
	public Societe() {
		super();
	}
	public Societe(String nomSociete) {
		
		super();
		this.fiches=new ArrayList<FichePFE>();
		this.nomSociete = nomSociete;
	}

	public Societe(Long id, String nomSociete, String emailSociete, String adresseSociete, List<FichePFE> fiches) {
		super();
		this.id = id;
		this.nomSociete = nomSociete;
		this.emailSociete = emailSociete;
		this.adresseSociete = adresseSociete;
		this.fiches = fiches;
	}
	public String getNomSociete() {
		return nomSociete;
	}
	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}
	public String getEmailSociete() {
		return emailSociete;
	}
	public void setEmailSociete(String emailSociete) {
		this.emailSociete = emailSociete;
	}
	public String getAdresseSociete() {
		return adresseSociete;
	}
	public void setAdresseSociete(String adresseSociete) {
		this.adresseSociete = adresseSociete;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<FichePFE> getFiches() {
		return fiches;
	}
	public void setFiches(List<FichePFE> fiches) {
		this.fiches = fiches;
	}
	public void setFiche(FichePFE fiche) {
		this.fiches.add(fiche);
	}
	

}
