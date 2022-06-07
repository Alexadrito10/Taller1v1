package com.icesi.samaca.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icesi.samaca.backend.model.person.Countryregion;

@Repository
public interface CountryregionRepository extends JpaRepository<Countryregion, Integer> {

}
