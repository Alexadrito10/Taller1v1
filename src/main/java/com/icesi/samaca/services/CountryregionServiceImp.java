package com.icesi.samaca.services;



import org.springframework.stereotype.Service;

import com.icesi.samaca.model.person.Countryregion;
import com.icesi.samaca.repositories.CountryregionRepository;

@Service
public class CountryregionServiceImp implements CountryregionService {

	CountryregionRepository cRRepo;

	public CountryregionServiceImp (CountryregionRepository cRRep) {

		cRRepo = cRRep;
	} 

	@Override
	public Countryregion saveCr(Countryregion cR) {
		try {
			if ((cR.getCountryregioncode() != null) && (cR.getCountryregioncode().length()>=1 && 
					cR.getCountryregioncode().length()<=4) && (cR.getName().length()>=5)) {

				cRRepo.save(cR);


			}
			else {

				throw new IllegalArgumentException();
			}
		}catch (IllegalArgumentException e) {
			System.out.println("Hubo un error en la creacion, revise los datos");
		}
		return cR;

	}

	@Override
	public boolean editCr(Countryregion cR) {
		boolean result = false;

		try {

			if ((cR.getCountryregioncode() != null) && (cR.getCountryregioncode().length()>=1 && 
				cR.getCountryregioncode().length()<=4) && (cR.getName().length()>=5)) {
				
				Countryregion toChange = cRRepo.getById(cR.getCountryregioncode());
				
				toChange.setModifieddate(cR.getModifieddate());
				toChange.setName(cR.getName());
				toChange.setStateprovinces(cR.getStateprovinces());
				cRRepo.save(toChange);
				
				result = true;
			}else {

				throw new IllegalArgumentException();
			}
		}catch(IllegalArgumentException c) {
			System.out.println("Hubo un error editando, revise los datos");
		}
		return result;
	}

}
