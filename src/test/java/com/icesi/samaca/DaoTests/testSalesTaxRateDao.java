package com.icesi.samaca.DaoTests;

import static org.junit.jupiter.api.Assertions.assertAll;
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
import com.icesi.samaca.backend.dao.SalesTaxRateDAO;
import com.icesi.samaca.backend.dao.StateProvinceDAO;
import com.icesi.samaca.backend.model.person.Stateprovince;
import com.icesi.samaca.backend.model.sales.Salestaxrate;

@ExtendWith(SpringExtension.class)

//@SpringBootTest
@DataJpaTest
@ContextConfiguration(classes = Taller1AlexSamacaApplication.class)
public class testSalesTaxRateDao {
	
	@Autowired
	SalesTaxRateDAO sTRDao;
	
	@Autowired
	StateProvinceDAO sPDao;
	
	Salestaxrate sTR;
	
	Stateprovince sP;
	
	@BeforeEach
	void setup1() {
		
		sTR = new Salestaxrate();
		sTR.setTaxrate(new BigDecimal("0.2"));
		sTR.setName("Impuesto de la papa");
		
		sP = new Stateprovince();
		sP.setName("Valle del Cauca");
		sP.setStateprovincecode("VALCA");
		
		sPDao.save(sP);
		sTR.setStateprovince(sP);
		sTRDao.save(sTR);
		
	}
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testSave() {
		
		sTRDao.save(sTR);
		assertEquals("Impuesto de la papa",sTRDao.findById(sTR.getSalestaxrateid()).getName() );
		
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testFindAll() {
		sTRDao.save(sTR);
		assertEquals(1,sTRDao.findAll().size() );
		
	}
	

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testFindByStateProvince() {
		
		
		sTRDao.save(sTR);
		sTR = new Salestaxrate();
		sTR.setTaxrate(new BigDecimal("0.1"));
		sTR.setName("Impuesto del papel");
		
		sP = new Stateprovince();
		sP.setName("Bogotá");
		sP.setStateprovincecode("BOGOT");
		sPDao.save(sP);
		sTR.setStateprovince(sP);
		sTRDao.save(sTR);
		assertEquals(1, sTRDao.findByStateprovince(sP.getStateprovinceid()).size() );
		assertEquals("Impuesto del papel", sTRDao.findByStateprovince(sP.getStateprovinceid()).get(0).getName() );
		
	}
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testUpdate() {
		
		
		sTRDao.save(sTR);
		
		sTR.setTaxrate(new BigDecimal("0.1"));
		sTR.setName("Impuesto del papel");
		sTRDao.update(sTR);
		
		assertAll(
				() -> assertEquals(sTRDao.findById(sTR.getSalestaxrateid()), sTR),
				() -> assertEquals(new BigDecimal("0.1"),sTRDao.findById(sTR.getSalestaxrateid()).getTaxrate()),
				() -> assertEquals("Impuesto del papel",sTRDao.findById(sTR.getSalestaxrateid()).getName()),
				()-> assertEquals(1,sTRDao.findAll().size() )
				
				

					);
		
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testFindByName() {
		
		
		sTRDao.save(sTR);
		sTR = new Salestaxrate();
		sTR.setTaxrate(new BigDecimal("0.1"));
		sTR.setName("Impuesto del papel");
		
		sP = new Stateprovince();
		sP.setName("Bogotá");
		sP.setStateprovincecode("BOGOT");
		sPDao.save(sP);
		sTR.setStateprovince(sP);
		sTRDao.save(sTR);
		assertEquals(1, sTRDao.findByName(sTR.getName()).size());
		assertEquals("Impuesto del papel", sTRDao.findByName(sTR.getName()).get(0).getName());
		
		
		
	}
	
	
	
	
	
	
}
