package org.demo.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class EncadrantSociete {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String last_name;
	private String email;
	@OneToMany(mappedBy = "esoc")
	private List<FichePFE> fiches;
	public EncadrantSociete() {
		super();
		this.fiches=new ArrayList<FichePFE>();
	}
	public EncadrantSociete(Long id, String name, String last_name, String email, List<FichePFE> fiches) {
		super();
		this.id = id;
		this.name = name;
		this.last_name = last_name;
		this.email = email;
		this.fiches = fiches;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<FichePFE> getFiches() {
		return fiches;
	}
	public void setFiches(List<FichePFE> fiches) {
		this.fiches = fiches;
	}
	public void setFiche(FichePFE fiche) {
		this.fiches.add(fiche);
	}
	

}
