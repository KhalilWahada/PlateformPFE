package org.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Technologies {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nametechnologie;
	
	
	private String type ;
	
	
	private	String etat;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNametechnologie() {
		return nametechnologie;
	}


	public void setNametechnologie(String nametechnologie) {
		this.nametechnologie = nametechnologie;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getEtat() {
		return etat;
	}


	public void setEtat(String etat) {
		this.etat = etat;
	}


	public Technologies() {
		super();
	}


	public Technologies( String nametechnologie, String type, String etat) {
		super();
		this.nametechnologie = nametechnologie;
		this.type = type;
		this.etat = etat;
	}
	
	

}
