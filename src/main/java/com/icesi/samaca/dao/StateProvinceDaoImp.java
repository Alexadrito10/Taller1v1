package com.icesi.samaca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.icesi.samaca.model.person.Stateprovince;
import com.icesi.samaca.model.sales.Salesterritory;

@Repository
@Scope("singleton")
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
		String jpql = "Select sP from Stateprovince sP";
		return 	entityManager.createQuery(jpql).getResultList();	
	}

	@Override
	public Stateprovince findById(Integer sPId) {
		return entityManager.find(Stateprovince.class, sPId);
	}

	@Override
	public List<Stateprovince> findByCountryRegion(Integer cRId) {
		String jpql = "Select sP from Stateprovince sP WHERE sp.countryregionid = '"+cRId+"'";
		return 	entityManager.createQuery(jpql).getResultList();	
	}

	@Override
	public List<Stateprovince> findByTerritory(Integer tId) {
		String jpql = "SELECT sp FROM Stateprovince sp WHERE sp.name = '"+tId+"'";
		return entityManager.createQuery(jpql,Stateprovince.class).getResultList();
	}

	@Override
	public List<Stateprovince> findByName(String name) {
		String jpql = "SELECT sp FROM Stateprovince sp WHERE sp.name = '"+name+"'";
		return entityManager.createQuery(jpql,Stateprovince.class).getResultList();
	}
	
	
	@Transactional
	public List<Object[]> getStateprovincesWithAddressAndSales(Salesterritory salesterritory) {
		//Aqui el parametro porque nos dice que recibe como parametro un territorio de venta,y el Exist es por la parte
		//que dice que debe retornar los estados provincia que cumplen con tener al menos una tasa impositiva de ventas
		
		//Debe ser un [] de objects porque la consulta pide varias 
//		a. Lo(s) estados-provincia (s) con sus datos
//		y cantidad de direcciones (que pertenecen a un territorio), 
//		ordenados por nombre. Recibe como parámetro un territorio de venta y 
//		retorna todos los estados-provincia que cumplen con tener al menos una tasa impositiva de ventas.
//		
		String jpql = "SELECT stateprovince, COUNT(address.addressid) "
				+ "FROM Stateprovince stateprovince, Address address "
				+ "WHERE stateprovince.stateprovinceid = address.stateprovince.stateprovinceid"
				+ " AND stateprovince.territoryid = " + salesterritory.getTerritoryid()   
				+ " AND EXISTS(SELECT salestaxrate.stateprovince FROM Salestaxrate salestaxrate WHERE salestaxrate.stateprovince = stateprovince)"
				+ " GROUP BY stateprovince.stateprovinceid "
				+ "ORDER BY stateprovince.name";
		
		return entityManager.createQuery(jpql,Object[].class).getResultList();
	}
	

}
