package org.demo.repository;

import org.demo.models.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;



public interface EnseignantRepository extends JpaRepository<Enseignant, Long>{
}
