package com.icesi.samaca.backend.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.samaca.backend.dao.SalesTaxRateDaoImp;
import com.icesi.samaca.backend.model.person.Stateprovince;
import com.icesi.samaca.backend.model.sales.Salestaxrate;
import com.icesi.samaca.backend.repositories.SalestaxrateRepository;
import com.icesi.samaca.backend.repositories.StateprovinceRepository;

@Service
public class SalestaxrateServiceImp implements SalestaxrateService{
	
	@Autowired
	SalesTaxRateDaoImp sTRDao;

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
		//Salestaxrate aux= null;
		if((salesTR.getTaxrate().compareTo(BigDecimal.ZERO))>=0 &&  
				(salesTR.getName().length()>=5))
		{
			Optional<Stateprovince> state= this.stateprovinceRepo.findById(salesTR.getStateprovince().getStateprovinceid());
			if(state.isPresent()) {
				salesTR.setStateprovince(state.get());
				this.sTRDao.save(salesTR);
			}
				
		}
		else 
		{
			throw new IllegalArgumentException();
		}

		return salesTR;
	}

	@Override
	public Salestaxrate editSalesTR(Salestaxrate salesTR) throws IllegalArgumentException {
		//Salestaxrate result = null;

		if(salesTR.getSalestaxrateid()!= null){
			Salestaxrate stateOp= sTRDao.findById(salesTR.getSalestaxrateid());
			if(stateOp != salesTR){
				
				sTRDao.update(salesTR);
				
			}
		}else{
			throw new IllegalArgumentException();
		}

		return salesTR;
	}

	public Iterable<Salestaxrate> findAll(){
		return sTRDao.findAll();
	}
	
	public Optional<Salestaxrate> findById(Integer id){
		return Optional.of(sTRDao.findById(id));
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
