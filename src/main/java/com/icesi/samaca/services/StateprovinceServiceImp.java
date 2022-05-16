package com.icesi.samaca.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.samaca.model.person.Countryregion;
import com.icesi.samaca.model.person.Stateprovince;
import com.icesi.samaca.model.sales.Salesterritory;
import com.icesi.samaca.repositories.CountryregionRepository;
import com.icesi.samaca.repositories.SalesterritoryRepository;
import com.icesi.samaca.repositories.StateprovinceRepository;


@Service
public class StateprovinceServiceImp implements StateprovinceService {


	StateprovinceRepository stateProvinceRepo;
	CountryregionRepository countryregionRepository;
	SalesterritoryRepository salesterritoryRepo;


	@Autowired
	public StateprovinceServiceImp(CountryregionRepository crR, StateprovinceRepository staPRepo, SalesterritoryRepository stRepo) {

		stateProvinceRepo = staPRepo;
		countryregionRepository = crR;
		salesterritoryRepo = stRepo;

	}


	@Override
	public Stateprovince saveStateprov(Stateprovince sP, String countryRegionId,Integer territoryId) throws IllegalArgumentException {
		


			if(sP != null  
					&&(!sP.getStateprovinceid().toString().isBlank() && sP.getStateprovincecode().length() == 5)  
					&& (!sP.getIsonlystateprovinceflag().isBlank() && (sP.getIsonlystateprovinceflag().equals("Y") || sP.getIsonlystateprovinceflag().equals("N")))  
					){

				Optional<Countryregion> optional = this.countryregionRepository.findById(countryRegionId);
				if(optional.isPresent()) {
					sP.setCountryregion(optional.get());
					
					stateProvinceRepo.save(sP);

				}


			}
			else 
			{
				throw new IllegalArgumentException("Algo en la creaci�n sali� mal, por favor revise los parametros");
			}



		

		return sP;
	}	

	@Override
	public Stateprovince editStateproV(Stateprovince sP,String countryRegionId,Integer territoryId) throws IllegalArgumentException {

		Stateprovince result = null;
		try {

			if(sP != null && !sP.getStateprovinceid().toString().isEmpty()) {
				Optional<Stateprovince> checkIfExist = this.stateProvinceRepo.findById(sP.getStateprovinceid());


				if(checkIfExist.isPresent()
						&&(sP.getStateprovincecode().length() == 5)  
						&& (!sP.getIsonlystateprovinceflag().isBlank() 
								&& (sP.getIsonlystateprovinceflag().equals("Y") 
										|| sP.getIsonlystateprovinceflag().equals("N")))){

					Optional<Countryregion> optional = this.countryregionRepository.findById(countryRegionId);
					Optional<Salesterritory> optional1 = this.salesterritoryRepo.findById(territoryId);
					if(optional.isPresent() && optional1.isPresent()) {
						Stateprovince toEdit = stateProvinceRepo.getById(sP.getStateprovinceid());
						toEdit.setIsonlystateprovinceflag(sP.getIsonlystateprovinceflag());
						toEdit.setModifieddate(sP.getModifieddate());
						toEdit.setName(sP.getName());
						toEdit.setStateprovincecode(sP.getStateprovincecode());
						toEdit.setTerritoryid(territoryId);
						toEdit.setCountryregion(optional.get());
						
						
						
						
						
						

					}
					else 
					{
						throw new IllegalArgumentException("Algo en la edición salió mal, por favor revise los parametros");
					}



				}
				else 
				{
					throw new IllegalArgumentException("Algo en la edición salió mal, por favor revise los parametros");
				}

			}
			else 
			{
				throw new IllegalArgumentException("Algo en la edición salió mal, por favor revise los parametros");
			}



		}catch (IllegalArgumentException e) {
			// TODO: handle exception
			System.out.println("Algo en la edición salió mal, por favor revise los parametros");
		}

		return result;

	}

}
