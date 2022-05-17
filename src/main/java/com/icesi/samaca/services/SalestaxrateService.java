package com.icesi.samaca.services;


import com.icesi.samaca.model.sales.Salestaxrate;

public interface SalestaxrateService {
	
	
	public Salestaxrate saveSalesTR(Salestaxrate salesTR, Integer id) ;
	public Salestaxrate editSalesTR(Salestaxrate salesTR, Integer id);
}
