package com.icesi.samaca.services;

import com.icesi.samaca.model.person.Countryregion;


public interface CountryregionService {
	
		
	public Countryregion saveCr(Countryregion cR) throws IllegalArgumentException ;
	public Countryregion editCr(Countryregion cR) throws IllegalArgumentException;

}
