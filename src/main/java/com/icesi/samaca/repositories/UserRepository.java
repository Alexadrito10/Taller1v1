package com.icesi.samaca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icesi.samaca.model.person.UserApp;

public interface UserRepository extends JpaRepository<UserApp, Long>{

	
	
	UserApp findByUsername(String username);
}
