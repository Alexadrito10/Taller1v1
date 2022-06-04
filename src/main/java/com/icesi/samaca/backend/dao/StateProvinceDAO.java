package com.icesi.samaca.backend.dao;

import java.util.List;

import com.icesi.samaca.backend.model.person.Stateprovince;
import com.icesi.samaca.backend.model.sales.Salesterritory;



public interface StateProvinceDAO {
	
	public void save(Stateprovince sP);
	public void update(Stateprovince sP);
	public List<Stateprovince> findAll();
	public Stateprovince findById(Integer sPId);
	public List<Stateprovince> findByCountryRegion(Integer cRId);
	public List<Stateprovince> findByTerritory(Integer tId);
	public List<Stateprovince> findByName(String name);
	public List<Object[]> findByAddressAndSales(Salesterritory salesterritory);
	
	
	
	
	


}
