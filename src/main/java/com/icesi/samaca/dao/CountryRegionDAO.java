package com.icesi.samaca.dao;

import java.util.List;

import com.icesi.samaca.model.person.Countryregion;

public interface CountryRegionDAO {
	public void save(Countryregion cR);
	public void update(Countryregion cR);
	public List<Countryregion> findAll();
	public Countryregion findById(Integer cRId);
	
	
	


}
