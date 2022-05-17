package com.icesi.samaca.dao;

import java.util.List;

import com.icesi.samaca.model.sales.Salestaxrate;



public interface salesTaxRateDAO {
	public void save(Salestaxrate sT);
	public void update(Salestaxrate sT );
	public List<Salestaxrate> findAll();
	public Salestaxrate findById(Integer sTId);
	public List<Salestaxrate> findByStateprovince(Integer stPId);
	
}
