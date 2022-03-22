package com.icesi.samaca.services;

import java.math.BigDecimal;

import com.icesi.samaca.model.sales.Salestaxrate;
import com.icesi.samaca.repositories.SalestaxrateRepository;
import com.icesi.samaca.repositories.StateprovinceRepository;

public class SalestaxrateImp implements SalestaxrateService{

	SalestaxrateRepository salesTRRepo;
	StateprovinceRepository stateprovinceRepo;


	public SalestaxrateImp(SalestaxrateRepository salTRRepo, StateprovinceRepository stateprovinceRepository )
	{
		salesTRRepo = salTRRepo;
		stateprovinceRepo = stateprovinceRepository;

	}


	@Override
	public Salestaxrate saveSalesTR(Salestaxrate salesTR) {
		try {
			if((salesTR.getTaxrate().compareTo(BigDecimal.ZERO))>=0 &&  
					(salesTR.getName().length()>=5) &&
					(stateprovinceRepo.findById(salesTR.getStateprovinceid()).isPresent()))
			{
				
				salesTRRepo.save(salesTR);
			}
			else 
			{
				throw new IllegalArgumentException();
			}
		}catch (IllegalArgumentException e) {
			// TODO: handle exception
		}
		return salesTR;
	}

	@Override
	public boolean editSalesTR(Salestaxrate salesTR) {
		
		boolean result = false;
		try {
			if((salesTR.getTaxrate().compareTo(BigDecimal.ZERO))>=0 &&  
					(salesTR.getName().length()>=5) &&
					(stateprovinceRepo.findById(salesTR.getStateprovinceid()).isPresent()))
			{	
				result = true;
				Salestaxrate toChange = salesTRRepo.getById(salesTR.getSalestaxrateid());
				toChange.setModifieddate(salesTR.getModifieddate());
				toChange.setName(salesTR.getName());
				toChange.setRowguid(salesTR.getRowguid());
				toChange.setStateprovinceid(salesTR.getStateprovinceid());
				toChange.setTaxrate(salesTR.getTaxrate());
				toChange.setTaxtype(salesTR.getTaxtype());
				salesTRRepo.save(toChange);
			}
			else 
			{
				throw new IllegalArgumentException();
			}
		}catch (IllegalArgumentException e) {
			// TODO: handle exception
		}
		return result;
	}



}
