package com.icesi.samaca.UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import com.icesi.samaca.Taller1AlexSamacaApplication;
import com.icesi.samaca.backend.model.person.Countryregion;
import com.icesi.samaca.backend.repositories.CountryregionRepository;
import com.icesi.samaca.backend.services.CountryregionServiceImp;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Table;

@ContextConfiguration(classes= Taller1AlexSamacaApplication.class)
@ExtendWith(MockitoExtension.class)
public class CountryRegionUT {

	private Countryregion crTester1;
	@Mock
	private CountryregionRepository cRRepo;

	@InjectMocks
	private CountryregionServiceImp cRService;
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
		@DisplayName("Saves a nullCountryRegion")
		void savesNullCodeCountryRegionTest() {

			crTester1 = null;
			Assertions.assertThrows(NullPointerException.class, () ->{
				cRService.saveCr(crTester1);
			} );
			verify(cRRepo,times(0)).save(crTester1);
		}

		@Test
		@DisplayName("Saves a countryRegion with a Countrycode lesser than 1 character")
		void savesACodewith0charTest() {



			crTester1.setCountryregioncode("");
			Assertions.assertThrows(IllegalArgumentException.class, ()->{
				cRService.saveCr(crTester1);} );
			
			verify(cRRepo,times(0)).save(crTester1);


		}
		@Test
		@DisplayName("Saves a countryRegion with a Countrycode greater than 4 character")
		void savesACodewith5charTest() {

			crTester1.setCountryregioncode("code5");
			Assertions.assertThrows(IllegalArgumentException.class, ()->{
				cRService.saveCr(crTester1);} );
			verify(cRRepo,times(0)).save(crTester1);

		}

		@Test
		@DisplayName("Saves a countryRegion with a Countrycode that fulfill the constraints")
		void savesACodeThatWorksTest() {


			crTester1.setCountryregioncode("RCO");
			when(cRRepo.save(crTester1)).thenReturn(crTester1);

			Countryregion aux = cRService.saveCr(crTester1);

			assertNotNull(aux);
			assertEquals("Macaco",aux.getName());
			assertEquals("RCO", aux.getCountryregioncode());
			verify(cRRepo).save(crTester1);	
			verify(cRRepo,times(1)).save(crTester1);
		


		}
		@Test
		@DisplayName("Saves a CountryRegion with a name that fulfill the constraints")
		void saveANameThatWorksTest() {

			crTester1.setCountryregioncode("RCO");
			crTester1.setName("NoSoySantiago");
			when(cRRepo.save(crTester1)).thenReturn(crTester1);

			Countryregion aux = cRService.saveCr(crTester1);

			assertNotNull(aux);
			assertEquals("NoSoySantiago",aux.getName());
			verify(cRRepo).save(crTester1);	
			verify(cRRepo,times(1)).save(crTester1);



		}@Test
		@DisplayName("Saves a CountryRegion with a name with less than 5 characters")
		void saveAShortNameTest() {

			crTester1.setCountryregioncode("RCO");
			crTester1.setName("Ana");

			Assertions.assertThrows(IllegalArgumentException.class, ()->{
				cRService.saveCr(crTester1);} );
			
			verify(cRRepo,times(0)).save(crTester1);
			

		}

	}
	@Nested
	@DisplayName("Edit methods for CountryRegion")
	class EditCountryRegion{

		@BeforeEach
		void setup() throws ParseException {

			crTester1 = new Countryregion();

			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date= df.parse("14-03-2022");
			
			LocalDate time = new java.sql.Date(date.getTime()).toLocalDate();

			crTester1.setModifieddate(time);
			crTester1.setName("Macaco");
			crTester1.setCountryregioncode("COL");
			cRService.saveCr(crTester1);


		}
		@Test
		@DisplayName("Edit a CountryRegion with a name with less than 5 characters")
		void updateForAShortNameTest(){


			crTester1.setName("Joy");
			Assertions.assertThrows(IllegalArgumentException.class, ()->{
				cRService.editCr(crTester1);} );

		}@Test
		@DisplayName("Edit a CountryRegion and add a null name ")
		void updateForANullNameTest(){


			crTester1.setName(null);
			Assertions.assertThrows(IllegalArgumentException.class, ()->{
				cRService.editCr(crTester1);} );

		}
		@Test
		@DisplayName("Edit a CountryRegion thatDoesn´t exist ")
		void updateAnNonExistentCountryRegionTest(){


			crTester1.setCountryregioncode("GER");
			Assertions.assertThrows(IllegalArgumentException.class, ()->{
				cRService.editCr(crTester1);} );
			
			

		}	
		



	}






}
