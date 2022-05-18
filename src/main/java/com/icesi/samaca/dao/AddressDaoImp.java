package com.icesi.samaca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.icesi.samaca.model.person.Address;
import com.icesi.samaca.model.sales.Salestaxrate;

@Repository
@Scope("Singleton")
public class AddressDaoImp implements AddresDAO {
	
	@PersistenceContext
	@Autowired
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(Address addr) {
		// TODO Auto-generated method stub
		entityManager.persist(addr);
		
	}

	@Override
	@Transactional
	public void update(Address addr) {
		// TODO Auto-generated method stub
		entityManager.merge(addr);
	}

	@Override

	public List<Address> findAll() {
		// TODO Auto-generated method stub
		Query query= entityManager.createQuery("SELECT s FROM Address s");
		return query.getResultList();
	}

	@Override
	public Address findById(Integer sTId) {
		// TODO Auto-generated method stub
		return entityManager.find(Address.class, sTId);
		
	}

	@Override
	public List<Address> findByCity(String city) {
		
		String jpql = "SELECT a FROM Address a WHERE a.city = '"+city+"'";
		
		// TODO Auto-generated method stub
		return entityManager.createQuery(jpql,Address.class).getResultList();
		
		
		
	}

	@Override
	public List<Address> findByStateProv(Integer stPId) {
		// TODO Auto-generated method stub
		String jpql = "SELECT a FROM Address a WHERE a.stateprovince.stateprovinceid = '"+stPId+"'";
		return entityManager.createQuery(jpql,Address.class).getResultList();
		
		
		
	}
	
	

}
