package org.demo.models;

import javax.persistence.Entity;

@Entity
public class Etudiant extends User{

	private String classe;
	private boolean authorized;
	private String newmail;
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
	
}
