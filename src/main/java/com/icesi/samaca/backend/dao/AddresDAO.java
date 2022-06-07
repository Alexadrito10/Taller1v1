package com.icesi.samaca.backend.dao;

import java.util.List;

import com.icesi.samaca.backend.model.person.Address;
import com.icesi.samaca.backend.model.sales.Salestaxrate;



public interface AddresDAO {
	
	public void save(Address addr);
	public void update(Address addr);
	public List<Address> findAll();
	public Address findById(Integer sTId);
	public List<Address> findByStateProv(Integer stPId);
	public List<Address> findByCity(String city);
	public List<Address> getListAddressByAtLeastTwoBySalesHeader() ;

}
