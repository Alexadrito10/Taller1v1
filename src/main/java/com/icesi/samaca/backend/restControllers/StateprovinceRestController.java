package com.icesi.samaca.backend.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icesi.samaca.backend.dao.StateProvinceDaoImp;
import com.icesi.samaca.backend.model.person.Address;
import com.icesi.samaca.backend.model.person.Countryregion;
import com.icesi.samaca.backend.model.person.Stateprovince;
import com.icesi.samaca.backend.services.StateprovinceServiceImp;

@RestController
@RequestMapping("/api")
public class StateprovinceRestController {
	
	@Autowired
	private StateprovinceServiceImp sPService;
	
	
	@RequestMapping("/stateprovinces/")
	public Iterable<Stateprovince> getStateprovinces(){
		return sPService.findAll();
		
		
	}
	
	@RequestMapping("/stateprovinces/{id}")
	public Stateprovince getStateprovince(@PathVariable("id") Integer stateprovinceid){
		return sPService.findById(stateprovinceid).get();
		
		
	}
	@PostMapping("/stateprovinces/add")
	public Stateprovince addStateprovince(@RequestBody Stateprovince sP){
		return sPService.saveStateprov(sP);
		
		
	}
	@DeleteMapping("/stateprovinces/{id}")
	public Stateprovince deleteStateprovince(@PathVariable("id") Integer stateprovinceid){
		return sPService.deleteStateproV(stateprovinceid);
		
		
	}

	@PutMapping("/stateprovinces/{id}")
	public Stateprovince deleteStateprovince(@RequestBody Stateprovince sP){
		return sPService.editStateproV(sP);
		
		
	}
	
	

}
