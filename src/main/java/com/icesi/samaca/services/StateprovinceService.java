package com.icesi.samaca.services;

import com.icesi.samaca.model.person.Stateprovince;


public interface StateprovinceService {
	
	public Stateprovince saveStateprov(Stateprovince sP,Integer cRId, Integer territoryId) ;
	public Stateprovince editStateproV(Stateprovince sP, Integer cRId, Integer territoryId);

}
