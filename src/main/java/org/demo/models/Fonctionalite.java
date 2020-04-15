package org.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Fonctionalite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFonctionalite;
	
	private String nameFonctionalite;
	private String description ;
	private int degree;
	 @ManyToOne
	 @JoinColumn(name="fiche_id")
	 private FichePFE fichef;
	 
	public Long getIdFonctionalite() {
		return idFonctionalite;
	}
	public void setIdFonctionalite(Long idFonctionalite) {
		this.idFonctionalite = idFonctionalite;
	}
	public String getNameFonctionalite() {
		return nameFonctionalite;
	}
	public void setNameFonctionalite(String nameFonctionalite) {
		this.nameFonctionalite = nameFonctionalite;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	public Fonctionalite(String nameFonctionalite, String description, int degree) {
		super();
		this.nameFonctionalite = nameFonctionalite;
		this.description = description;
		this.degree = degree;
	}
	public Fonctionalite() {
		super();
	}
	public FichePFE getFichef() {
		return fichef;
	}
	public void setFichef(FichePFE fichef) {
		this.fichef = fichef;
	} 
	
	

}
