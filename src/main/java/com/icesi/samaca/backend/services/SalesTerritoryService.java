package com.icesi.samaca.backend.services;

import com.icesi.samaca.backend.model.sales.Salesterritory;

public interface SalesTerritoryService{
	public Salesterritory saveSalesTerritory(Salesterritory territory);
	public Salesterritory editSalesTerritory(Salesterritory territory);
	public Salesterritory deleteSalesTerritory(Integer territoryid);
}
