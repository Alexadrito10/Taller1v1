package com.icesi.samaca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.icesi.samaca.model.person.Stateprovince;

public class StateProvinceDaoImp implements StateProvinceDAO{
	
	@PersistenceContext
	@Autowired
	private EntityManager entityManager;

	@Override
	public void save(Stateprovince sP) {
		entityManager.persist(sP);
		
	}

	@Override
	public void update(Stateprovince sP) {
		entityManager.merge(sP);
		
	}

	@Override
	public List<Stateprovince> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stateprovince findById(Integer sPId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stateprovince> findByCountryRegion(Integer cRId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stateprovince> findByTerritory(Integer tId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stateprovince> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
