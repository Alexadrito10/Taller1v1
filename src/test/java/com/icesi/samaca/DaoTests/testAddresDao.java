package com.icesi.samaca.DaoTests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.icesi.samaca.Taller1AlexSamacaApplication;
import com.icesi.samaca.dao.AddressDaoImp;
import com.icesi.samaca.dao.StateProvinceDAO;
import com.icesi.samaca.dao.StateProvinceDaoImp;
import com.icesi.samaca.model.person.Address;
import com.icesi.samaca.model.person.Stateprovince;



@ExtendWith(SpringExtension.class)

//@SpringBootTest
@DataJpaTest
@ContextConfiguration(classes = Taller1AlexSamacaApplication.class)

public class testAddresDao {
	
	@Autowired
	private AddressDaoImp addressDaoImp;
	
	@Autowired
	private StateProvinceDaoImp stateDaoImp;
	
	
	
	private Address addr;
	
	private Stateprovince sP;
	
	
	@BeforeEach
	void setup1() {
		addr = new Address();
		addr.setAddressline1("Esta no es mi casa");
		addr.setAddressline2("Esta no es mi puerta");
		addr.setCity("Cali");
		addr.setPostalcode("COL-CALI");
		addr.setSpatiallocation("Comuna 5");
		
		sP = new Stateprovince();
		sP.setName("Valle del Cauca");
		sP.setStateprovincecode("VALCA");
		
		stateDaoImp.save(sP);
		addr.setStateprovince(sP);
		addressDaoImp.save(addr);
		
		
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testFindAddressById() {
		
		addressDaoImp.save(addr);
		
		assertEquals(addr,addressDaoImp.findById(addr.getAddressid()));
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testUpdate() {
		
		addressDaoImp.save(addr);
		
		addr.setCity("Buga");
		addr.setAddressline1("La bugeña de Sossa");
		addr.setAddressline2("Sosa no sabe dar bugeña");
		
		addressDaoImp.update(addr);
		
		
		
		
		assertAll(
				() -> assertEquals(addressDaoImp.findById(addr.getAddressid()), addr),
				() -> assertEquals("Comuna 5",addressDaoImp.findById(addr.getAddressid()).getSpatiallocation()),
				() -> assertEquals(1,addressDaoImp.findAll().size())

					);
		
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testSave() {
		
		
		addr.setCity("Buga");
		
		addressDaoImp.save(addr);
		assertEquals(addressDaoImp.findById(addr.getAddressid()), addr);
		
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testFindAll() {
		addressDaoImp.save(addr);
		addr = new Address();
		addr.setAddressline1("Mi casita");
		addr.setAddressline2("Pasando el parque");
		addr.setCity("Jamundí");
		addr.setPostalcode("COL-CALI");
		addr.setSpatiallocation("Aqui no sé");
		
		addressDaoImp.save(addr);
		assertEquals(2, addressDaoImp.findAll().size());
		
		
	}
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testFindByCity() {
		addressDaoImp.save(addr);
		addr = new Address();
		addr.setAddressline1("Mi casita");
		addr.setAddressline2("Pasando el parque");
		addr.setCity("Jamundí");
		addr.setPostalcode("COL-CALI");
		addr.setSpatiallocation("Aqui no sé");
		addressDaoImp.save(addr);
		
		List<Address> result = addressDaoImp.findByCity("Cali");
		assertEquals(1,result.size());
		
		
		
		
	}
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void testFindByStateProv() {
		addressDaoImp.save(addr);
		addr = new Address();
		addr.setAddressline1("Mi casita");
		addr.setAddressline2("Pasando el parque");
		addr.setCity("Jamundí");
		addr.setPostalcode("COL-CALI");
		addr.setSpatiallocation("Aqui no sé");
		addr.setStateprovince(sP);
		addressDaoImp.save(addr);
		
		List<Address> result = addressDaoImp.findByStateProv(sP.getStateprovinceid());
		assertEquals(2,result.size());
		
		
		
		
	}
	
	
	

	
	
	
	
	
	

	
}
