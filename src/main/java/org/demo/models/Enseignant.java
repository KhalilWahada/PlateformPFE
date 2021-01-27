package org.demo.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;


@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Enseignant extends User{
	
	 @JsonBackReference
	@OneToMany(mappedBy = "enseignant")
	private List<FichePFE> fiches;
	
	private int numberEncadrement;
	
	private int numberEncadrementT;
	
	private boolean isactive ;
	@OneToMany(mappedBy = "emploiens")
	private List<Emploidujour> emploidutemp;

	public List<Emploidujour> getEmploidutemp() {
		return emploidutemp;
	}

	public void setEmploidutemp(List<Emploidujour> emploidutemp) {
		this.emploidutemp = emploidutemp;
	}

	public List<FichePFE> getFiches() {
		return fiches;
	}

	public void setFiches(List<FichePFE> fiches) {
		this.fiches = fiches;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public int getNumberEncadrement() {
		return numberEncadrement;
	}

	public void setNumberEncadrement(int numberEncadrement) {
		this.numberEncadrement = numberEncadrement;
	}

	public int getNumberEncadrementT() {
		return numberEncadrementT;
	}

	public void setNumberEncadrementT(int numberEncadrementT) {
		this.numberEncadrementT = numberEncadrementT;
	}

	public Enseignant(String code, String name, String lastname, int numberEncadrement, int numberEncadrementT) {
		super(code, name, lastname);
		this.numberEncadrement = numberEncadrement;
		this.numberEncadrementT = numberEncadrementT;
		this.isactive = true ;

	}

	public Enseignant() {
		super();
		this.isactive = true ;
		
		
	}
	
	

}
