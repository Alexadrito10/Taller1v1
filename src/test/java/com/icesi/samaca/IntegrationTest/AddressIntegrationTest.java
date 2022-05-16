package com.icesi.samaca.IntegrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.constraints.AssertTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.icesi.samaca.Taller1AlexSamacaApplication;
import com.icesi.samaca.model.person.Address;
import com.icesi.samaca.model.person.Stateprovince;
import com.icesi.samaca.repositories.AddressRepository;
import com.icesi.samaca.repositories.StateprovinceRepository;
import com.icesi.samaca.services.AddresServiceImp;
import com.icesi.samaca.services.AddressService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Taller1AlexSamacaApplication.class)
public class AddressIntegrationTest {

	private AddressRepository addresRepo;

	private StateprovinceRepository stateRepo;

	private AddresServiceImp addresService;

	private Address addressTester;

	private Stateprovince stateProvincesTester1;

	@Autowired
	public AddressIntegrationTest(AddressRepository addresRepo,StateprovinceRepository stateRepo,AddresServiceImp addresService) {
		this.addresRepo = addresRepo;
		this.stateRepo = stateRepo;
		this.addresService = addresService;

	}
	@BeforeEach
	void setup() {
		addressTester = new Address();
		addressTester.setAddressid(1);
		addressTester.setAddressline1("Direccion Test");
		addressTester.setCity("Cali");
		addressTester.setPostalcode("123456");


		stateProvincesTester1 = new Stateprovince();
		stateProvincesTester1.setStateprovinceid(57930);
		stateProvincesTester1.setIsonlystateprovinceflag("Y");
		stateProvincesTester1.setName("XiaoLover");

		stateRepo.save(stateProvincesTester1);
		addressTester.setStateprovince(stateProvincesTester1);
	}

	@Test
	void testSaveAddress() {
		Address aux= addresService.saveAddress(addressTester, 1);
		assertNotNull(addresRepo.findById(1));

		Address temp= addresRepo.findById(aux.getAddressid()).get();
		assertNotNull(temp);
		assertEquals(aux.getAddressid(), temp.getAddressid());
		Assertions.assertThat(temp).isInstanceOf(Address.class);
		Assertions.assertThat(temp.getAddressid()).isGreaterThan(0);
		assertEquals(temp.getCity(), "Cali");	
	}

	@Test
	void testSaveAllNull() {
		assertThrows(IllegalArgumentException.class, () ->{
			addresService.saveAddress(null, null);
		});
	}

	@Test
	void TestSaveCityWrong(){
		try {
			addressTester.setCity("C");
			Address aux= addresService.saveAddress(addressTester, 1);
			Address temp= addresRepo.findById(aux.getAddressid()).get();
			assertNull(temp);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();

		}
	}

	@Test
	void TestSavePostalCodeWrong() {
		try {
			addressTester.setPostalcode("2");
		}catch(IllegalArgumentException e) {
			Throwable ex= assertThrows(IllegalArgumentException.class, ()-> addressTester.getPostalcode());
			assertEquals("Hubo un error en la creacion, revise los datos", ex.getMessage());
		}
	}
	@Test
	void TestUpdatePostalNull() {

		Address aux= addresService.saveAddress(addressTester, 1);
		aux.setPostalcode(null);


		assertThrows(IllegalArgumentException.class, () ->{
			addresService.editAddres(aux, 57930);
		});
		assertEquals("123456", addresRepo.findById(1).get().getPostalcode());

	}
	@Test
	void TestUpdateWrongPostalCode() {

		Address aux= addresService.saveAddress(addressTester, 1);
		aux.setPostalcode("111");


		assertThrows(IllegalArgumentException.class, () ->{
			addresService.editAddres(aux, 57930);
		});
		assertEquals("123456", addresRepo.findById(1).get().getPostalcode());
	}
	@Test
	void TestUpdateWrongCity() {

		Address aux= addresService.saveAddress(addressTester, 1);
		aux.setPostalcode("");


		assertThrows(IllegalArgumentException.class, () ->{
			addresService.editAddres(aux, 1);
		});
		assertEquals("Cali", addresRepo.findById(1).get().getCity());

		aux.setPostalcode("Ci");


		assertThrows(IllegalArgumentException.class, () ->{
			addresService.editAddres(aux, 1);
		});
		
		assertEquals("Cali", addresRepo.findById(1).get().getCity());
	}
	@Test
	void TestUpdateWrongAddressLine() {

		Address aux= addresService.saveAddress(addressTester, 1);
		aux.setAddressline1(null);


		assertThrows(IllegalArgumentException.class, () ->{
			addresService.editAddres(aux, 1);
		});
		assertEquals("Direccion Test", addresRepo.findById(1).get().getAddressline1());
		
		
		aux.setAddressline1("");


		assertThrows(IllegalArgumentException.class, () ->{
			addresService.editAddres(aux, 1);
		});
		assertEquals("Direccion Test", addresRepo.findById(1).get().getAddressline1());
	}
	
	@Test
	void TestUpdateWell() {

		Address aux= addresService.saveAddress(addressTester, 1);
		aux.setPostalcode("111111");


		addresService.editAddres(aux, 1);
		assertEquals("111111", addresRepo.findById(1).get().getPostalcode());
	}

}