package org.demo.models;

import javax.persistence.Entity;


@Entity
public class Directiondesstages extends User {

	public Directiondesstages() {
		super();
		super.setRole(ERole.DDS);
	}


}
