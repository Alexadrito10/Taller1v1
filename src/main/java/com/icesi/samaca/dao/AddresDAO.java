package com.icesi.samaca.dao;

import java.util.List;

import com.icesi.samaca.model.person.Address;
import com.icesi.samaca.model.sales.Salestaxrate;



public interface AddresDAO {
	
	public void save(Address addr);
	public void update(Address addr);
	public List<Address> findAll();
	public Address findById(Integer sTId);
	public List<Address> findByStateProv(Integer stPId);
	public List<Address> findByCity(String city);

}
