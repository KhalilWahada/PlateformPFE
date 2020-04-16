package org.demo.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Soutenance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date dateSoutenance;
	
	private int duree;

	@OneToOne
    @JoinColumn(name = "fiche_id")
	private FichePFE fichesoutenance;
	
	public FichePFE getFichesoutenance() {
		return fichesoutenance;
	}

	public void setFichesoutenance(FichePFE fichesoutenance) {
		this.fichesoutenance = fichesoutenance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateSoutenance() {
		return dateSoutenance;
	}

	public void setDateSoutenance(Date dateSoutenance) {
		this.dateSoutenance = dateSoutenance;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public Soutenance() {
		super();
	}

	public Soutenance( Date dateSoutenance, int duree) {
		super();
		this.dateSoutenance = dateSoutenance;
		this.duree = duree;
	}
	
	
	

}
