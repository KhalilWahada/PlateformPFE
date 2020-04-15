package org.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String code;
	
	
	private String name;
	
	
	private String lastname;
	
	private ERole role;

	
	public User() {
		super();
	}
	
	

	public User( String code, String name, String lastname) {
		super();
		this.code = code;
		this.name = name;
		this.lastname = lastname;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public ERole getRole() {
		return role;
	}



	public void setRole(ERole role) {
		this.role = role;
	}
	
	
	
}
