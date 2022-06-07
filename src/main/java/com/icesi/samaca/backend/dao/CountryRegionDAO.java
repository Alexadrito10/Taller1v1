package com.icesi.samaca.backend.dao;

import java.util.List;

import com.icesi.samaca.backend.model.person.Countryregion;

public interface CountryRegionDAO {
	public void save(Countryregion cR);
	public void update(Countryregion cR);
	public List<Countryregion> findAll();
	public Countryregion findById(Integer cRId);
	
	
	


}
