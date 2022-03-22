package com.icesi.samaca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icesi.samaca.model.person.Businessentity;

public interface BussinessentityRepository extends JpaRepository<Businessentity, Integer> {
	
	

}
