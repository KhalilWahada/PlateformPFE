package org.demo.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
public class Enseignant extends User{
	
	@OneToMany(mappedBy = "enseignant")
	private List<FichePFE> fiches;
	
	private int numberEncadrement;
	
	private int numberEncadrementT;
	
	private boolean isactive ;
	

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
