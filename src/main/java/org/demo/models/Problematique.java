package org.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Problematique {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nameproblematique;
	
	@ManyToOne
	@JoinColumn(name="fiche_id")
	private FichePFE fichep;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameproblematique() {
		return nameproblematique;
	}

	public void setNameproblematique(String nameproblematique) {
		this.nameproblematique = nameproblematique;
	}

	public Problematique() {
	
	}

	public Problematique(String nameproblematique) {
		super();
		this.nameproblematique = nameproblematique;
	}

	public FichePFE getFichep() {
		return fichep;
	}

	public void setFichep(FichePFE fichep) {
		this.fichep = fichep;
	}
	
	

}
