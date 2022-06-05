package com.icesi.samaca.backend.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.icesi.samaca.backend.model.sales.Salesterritory;
import com.icesi.samaca.backend.services.SalesTerritoryServiceImp;

@RestController
public class SalesterritoryRestController {
	
	@Autowired
	private SalesTerritoryServiceImp sTService;
	
	@RequestMapping("/salesterritories/")
	public Iterable<Salesterritory> getSalesterritories(){
		return sTService.findAll();
	}
	
	@RequestMapping("/salesterritories/{id}")
	public Salesterritory getSalesterritory(@PathVariable("id") Integer salestaxrateid){
		return sTService.findById(salestaxrateid).get();
	}
	
	@PostMapping("/salesterritories/add")
	public Salesterritory addSalesterritory(@RequestBody Salesterritory sT){
		return sTService.saveSalesTerritory(sT);
		
	}
	
	@DeleteMapping("/salesterritories/{id}")
	public Salesterritory deleteSalesterritory(@PathVariable("id") Integer salestaxrateid){
		return sTService.deleteSalesTerritory(salestaxrateid);
	}
	@PutMapping("/salesterritories/{id}")
	public Salesterritory updateSalesterritory(@RequestBody Salesterritory sT){
		return sTService.editSalesTerritory(sT);
	}
	
	
	

}
