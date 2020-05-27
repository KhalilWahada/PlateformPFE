package org.demo.security;




public class CurrentUser {
	private Long id;	
	
	private String code;
	
	private String Email;
	
	private String CIN;
	
	private String name;
	
	
	private String lastname;
	
	private String role;

	
	public CurrentUser() {
		super();
	}
	
	

	public CurrentUser(Long id, String code, String name, String lastname) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.lastname = lastname;
	}



	public CurrentUser(String code, String email, String cIN, String name, String lastname) {
		super();
		this.code = code;
		Email = email;
		CIN = cIN;
		this.name = name;
		this.lastname = lastname;
	}



	public CurrentUser( String code, String name, String lastname) {
		super();
		this.code = code;
		this.name = name;
		this.lastname = lastname;
	}
	

	public CurrentUser(Long id, String code, String name, String lastname, String role) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.lastname = lastname;
		this.role = role;
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



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	
	
	
	
}
