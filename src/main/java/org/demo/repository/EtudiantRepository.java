package org.demo.repository;

import org.demo.models.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;



public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{
	public Etudiant findById (long id);
}
