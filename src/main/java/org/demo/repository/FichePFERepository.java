package org.demo.repository;

import org.demo.models.FichePFE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface FichePFERepository extends JpaRepository<FichePFE, Long>{

}
