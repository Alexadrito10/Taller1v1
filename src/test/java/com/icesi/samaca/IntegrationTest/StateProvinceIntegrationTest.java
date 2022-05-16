package com.icesi.samaca.IntegrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.icesi.samaca.Taller1AlexSamacaApplication;
import com.icesi.samaca.model.person.Address;
import com.icesi.samaca.model.person.Countryregion;
import com.icesi.samaca.model.person.Stateprovince;
import com.icesi.samaca.model.sales.Salesterritory;
import com.icesi.samaca.repositories.CountryregionRepository;
import com.icesi.samaca.repositories.SalestaxrateRepository;
import com.icesi.samaca.repositories.SalesterritoryRepository;
import com.icesi.samaca.repositories.StateprovinceRepository;
import com.icesi.samaca.services.SalestaxrateServiceImp;
import com.icesi.samaca.services.StateprovinceServiceImp;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Taller1AlexSamacaApplication.class)
public class StateProvinceIntegrationTest {
	
	StateprovinceRepository stateprovinceRepo;
	CountryregionRepository countryregionRepo;
	SalesterritoryRepository salesterritoryRepo;
	
	private StateprovinceServiceImp stateProvinceService;
	
	Stateprovince stateprovinceTester;
	Countryregion crTester1;
	Salesterritory salesterritoryTester;
	
	
	
	@Autowired
	public StateProvinceIntegrationTest(StateprovinceRepository stateprovinceRepo,
										CountryregionRepository countryregionRepo,SalesterritoryRepository salesterritoryRepo
										,StateprovinceServiceImp stateProvinceService) {
		
		
		this.stateprovinceRepo = stateprovinceRepo;
		
		this.countryregionRepo = countryregionRepo;
		
		this.salesterritoryRepo = salesterritoryRepo;
		
		this.stateProvinceService = stateProvinceService;
		
		
	}
	
	@BeforeEach
	void setup() throws ParseException {
		
		crTester1 = new Countryregion();
		salesterritoryTester = new Salesterritory();
		stateprovinceTester = new Stateprovince();

		crTester1.setName("Macaco");
		crTester1.setCountryregioncode("BRA");
		
		stateprovinceTester.setCountryregion(crTester1);
		
		stateprovinceTester.setStateprovinceid(57930);
		stateprovinceTester.setIsonlystateprovinceflag("Y");
		stateprovinceTester.setName("XiaoLover");
		stateprovinceTester.setStateprovincecode("COLOM");
		
		stateprovinceTester.setTerritoryid(12);
	}
	
	
	@Test
	void saveStateProvince() {
		Stateprovince aux= stateProvinceService.saveStateprov(stateprovinceTester, "BRA", 12);
		assertNotNull(aux);
	}
	
	
	
	

}