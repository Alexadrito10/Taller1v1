package com.icesi.samaca.services;

import com.icesi.samaca.model.person.Address;


public interface AddressService {
	
	
	
	public Address saveAddress(Address addr, Integer id) ;
	public Address editAddres(Address addr, Integer id);
	
	
	

}
