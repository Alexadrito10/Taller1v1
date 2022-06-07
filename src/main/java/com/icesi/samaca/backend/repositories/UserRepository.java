package com.icesi.samaca.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icesi.samaca.backend.model.person.UserApp;

public interface UserRepository extends JpaRepository<UserApp, Long>{

	
	
	UserApp findByUsername(String username);
}
