package org.demo.repository;

import org.demo.models.Directiondesstages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DDSRepository extends JpaRepository<Directiondesstages, Long>{
	public Directiondesstages findById (long id);
	public Directiondesstages findByCode (String Code);
	
}
