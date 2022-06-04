package com.icesi.samaca.DaoTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

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
import com.icesi.samaca.dao.CountryRegionDAO;
import com.icesi.samaca.dao.SalesTaxRateDAO;
import com.icesi.samaca.dao.StateProvinceDAO;
import com.icesi.samaca.model.person.Countryregion;
import com.icesi.samaca.model.person.Stateprovince;
import com.icesi.samaca.model.sales.Salestaxrate;

@ExtendWith(SpringExtension.class)

//@SpringBootTest
@DataJpaTest
@ContextConfiguration(classes = Taller1AlexSamacaApplication.class)
public class testStateProvinceDao {
	
	@Autowired
	StateProvinceDAO stPDao;
	@Autowired
	CountryRegionDAO cRDao;
	@Autowired
	SalesTaxRateDAO sTRDao;
	
	Stateprovince sP;
	
	Countryregion cR;
	
	Salestaxrate sTR;
	
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
	}
	
	
	
	
	
}
