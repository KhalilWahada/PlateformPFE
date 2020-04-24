package org.demo.models;

import javax.persistence.Entity;

@Entity
public class Enseignant extends User{
	
	private int numberEncadrement;
	
	private int numberEncadrementT;

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
		
	}

	public Enseignant() {
		super();
		
	}
	
	

}
