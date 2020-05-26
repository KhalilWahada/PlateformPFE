package org.demo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.demo.models.User;
import org.demo.repository.UserRepository;
import org.demo.security.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
	
	@Autowired
	private UserRepository userrep;
	 @PostMapping("/login")
		public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		 Optional<User> et = userrep.findByCode(loginRequest.getUsername());
		 if(et.get().getCIN().equals(loginRequest.getPassword())) {
			return ResponseEntity.ok(new User(et.get().getId(),
											  et.get().getCode(),
										   	  et.get().getName(),
											  et.get().getLastname()));
		}
		 
		 return ResponseEntity.status(400).body("wrong password");		
	 
	 }
}
