package com.icesi.samaca.dao;

import java.util.List;

import com.icesi.samaca.model.person.Address;



public interface addresDAO {
	
	public void save(Address addr);
	public void update(Address addr);
	public List<Address> findAll();
	public Address findById(Integer sTId);
	public List<Address> findByAddress(Integer stPId);

}
