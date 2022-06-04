package com.icesi.samaca.backend.services;

import com.icesi.samaca.backend.model.person.Countryregion;


public interface CountryregionService {
	
		
	public Countryregion saveCr(Countryregion cR) throws IllegalArgumentException ;
	public Countryregion editCr(Countryregion cR) throws IllegalArgumentException;

}
