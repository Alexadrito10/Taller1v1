package com.icesi.samaca.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icesi.samaca.backend.dao.StateProvinceDaoImp;
import com.icesi.samaca.backend.model.person.Countryregion;
import com.icesi.samaca.backend.model.person.Stateprovince;
import com.icesi.samaca.backend.model.sales.Salesterritory;
import com.icesi.samaca.backend.repositories.CountryregionRepository;
import com.icesi.samaca.backend.repositories.SalesterritoryRepository;
import com.icesi.samaca.backend.repositories.StateprovinceRepository;

@Service
public class StateprovinceServiceImp implements StateprovinceService {
	@Autowired
	StateProvinceDaoImp stateProvinceDao;
	
	StateprovinceRepository stateProvinceRepo;
	CountryregionRepository countryregionRepository;
	SalesterritoryRepository salesterritoryRepo;

	@Autowired
	public StateprovinceServiceImp(CountryregionRepository crR, StateprovinceRepository staPRepo,
			SalesterritoryRepository stRepo) {

		stateProvinceRepo = staPRepo;
		countryregionRepository = crR;
		salesterritoryRepo = stRepo;

	}

	@Override
	public Stateprovince saveStateprov(Stateprovince sP) throws IllegalArgumentException {
		//Stateprovince province = null;

		if ((sP.getStateprovincecode().length() == 5)
				&& (sP.getIsonlystateprovinceflag().equals("Y")
						|| sP.getIsonlystateprovinceflag().equals("N")) && sP.getName().length() >= 5) {

			Optional<Countryregion> optional = this.countryregionRepository.findById(sP.getCountryregion().getCountryregionid());
			//Optional<Salesterritory> optional2= this.salesterritoryRepo.findById(salesterritoryId);
			if (optional.isPresent()) {
				
				sP.setCountryregion(optional.get());

				this.stateProvinceDao.save(sP);

			} 
		} else {
			throw new IllegalArgumentException("Algo en la creaci�n sali� mal, por favor revise los parametros");
		}

		return sP;
	}

	@Override
	@Transactional
	public Stateprovince editStateproV(Stateprovince sP) throws IllegalArgumentException {
		//Stateprovince result = null;
			if(sP != null && sP.getStateprovinceid()!=null) {
				Optional<Stateprovince> checkIfExist = this.stateProvinceRepo.findById(sP.getStateprovinceid());
				//Optional<Countryregion> optional = this.countryregionRepository.findById(sP.getCountryregion().getCountryregionid());
				Stateprovince optional = stateProvinceDao.findByCountryRegion(sP.getCountryregion().getCountryregionid()).get(0);
					if(checkIfExist.isPresent() && optional!= null) {
						//result= saveStateprov(sP);
						stateProvinceDao.update(sP);
			}else {
				throw new IllegalArgumentException("Algo en la edición salió mal, por favor revise los parametros");
			}
		}else {
			throw new IllegalArgumentException("Algo en la edición salió mal, por favor revise los parametros");
		}
		return sP;
	}

	public Iterable<Stateprovince> findAll() {
		return stateProvinceDao.findAll();
	}

	public Optional<Stateprovince> findById(Integer id) {
		return Optional.of(stateProvinceDao.findById(id));
	}

	public Iterable<Countryregion> findAllCountries() {
		return countryregionRepository.findAll();
	}

	public Iterable<Salesterritory> findAllTerritories() {
		return salesterritoryRepo.findAll();
	}
	
	public Iterable<Stateprovince> findByCountry(Integer id){
		Countryregion country= countryregionRepository.findById(id).get();
		List<Stateprovince> provinceList= country.getStateprovinces();
		Iterable<Stateprovince> provinceIterable= provinceList;
		
		return provinceIterable;
	}

	@Override
	public Stateprovince deleteStateproV(Integer stateProvinceId) {
		Optional<Stateprovince> result = stateProvinceRepo.findById(stateProvinceId);
		stateProvinceRepo.delete(result.get());
		
		return result.get();
		
	}
}
