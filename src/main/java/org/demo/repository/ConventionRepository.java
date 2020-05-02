package org.demo.repository;

import org.demo.models.Convention;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConventionRepository extends JpaRepository<Convention, Long> {

}
