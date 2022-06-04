package com.icesi.samaca.IntegrationTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
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
import com.icesi.samaca.model.person.Countryregion;
import com.icesi.samaca.repositories.CountryregionRepository;
import com.icesi.samaca.services.CountryregionServiceImp;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Taller1AlexSamacaApplication.class)
public class CountryRegionIntegrationTest  {
	
	
	
	private Countryregion crTester1;
	
	private CountryregionRepository cRRepo;
	
	private CountryregionServiceImp crService;
	
	@Autowired
	public CountryRegionIntegrationTest( CountryregionServiceImp crS, CountryregionRepository crR) {
		cRRepo = crR;
		crService= crS;
		
		
	}
	
	@Nested
	@DisplayName("Save methods for CountryRegion")
	class saveCountryRegion{

		@BeforeEach
		void setup() throws ParseException {
			crTester1 = new Countryregion();

			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date= df.parse("14-03-2022");
			
			LocalDate time = new java.sql.Date(date.getTime()).toLocalDate();

			crTester1.setModifieddate(time);
			crTester1.setName("Macaco");
			crTester1.setCountryregioncode("ARG");

		}
		
		@Test
		void saveCorrectly() {
			
			Countryregion fg = crService.saveCr(crTester1);
			//Countryregion cr = cRRepo.findById(fg.getCountryregioncode()).get();
			assertNotNull(fg);
		}
	}
	
	
	
	

}
