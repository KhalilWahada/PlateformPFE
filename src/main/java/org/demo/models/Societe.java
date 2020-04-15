package org.demo.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Societe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nomSociete;
	private String emailSociete;
	private String adresseSociete;
	@OneToMany(mappedBy = "soc")
	private List<FichePFE> fiches;
	
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
	public Societe() {
		super();
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
	

}
