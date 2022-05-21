package com.icesi.samaca.services;

import com.icesi.samaca.model.person.Stateprovince;


public interface StateprovinceService {
	
	public Stateprovince saveStateprov(Stateprovince sP, Integer countryregionId) ;
	public Stateprovince editStateproV(Stateprovince sP, Integer countryregionId);

}
