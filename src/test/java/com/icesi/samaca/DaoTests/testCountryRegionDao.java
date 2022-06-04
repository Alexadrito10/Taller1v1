package com.icesi.samaca.DaoTests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.icesi.samaca.Taller1AlexSamacaApplication;
import com.icesi.samaca.backend.dao.CountryRegionDAO;
import com.icesi.samaca.backend.dao.CountryRegionDaoImp;
import com.icesi.samaca.backend.model.person.Countryregion;


@ExtendWith(SpringExtension.class)

//@SpringBootTest
@DataJpaTest
@ContextConfiguration(classes = Taller1AlexSamacaApplication.class)
@TestMethodOrder(OrderAnnotation.class)

public class testCountryRegionDao {
	
	@Autowired
	CountryRegionDaoImp cRDao;
	
	private Countryregion cR;
	
	@BeforeEach
	void setup1() {
		
		cR = new Countryregion();
		cR.setCountryregioncode("COL");
		cR.setName("Colombia");
		
	}
	@Order(1)
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testSave() {
		cRDao.save(cR);
		assertEquals(cRDao.findById(cR.getCountryregionid()), cR);
		
		
	}
	
	@Order(2)
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findById() {
		
		cRDao.save(cR);
		Integer a = cR.getCountryregionid();
		System.out.println("--------------------------------"+" El codigo es: "+a);
		cR = new Countryregion();
		cR.setCountryregioncode("CHI");
		cR.setName("Chile");
		cRDao.save(cR);
		assertEquals(cRDao.findById(cR.getCountryregionid()).getName(), "Chile");
		assertEquals(cRDao.findById(a).getName(),"Colombia");
		assertEquals(Integer.parseInt("2"),a);
		
		
	}
	@Order(3)
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testFindAll() {
		
		cRDao.save(cR);
		assertEquals(1,cRDao.findAll().size());
		
	}
	
	@Order(4)
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testUpdate() {
		
		cRDao.save(cR);
		
		cR.setCountryregioncode("VNZ");
		cR.setName("VENEZUELA");

		cRDao.update(cR);
		
		assertAll(
				() -> assertEquals(cRDao.findById(cR.getCountryregionid()), cR),
				() -> assertEquals("VNZ",cRDao.findById(cR.getCountryregionid()).getCountryregioncode()),
				() -> assertEquals("VENEZUELA",cRDao.findById(cR.getCountryregionid()).getName()),
				()-> assertEquals(1,cRDao.findAll().size() )

					);
		
		
	}
	
	
		
	
	
	


}
