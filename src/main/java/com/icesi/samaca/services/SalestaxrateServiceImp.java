package com.icesi.samaca.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.icesi.samaca.model.sales.Salestaxrate;
import com.icesi.samaca.repositories.SalestaxrateRepository;
import com.icesi.samaca.repositories.StateprovinceRepository;

@Service
public class SalestaxrateServiceImp implements SalestaxrateService{

	SalestaxrateRepository salesTRRepo;
	StateprovinceRepository stateprovinceRepo;


	public SalestaxrateServiceImp(SalestaxrateRepository salTRRepo, StateprovinceRepository stateprovinceRepository )
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
			System.out.println("Algo en la creación salió mal, por favor revise los parametros");
		}
		return salesTR;
	}

	@Override
	public Salestaxrate editSalesTR(Salestaxrate salesTR) {
		
		Salestaxrate result = null;
		try {
			if((salesTR.getTaxrate().compareTo(BigDecimal.ZERO))>=0 &&  
					(salesTR.getName().length()>=5) &&
					(stateprovinceRepo.findById(salesTR.getStateprovinceid()).isPresent()))
			{	
				
				Salestaxrate toChange = salesTRRepo.getById(salesTR.getSalestaxrateid());
				toChange.setModifieddate(salesTR.getModifieddate());
				toChange.setName(salesTR.getName());
				toChange.setRowguid(salesTR.getRowguid());
				toChange.setStateprovinceid(salesTR.getStateprovinceid());
				toChange.setTaxrate(salesTR.getTaxrate());
				toChange.setTaxtype(salesTR.getTaxtype());
				result = toChange;
				salesTRRepo.save(toChange);
				
			}
			else 
			{
				throw new IllegalArgumentException();
			}
		}catch (IllegalArgumentException e) {
			// TODO: handle exception
			System.out.println("Algo en la edición salió mal, por favor revise los parametros");
		}
		return result;
	}



}
