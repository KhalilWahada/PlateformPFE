package org.demo.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.demo.models.Administrateur;
import org.demo.models.Directiondesstages;
import org.demo.models.Enseignant;
import org.demo.models.Etudiant;
import org.demo.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails{

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String userName;
	    private String password;
	  //  private boolean active;
	    private List<GrantedAuthority> authorities;
	    
	public MyUserDetails(User user) {
			super();
			this.userName = user.getCode();
			this.password = user.getCIN();
			if (user instanceof Etudiant)
				this.authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_ETUDIANT"));
			if (user instanceof Administrateur)
				this.authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
			if (user instanceof Enseignant)
				this.authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_ENS"));
			if (user instanceof Directiondesstages)
				this.authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_DDS"));
		}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
	
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
	
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
