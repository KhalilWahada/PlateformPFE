package org.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Date;
import java.util.List;


@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Emploidujour {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long emploi_id;
	 
	@ManyToOne
	private Enseignant emploiens;
	 
	private Date date;
	
	public Enseignant getEmploiens() {
		return emploiens;
	}
	public void setEmploiens(Enseignant emploiens) {
		this.emploiens = emploiens;
	}
	private String Seance1;
	private String Seance2;
	private String Seance3;
	private String Seance4;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSeance1() {
		return Seance1;
	}
	public void setSeance1(String seance1) {
		Seance1 = seance1;
	}
	public String getSeance2() {
		return Seance2;
	}
	public void setSeance2(String seance2) {
		Seance2 = seance2;
	}
	public String getSeance3() {
		return Seance3;
	}
	public void setSeance3(String seance3) {
		Seance3 = seance3;
	}
	public String getSeance4() {
		return Seance4;
	}
	public void setSeance4(String seance4) {
		Seance4 = seance4;
	}


}
