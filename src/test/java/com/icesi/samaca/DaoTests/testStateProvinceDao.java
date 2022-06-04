package com.icesi.samaca.DaoTests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.icesi.samaca.Taller1AlexSamacaApplication;
import com.icesi.samaca.dao.AddressDaoImp;
import com.icesi.samaca.dao.CountryRegionDAO;
import com.icesi.samaca.dao.CountryRegionDaoImp;
import com.icesi.samaca.dao.SalesTaxRateDAO;
import com.icesi.samaca.dao.SalesTaxRateDaoImp;
import com.icesi.samaca.dao.StateProvinceDAO;
import com.icesi.samaca.dao.StateProvinceDaoImp;
import com.icesi.samaca.model.person.Address;
import com.icesi.samaca.model.person.Countryregion;
import com.icesi.samaca.model.person.Stateprovince;
import com.icesi.samaca.model.sales.Salestaxrate;
import com.icesi.samaca.model.sales.Salesterritory;
import com.icesi.samaca.repositories.SalesterritoryRepository;

@ExtendWith(SpringExtension.class)

//@SpringBootTest
@DataJpaTest
@ContextConfiguration(classes = Taller1AlexSamacaApplication.class)
public class testStateProvinceDao {
	
	@Autowired
	StateProvinceDaoImp stPDao;
	@Autowired
	CountryRegionDaoImp cRDao;
	@Autowired
	SalesTaxRateDaoImp sTRDao;
	
	@Autowired
	AddressDaoImp addrDao;
	
	@Autowired
	SalesterritoryRepository stRepo;
	
	Salesterritory sT;
	
	Stateprovince sP;
	
	Countryregion cR;
	
	Salestaxrate sTR;
	
	Address addr;
	
	
	@BeforeEach
	void setup1(){
		
		cR = new Countryregion();
		cR.setCountryregioncode("COL");
		cR.setName("Colombia");
		
		cRDao.save(cR);
		
		sTR = new Salestaxrate();
		sTR.setTaxrate(new BigDecimal("0.2"));
		sTR.setName("Impuesto de la papa");
		
		sP = new Stateprovince();
		sP.setName("Valle del Cauca");
		sP.setStateprovincecode("VALCA");
		
		sT = new Salesterritory();
		
		sTR.setStateprovince(sP);
		sTRDao.save(sTR);
		//sP.setTerritoryid();
		sP.setCountryregion(cR);
		stPDao.save(sP);
		
		
		
		
		
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testSave() {
		
		stPDao.save(sP);
		
		assertEquals(sP,stPDao.findById(sP.getStateprovinceid()));
		
	}
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testUpdate() {
		
		stPDao.save(sP);
		sP.setName("Antioquia");
		sP.setStateprovincecode("ANTIO");
		
		stPDao.update(sP);
		
		assertAll(
				() -> assertEquals(sP, stPDao.findById(sP.getStateprovinceid()) ),
				() -> assertEquals("Antioquia",stPDao.findById(sP.getStateprovinceid()).getName()),
				() -> assertEquals("ANTIO",stPDao.findById(sP.getStateprovinceid()).getStateprovincecode()),
				()-> assertEquals(1,cRDao.findAll().size() )

					);
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testFindAll() {
		
		stPDao.save(sP);
		sP = new Stateprovince();
		sP.setName("Caracas");
		sP.setStateprovincecode("CARAC");
		sP.setCountryregion(cR);
		stPDao.save(sP);
		
		assertEquals(2,stPDao.findAll().size());
		
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testFindByCountryRegion(){
		
		
		cR = new Countryregion();
		cR.setCountryregioncode("CHI");
		cR.setName("Chile");
		
		cRDao.save(cR);
		
		sTR = new Salestaxrate();
		sTR.setTaxrate(new BigDecimal("0.15"));
		sTR.setName("Impuesto del papel");
		
		sP = new Stateprovince();
		sP.setName("Valle de alla");
		sP.setStateprovincecode("VALLA");
		
		sTR.setStateprovince(sP);
		sTRDao.save(sTR);
		//sP.setTerritoryid();
		sP.setCountryregion(cR);
		stPDao.save(sP);
		
		
	
		
		assertEquals(1,stPDao.findByCountryRegion(cR.getCountryregionid()).size());
		assertEquals("Valle de alla",stPDao.findByCountryRegion(cR.getCountryregionid()).get(0).getName());
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testfindByTerritory() {
		
		sT.setName("Puerto Buenaventura");
		
		
		stRepo.save(sT);
		
		sP.setTerritoryid(sT.getTerritoryid());
		stPDao.save(sP);
		
		sP = new Stateprovince();
		sP.setName("Boyaca");
		sP.setStateprovincecode("BOYAC");
		sP.setTerritoryid(sT.getTerritoryid());
		
		stPDao.save(sP);
		List<Stateprovince> results = stPDao.findByTerritory(sT.getTerritoryid());
		assertEquals(2,results.size());
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testfindByName() {
		
		stPDao.save(sP);
		sP = new Stateprovince();
		sP.setName("Caracas");
		sP.setStateprovincecode("CARAC");
		sP.setCountryregion(cR);
		stPDao.save(sP);
		
		assertEquals(sP, stPDao.findByName(sP.getName()).get(0));	
		
	
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testFindByAddressAndSales() {
		
		sT.setName("Puerto Buenaventura");
		
		
		stRepo.save(sT);
		
		sP.setTerritoryid(sT.getTerritoryid());
		
		addr = new Address();
		addr.setStateprovince(sP);
		
		addr.setStateprovince(sP);
		addrDao.save(addr);
		
		stPDao.save(sP);
		
		
		
		sP = new Stateprovince();
		sP.setName("Boyaca");
		sP.setStateprovincecode("BOYAC");
		
		sT = new Salesterritory();
		
		sT.setName("El centro");
		sTR.setStateprovince(sP);
		
		
		
		sP.setTerritoryid(sT.getTerritoryid());
		
		addr = new Address();
		addr.setStateprovince(sP);
		
		addrDao.save(addr);
		
		sTRDao.save(sTR);
		
		
		
		stPDao.save(sP);
		List<Object[]> results = stPDao.findByAddressAndSales(sT);
		assertEquals(1,results.size());
		
	
	}
	
	
	
	
}
