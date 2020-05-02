package org.demo.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Convention {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String sexe;
	private String mail;
	private Date dds;
	private Date dfs;
	private String societe;
	private String adresse;
	private String Telephone;
	private String mailsoc;
	private String repsoc;
	@OneToOne
	@JoinColumn(name = "etudiant_id")
	private Etudiant etudiant;





	public Convention(String sexe, String mail, Date dds, Date dfs, String societe, String adresse, String telephone,
		String mailsoc, String repsoc, Etudiant etudiant) {
	super();
	this.sexe = sexe;
	this.mail = mail;
	this.dds = dds;
	this.dfs = dfs;
	this.societe = societe;
	this.adresse = adresse;
	Telephone = telephone;
	this.mailsoc = mailsoc;
	this.repsoc = repsoc;
	this.etudiant = etudiant;
}



	public Convention(String sexe, String mail) {
	super();
	this.sexe = sexe;
	this.mail = mail;
}



public Convention() {
	super();
}


public String getSexe() {
	return sexe;
}
public void setSexe(String sexe) {
	this.sexe = sexe;
}
public String getMail() {
	return mail;
}
public void setMail(String mail) {
	this.mail = mail;
}
public Date getDds() {
	return dds;
}
public void setDds(Date dds) {
	this.dds = dds;
}
public Date getDfs() {
	return dfs;
}
public void setDfs(Date dfs) {
	this.dfs = dfs;
}
public String getSociete() {
	return societe;
}
public void setSociete(String societe) {
	this.societe = societe;
}
public String getAdresse() {
	return adresse;
}
public void setAdresse(String adresse) {
	this.adresse = adresse;
}
public String getTelephone() {
	return Telephone;
}
public void setTelephone(String telephone) {
	Telephone = telephone;
}
public String getMailsoc() {
	return mailsoc;
}
public void setMailsoc(String mailsoc) {
	this.mailsoc = mailsoc;
}
public String getRepsoc() {
	return repsoc;
}
public void setRepsoc(String repsoc) {
	this.repsoc = repsoc;
}
public Etudiant getEtudiant() {
	return etudiant;
}
public void setEtudiant(Etudiant etudiant) {
	this.etudiant = etudiant;
}


}
