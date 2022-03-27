package com.icesi.samaca.services;

import com.icesi.samaca.model.person.Stateprovince;
import com.icesi.samaca.repositories.CountryregionRepository;


public class StateprovinceServiceImp implements StateprovinceService {
	
	CountryregionRepository countryregionRepository;
	
	
	public StateprovinceServiceImp(CountryregionRepository crR) {
		
		countryregionRepository = crR;
		
	}
	

	@Override
	public Stateprovince saveStateprov(Stateprovince sP) {
		try {
			
			if(sP != null && (!sP.getStateprovinceid().toString().isBlank() )){
				
				
			}
			


		}catch (IllegalArgumentException e) {
			// TODO: handle exception
			System.out.println("Algo en la creación salió mal, por favor revise los parametros");
		}
		
		return null;
	}

	@Override
	public Stateprovince editStateproV(Stateprovince sP) {
		// TODO Auto-generated method stub
		return null;
	}

}
