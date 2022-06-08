package com.icesi.samaca.backend.restControllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icesi.samaca.backend.model.person.Address;
import com.icesi.samaca.backend.services.AddresServiceImp;

@RestController
@RequestMapping("/api")
public class AddressRestController {
	
	@Autowired
	private AddresServiceImp addrService;
	
	@RequestMapping("/addresses/")
	public Iterable<Address> getAddresses(){
		return addrService.findAll();
	}
	
	@RequestMapping("/addresses/{id}")
	public Address getAddress(@PathVariable("id") Integer addressid){
		return addrService.findById(addressid).get();
	}
	
	@PostMapping("/addresses/")
	public Address addAddress(@RequestBody Address a){
		return addrService.saveAddress(a);
		
	}
	
	@DeleteMapping("/addresses/{id}")
	public Address deleteAddress(@PathVariable("id") Integer addressid){
		Optional<Address> result = addrService.findById(addressid);
		
		addrService.deleteAddress(addressid);
	
		return result.get();
		
	}
	@PutMapping("/addresses/{id}")
	public Address updateAddress(@RequestBody Address a) {
		
		addrService.editAddres(a);
		return a;
		
	}
	
	

}
