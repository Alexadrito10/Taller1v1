package com.icesi.samaca.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.samaca.model.person.Stateprovince;
import com.icesi.samaca.model.sales.Salestaxrate;
import com.icesi.samaca.repositories.SalestaxrateRepository;
import com.icesi.samaca.repositories.StateprovinceRepository;

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
				(salesTR.getName().length()>=5) && stateprovinceRepo.findById(salesTR.getStateprovinceid()).isPresent())
		{
				aux= this.salesTRRepo.save(salesTR);
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

		if((salesTR.getTaxrate().compareTo(BigDecimal.ZERO))>=0 &&  
				(salesTR.getName().length()>=5) &&
				(stateprovinceRepo.findById(salesTR.getStateprovinceid()).isPresent()))
		{	

//			Salestaxrate toChange = salesTRRepo.getById(salesTR.getSalestaxrateid());
//			//toChange.setModifieddate(salesTR.getModifieddate());
//			toChange.setName(salesTR.getName());
//			toChange.setRowguid(salesTR.getRowguid());
//			toChange.setStateprovinceid(salesTR.getStateprovinceid());
//			toChange.setTaxrate(salesTR.getTaxrate());
//			toChange.setTaxtype(salesTR.getTaxtype());
//			result = toChange;
			salesTRRepo.save(salesTR);
			result = salesTR;

		}
		else 
		{
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
	


}
