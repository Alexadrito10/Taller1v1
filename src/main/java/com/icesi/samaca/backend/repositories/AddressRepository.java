package com.icesi.samaca.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icesi.samaca.backend.model.person.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
