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
	
	private String Email;
	
	private String CIN;
	
	private String name;
	
	
	private String lastname;

	
	public User() {
		super();
	}
	
	

	public User(Long id, String code, String name, String lastname) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.lastname = lastname;
	}



	public User(String code, String email, String cIN, String name, String lastname) {
		super();
		this.code = code;
		Email = email;
		CIN = cIN;
		this.name = name;
		this.lastname = lastname;
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



	public String getEmail() {
		return Email;
	}



	public void setEmail(String email) {
		Email = email;
	}



	public String getCIN() {
		return CIN;
	}



	public void setCIN(String cIN) {
		CIN = cIN;
	}



	
	
	
	
}
