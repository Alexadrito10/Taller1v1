package com.icesi.samaca.backend.services;

import com.icesi.samaca.backend.model.person.Stateprovince;


public interface StateprovinceService {
	
	public Stateprovince saveStateprov(Stateprovince sP, Integer countryregionId) ;
	public Stateprovince editStateproV(Stateprovince sP, Integer countryregionId);
	public Stateprovince deleteStateproV(Integer countryregionId);

}
