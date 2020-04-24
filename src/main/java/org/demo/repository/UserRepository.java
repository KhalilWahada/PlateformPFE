package org.demo.repository;

import java.util.Optional;

import org.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	 Optional<User> findByCode(String Code);
	 }
