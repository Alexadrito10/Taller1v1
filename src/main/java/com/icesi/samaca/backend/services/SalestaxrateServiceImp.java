package com.icesi.samaca.backend.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.samaca.backend.model.person.Stateprovince;
import com.icesi.samaca.backend.model.sales.Salestaxrate;
import com.icesi.samaca.backend.repositories.SalestaxrateRepository;
import com.icesi.samaca.backend.repositories.StateprovinceRepository;

@Service
public class SalestaxrateServiceImp implements SalestaxrateService{

	SalestaxrateRepository salesTRRepo;
	StateprovinceRepository stateprovinceRepo;


	@Autowired
	public SalestaxrateServiceImp(SalestaxrateRepository salTRRepo, StateprovinceRepository stateprovinceRepository )
	{
		salesTRRepo = salTRRepo;
		stateprovinceRepo = stateprovinceRepository;

	}


	@Override
	public Salestaxrate saveSalesTR(Salestaxrate salesTR) throws IllegalArgumentException {
		Salestaxrate aux= null;
		if((salesTR.getTaxrate().compareTo(BigDecimal.ZERO))>=0 &&  
				(salesTR.getName().length()>=5))
		{
			Optional<Stateprovince> state= this.stateprovinceRepo.findById(salesTR.getStateprovince().getStateprovinceid());
			if(state.isPresent()) {
				salesTR.setStateprovince(state.get());
				aux= this.salesTRRepo.save(salesTR);
			}
				
		}
		else 
		{
			throw new IllegalArgumentException();
		}

		return aux;
	}

	@Override
	public Salestaxrate editSalesTR(Salestaxrate salesTR) throws IllegalArgumentException {
		Salestaxrate result = null;

		if(salesTR.getSalestaxrateid()!= null){
			Optional<Salestaxrate> stateOp= salesTRRepo.findById(salesTR.getSalestaxrateid());
			if(stateOp.isPresent()){
				result= saveSalesTR(salesTR);
			}
		}else{
			throw new IllegalArgumentException();
		}

		return result;
	}

	public Iterable<Salestaxrate> findAll(){
		return salesTRRepo.findAll();
	}
	
	public Optional<Salestaxrate> findById(Integer id){
		return salesTRRepo.findById(id);
	}
	
	public Iterable<Stateprovince> findAllStateProvinces(){
		return stateprovinceRepo.findAll();
	}


	@Override
	public Salestaxrate deleteSalesTR(Integer salesTRID) {
		Optional<Salestaxrate> result = salesTRRepo.findById(salesTRID);
		salesTRRepo.delete(result.get());
		return result.get();
	}
	


}
