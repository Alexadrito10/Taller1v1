package com.icesi.samaca.backend.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icesi.samaca.backend.model.person.Address;
import com.icesi.samaca.backend.model.person.Countryregion;
import com.icesi.samaca.backend.model.sales.Salestaxrate;
import com.icesi.samaca.backend.services.SalestaxrateServiceImp;

@RestController
@RequestMapping("/api")
public class SalestaxrateRestController {
	
	@Autowired
	private SalestaxrateServiceImp sTRService;
	
	@RequestMapping("/salestaxrates/")
	public Iterable<Salestaxrate> getSalestaxrates(){
		return sTRService.findAll();
	}
	
	@RequestMapping("/salestaxrates/{id}")
	public Salestaxrate getSalestaxrate(@PathVariable("id") Integer salestaxrateid){
		return sTRService.findById(salestaxrateid).get();
	}
	
	@PostMapping("/salestaxrates/")
	public Salestaxrate addSalestaxrate(@RequestBody Salestaxrate s){
		return sTRService.saveSalesTR(s);
	}
	@DeleteMapping("/salestaxrates/{id}")
	public Salestaxrate deleteSalestaxrate (@PathVariable("id") Integer salestaxrateid) {
		return sTRService.deleteSalesTR(salestaxrateid);
	}
	@PutMapping("/salestaxrates/{id}")
	public Salestaxrate updateSalestaxrate (@RequestBody Salestaxrate s) {
		return sTRService.editSalesTR(s);
	}
	
	
	
	
 
}
