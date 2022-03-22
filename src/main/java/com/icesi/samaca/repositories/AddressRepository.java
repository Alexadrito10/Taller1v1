package com.icesi.samaca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icesi.samaca.model.person.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
