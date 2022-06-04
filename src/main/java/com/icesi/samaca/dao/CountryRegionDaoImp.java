package com.icesi.samaca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.icesi.samaca.model.person.Countryregion;

@Repository
@Scope("singleton")
public class CountryRegionDaoImp implements CountryRegionDAO {

	@PersistenceContext
	@Autowired
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public void save(Countryregion cR) {
		entityManager.persist(cR);
		
	}

	@Override
	@Transactional
	public void update(Countryregion cR) {
		// TODO Auto-generated method stub
		entityManager.merge(cR);
	}

	@Override
	public List<Countryregion> findAll() {
		String jpql = "Select cR from Countryregion cR";
		return 	entityManager.createQuery(jpql,Countryregion.class).getResultList();
	}

	@Override
	public Countryregion findById(Integer cRId) {
		return entityManager.find(Countryregion.class, cRId);
	}
	
	

}
