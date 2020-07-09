package org.demo.repository;


import org.demo.models.Technologies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologiesRepository extends JpaRepository<Technologies, Long>{
	public Technologies findById (long id);
}
