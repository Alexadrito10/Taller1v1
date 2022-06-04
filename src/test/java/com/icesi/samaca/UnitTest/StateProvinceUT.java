package com.icesi.samaca.UnitTest;

import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.icesi.samaca.backend.model.person.Countryregion;
import com.icesi.samaca.backend.model.person.Stateprovince;
import com.icesi.samaca.backend.model.sales.Salesterritory;
import com.icesi.samaca.backend.repositories.CountryregionRepository;
import com.icesi.samaca.backend.repositories.SalesterritoryRepository;
import com.icesi.samaca.backend.repositories.StateprovinceRepository;
import com.icesi.samaca.backend.services.StateprovinceServiceImp;

public class StateProvinceUT {
	
	Stateprovince stateprovinceTester;
	Countryregion crTester1;
	Salesterritory salesterritoryTester;
	
	
	
	@Mock
	private StateprovinceRepository stateprovinceRepo;
	
	@Mock
	private SalesterritoryRepository salesterritoryRepo;
	
	@Mock
	private CountryregionRepository countryregionRepo;
	
	@InjectMocks
	private StateprovinceServiceImp stateProvinceService;
	
	@Nested
	class SaveStateProvince{
		
		@BeforeEach
		void setUp() throws ParseException {
			
			
			
			
			
			crTester1 = new Countryregion();
			salesterritoryTester = new Salesterritory();
			stateprovinceTester = new Stateprovince();

			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date= df.parse("14-03-2022");
			
			LocalDate time = new java.sql.Date(date.getTime()).toLocalDate();
			
			crTester1.setModifieddate(time);
			crTester1.setName("Macaco");
			crTester1.setCountryregioncode("ARG");
			
			stateprovinceTester.setCountryregion(crTester1);
			
			
			salesterritoryTester.setTerritoryid(12);
			
			stateprovinceTester.setStateprovinceid( 57930);
			stateprovinceTester.setIsonlystateprovinceflag("Y");
			stateprovinceTester.setName("XiaoLover");
			
			stateProvinceService = new StateprovinceServiceImp(countryregionRepo, stateprovinceRepo, salesterritoryRepo);

			
			
			

			
		}
		@Test
		@DisplayName("Create a State with a code lesser than 5 chars")
		void WrongProvinceCodeCreationTest() {
			
			
			
			stateprovinceTester.setStateprovincecode("AMST");
			Assertions.assertThrows(IllegalArgumentException.class, () ->{
				stateProvinceService.saveStateprov(stateprovinceTester, null);
			} );
			
			
		}
		
		@Test
		@DisplayName("Create a State wrong flagFormat")
		void WrongFlagTest() {
			
			
			
			stateprovinceTester.setIsonlystateprovinceflag("N");
			Assertions.assertThrows(NullPointerException.class, () ->{
				stateProvinceService.saveStateprov(stateprovinceTester, null);
			} );
			
			
			
			
			
		}
		@Test
		@DisplayName("Create a State with a shorter name")
		void ShorterNameCreationTest() {
			
			
			
			
			
			stateprovinceTester.setName("al");
			Assertions.assertThrows(Exception.class, () ->{
				stateProvinceService.saveStateprov(stateprovinceTester, salesterritoryTester.getTerritoryid());
			} );
			
			
			
			
			
		}
		
		
		
	}

}
