package com.icesi.samaca.IntegrationTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.icesi.samaca.Taller1AlexSamacaApplication;
import com.icesi.samaca.model.person.Address;
import com.icesi.samaca.model.person.Stateprovince;
import com.icesi.samaca.model.sales.Salestaxrate;
import com.icesi.samaca.repositories.SalestaxrateRepository;
import com.icesi.samaca.repositories.StateprovinceRepository;
import com.icesi.samaca.services.SalestaxrateServiceImp;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Taller1AlexSamacaApplication.class)
public class SalestaxrateIntegrationTest {
	
	private Salestaxrate sTaxrateTester1;
	
	private SalestaxrateRepository sTaxrateRepo;
	
	private StateprovinceRepository stateProvRepo;
	
	private Stateprovince stateProvincesTester1;
	
	private SalestaxrateServiceImp sTaxrateService;
	
	
	@Autowired
	public SalestaxrateIntegrationTest (SalestaxrateRepository sTaxrateRepo,SalestaxrateServiceImp sTaxrateService,StateprovinceRepository stateProvRepo) {
		
		this.sTaxrateRepo = sTaxrateRepo;
		this.stateProvRepo = stateProvRepo;
		this.sTaxrateService = sTaxrateService;
	}
	
	@BeforeEach
	void setup() {
		sTaxrateTester1 = new Salestaxrate();
		sTaxrateTester1.setSalestaxrateid(40);
		sTaxrateTester1.setName("Tayloor");

		sTaxrateTester1.setTaxrate(new BigDecimal(50));
		
		stateProvincesTester1 = new Stateprovince();
		stateProvincesTester1.setStateprovinceid(57930);
		stateProvincesTester1.setIsonlystateprovinceflag("Y");
		stateProvincesTester1.setName("XiaoLover");
		
		stateProvRepo.save(stateProvincesTester1);
		sTaxrateTester1.setStateprovinceid(stateProvincesTester1.getStateprovinceid());
	}
	
	@Test
	void TestSaveSalesTax() {
		Salestaxrate aux= sTaxrateService.saveSalesTR(sTaxrateTester1);
		assertNotNull(sTaxrateRepo.findById(40));
		
		Salestaxrate temp= sTaxrateRepo.findById(aux.getSalestaxrateid()).get();
		assertNotNull(temp);
	}
	

	
	@Test
	void TestSaveTaxWrong(){
		try {
			sTaxrateTester1.setTaxrate(new BigDecimal(-20));
			Salestaxrate aux= sTaxrateService.saveSalesTR(sTaxrateTester1);
			Salestaxrate temp= sTaxrateRepo.findById(aux.getSalestaxrateid()).get();
			assertNull(temp);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void TestSaveNameWrong(){
		try {
			sTaxrateTester1.setName("A");
			Salestaxrate aux= sTaxrateService.saveSalesTR(sTaxrateTester1);
			Salestaxrate temp= sTaxrateRepo.findById(aux.getSalestaxrateid()).get();
			assertNull(temp);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
}