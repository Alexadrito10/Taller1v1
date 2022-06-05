package com.icesi.samaca.backend.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icesi.samaca.backend.model.person.Countryregion;
import com.icesi.samaca.backend.services.CountryregionServiceImp;

@RestController
public class CountryregionRestController {
	
	@Autowired
	CountryregionServiceImp countryRService;
	
	@RequestMapping("/countryregions/")
	public Iterable<Countryregion> getCountries(){
		return countryRService.findAll();
		
		
	}
	@RequestMapping("/countryregions/{id}")
	public Countryregion getCountryregion(@PathVariable("id") Integer countryregionid){
		return countryRService.findById(countryregionid).get();
	
	}
	
	@PostMapping("/countryregions/add")
	public Countryregion addCountryregion (@RequestBody Countryregion c) {
		return countryRService.saveCr(c);
		
		
	}
	
	@DeleteMapping("/countryregions/{id}")
	public Countryregion deleteCountryregion (@PathVariable("id") Integer countryregionid) {
		return countryRService.deleteCr(countryregionid);
		
		
	}
	

	@PutMapping("/countryregions/{id}")
	public Countryregion updateCountryregion (@RequestBody Countryregion c) {
		
		return countryRService.editCr(c);
		
		
		
	}
	
	
	 
	

}
