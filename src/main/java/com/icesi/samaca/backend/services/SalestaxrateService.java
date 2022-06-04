package com.icesi.samaca.backend.services;


import com.icesi.samaca.backend.model.sales.Salestaxrate;

public interface SalestaxrateService {
	
	
	public Salestaxrate saveSalesTR(Salestaxrate salesTR) ;
	public Salestaxrate editSalesTR(Salestaxrate salesTR);
}
