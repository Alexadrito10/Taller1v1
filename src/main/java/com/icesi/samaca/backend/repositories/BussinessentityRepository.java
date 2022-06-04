package com.icesi.samaca.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icesi.samaca.backend.model.person.Businessentity;

public interface BussinessentityRepository extends JpaRepository<Businessentity, Integer> {
	
	

}
