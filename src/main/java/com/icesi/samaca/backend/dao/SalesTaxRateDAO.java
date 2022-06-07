package com.icesi.samaca.backend.dao;

import java.util.List;

import com.icesi.samaca.backend.model.sales.Salestaxrate;



public interface SalesTaxRateDAO {
	public void save(Salestaxrate sT);
	public void update(Salestaxrate sT );
	public List<Salestaxrate> findAll();
	public Salestaxrate findById(Integer sTId);
	public List<Salestaxrate> findByStateprovince(Integer stPId);
	public List<Salestaxrate> findByName(String name);
	
	
}
