package org.demo.controller;


import java.util.Optional;

import javax.validation.Valid;

import org.demo.models.Administrateur;
import org.demo.models.Directiondesstages;
import org.demo.models.Enseignant;
import org.demo.models.Etudiant;

import org.demo.models.User;
import org.demo.repository.UserRepository;
import org.demo.security.CurrentUser;
import org.demo.security.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
	@Autowired
	public JavaMailSender emailSender;
	@Autowired
	private UserRepository userrep;
	 @PostMapping("/login")
		public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		 Optional<User> et = userrep.findByCode(loginRequest.getUsername());
		 String role="";
		 if (et.get().getPassword()==null) {
		 if(et.get().getCIN().equals(loginRequest.getPassword())) {
			 if (et.get() instanceof Etudiant)
					role=("etudiant");
				if (et.get() instanceof Administrateur)
					role=("administrateur");
				if (et.get() instanceof Enseignant)
					role=("enseignant");
				if (et.get() instanceof Directiondesstages)
					role=("direction de stage");
			return ResponseEntity.ok(new CurrentUser(et.get().getId(),
											  et.get().getCode(),
										   	  et.get().getName(),
											  et.get().getLastname(),
											  role));
		}
		 }
		 if (et.get().getPassword().equals(loginRequest.getPassword())) {
			 if (et.get() instanceof Etudiant)
					role=("etudiant");
				if (et.get() instanceof Administrateur)
					role=("administrateur");
				if (et.get() instanceof Enseignant)
					role=("enseignant");
				if (et.get() instanceof Directiondesstages)
					role=("direction de stage");
			return ResponseEntity.ok(new CurrentUser(et.get().getId(),
											  et.get().getCode(),
										   	  et.get().getName(),
											  et.get().getLastname(),
											  role));
		 }
		 
		 return ResponseEntity.status(400).body("wrong password");		
	 
	 }
	 @PutMapping("/updatepwd")
		public Object updatef(@Valid @RequestBody User user) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Optional<User> u = userrep.findByCode(auth.getName());
			u.get().setPassword(user.getPassword());
			userrep.save(u.get());		
			return u;
		}
	 @PostMapping("/loginchange")
		public ResponseEntity<?> authenticateUserchange(@Valid @RequestBody LoginRequest loginRequest) {
		 Optional<User> et = userrep.findByCode(loginRequest.getUsername());
		 String role="";
		
		
			 if (et.get() instanceof Etudiant)
					role=("etudiant");
				if (et.get() instanceof Administrateur)
					role=("administrateur");
				if (et.get() instanceof Enseignant)
					role=("enseignant");
				if (et.get() instanceof Directiondesstages)
					role=("direction de stage");
			return ResponseEntity.ok(new CurrentUser(et.get().getId(),
											  et.get().getCode(),
										   	  et.get().getName(),
											  et.get().getLastname(),
											  role));
		
		 }

	 @PostMapping("/forgetpassword")
	 public ResponseEntity<?> forgetpass(@Valid @RequestBody LoginRequest loginRequest) {
		 
		 Optional<User> et = userrep.findByEmail(loginRequest.getUsername());
		 
		 if (et.get().getPassword()!=null)
		 {
			 sendSimpleMessage(loginRequest.getUsername(),et.get().getPassword());
			 return ResponseEntity.ok("success");
			 
		 }
		 if (et.get().getCode()!=null)
		 {
			 sendSimpleMessage(loginRequest.getUsername(),et.get().getCode());
			 return ResponseEntity.ok("success");
		 }
		 
		 return ResponseEntity.status(400).body("Email does not exist");	
	    }

	    public void sendSimpleMessage(String to, String pass) {
	        
	    	String subject="Password PFE Esprit";
	    	String text ="Your password: "+pass;
	        SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setTo(to); 
	        message.setSubject(subject); 
	        message.setText(text);
	        emailSender.send(message);
	        
	    }
	 
	 
}
