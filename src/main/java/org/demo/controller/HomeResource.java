package org.demo.controller;

import java.util.Optional;

import org.demo.models.Etudiant;
import org.demo.models.User;
import org.demo.repository.EtudiantRepository;
import org.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {
	@Autowired
    private UserRepository ur;
	@Autowired
    private EtudiantRepository er;
    @GetMapping("/dddd")
    public String home() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String c =auth.getName();        
        
        return (c+"<h1>Welcome</h1>");
    }

    @GetMapping("/etud")
    public String user() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String c =auth.getName();
        Etudiant et = er.findByCode(auth.getName());
        
        return (et.getEmail()+"<h1>Welcome student</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }

}

