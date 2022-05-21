package com.icesi.samaca.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.icesi.samaca.model.sales.Salesterritory;
import com.icesi.samaca.repositories.SalesterritoryRepository;

@Service
public class SalesTerritoryServiceImp implements SalesTerritoryService{
	private SalesterritoryRepository salesTerritoryRepo;

	@Autowired
	public SalesTerritoryServiceImp(SalesterritoryRepository salesTerritoryRepo) {
		this.salesTerritoryRepo= salesTerritoryRepo;
	}

	@Override
	public Salesterritory saveSalesTerritory(Salesterritory sT){
		Salesterritory temp = null;
		if(sT.getName()!=null && sT.getName().length() >= 5) {
			temp= this.salesTerritoryRepo.save(sT);
		} else {
			throw new IllegalArgumentException("Algo en la creaci�n sali� mal, por favor revise los parametros");
		}
	
		return temp;
	}

	@Override
	@Transactional
	public Salesterritory  editSalesTerritory(Salesterritory sT){
		Salesterritory temp = null;
		if(sT.getTerritoryid()!=null) {
			Optional<Salesterritory> optional = salesTerritoryRepo.findById(sT.getTerritoryid());
			if(optional.isPresent()) {
				temp= saveSalesTerritory(sT);
			}
		}

		return temp;
	}

	public Iterable<Salesterritory> findAll(){
		return salesTerritoryRepo.findAll();
	}
	
	public Optional<Salesterritory> findById(Integer id){
		return salesTerritoryRepo.findById(id);
	}
}
