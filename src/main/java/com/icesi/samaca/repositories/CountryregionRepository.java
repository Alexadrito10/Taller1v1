package com.icesi.samaca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.icesi.samaca.model.person.Countryregion;

public interface CountryregionRepository extends JpaRepository<Countryregion, String> {

}
