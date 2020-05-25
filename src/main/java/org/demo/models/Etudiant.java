package org.demo.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.*;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Etudiant extends User{

	private String classe;
	private boolean authorized;
	private String newmail;
	@OneToOne(mappedBy = "etudiant", cascade = CascadeType.ALL)
	private FichePFE fiche;
	@OneToOne(mappedBy = "etudiant", cascade = CascadeType.ALL)
	private Convention conv;
	
	public Etudiant() {
		super();
		
	}
	
	public Etudiant(String classe, boolean authorized) {
		super();
		this.classe = classe;
		this.authorized = authorized;
	}

	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public boolean isAuthorized() {
		return authorized;
	}
	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}
	public String getNewmail() {
		return newmail;
	}
	public void setNewmail(String newmail) {
		this.newmail = newmail;
	}

	public FichePFE getFiche() {
		return fiche;
	}

	public void setFiche(FichePFE fiche) {
		this.fiche = fiche;
	}

	public Convention getConv() {
		return conv;
	}

	public void setConv(Convention conv) {
		this.conv = conv;
	}
	
	
}
