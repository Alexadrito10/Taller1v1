package com.icesi.samaca.UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

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
import com.icesi.samaca.model.person.Address;
import com.icesi.samaca.model.person.Stateprovince;
import com.icesi.samaca.model.sales.Salestaxrate;
import com.icesi.samaca.repositories.SalestaxrateRepository;
import com.icesi.samaca.repositories.StateprovinceRepository;
import com.icesi.samaca.services.SalestaxrateService;
import com.icesi.samaca.services.SalestaxrateServiceImp;

@ContextConfiguration(classes= Taller1AlexSamacaApplication.class)
@ExtendWith(MockitoExtension.class)
public class SalestaxrateUT {

	private Salestaxrate salestaxrateTester;

	private Stateprovince stateprovinceTester;



	@Mock
	private SalestaxrateRepository salesTRRepo;

	@Mock
	private StateprovinceRepository stateprovinceRepo;

	@InjectMocks
	private SalestaxrateServiceImp salestaxrateService;

	@Nested
	@DisplayName("Save methods for Salestaxes")
	class saveSalesTaxes{

		@BeforeEach
		void setUp() {
			salestaxrateTester = new Salestaxrate();
			stateprovinceTester = new Stateprovince();


			salestaxrateTester.setTaxrate(new BigDecimal(3));
			salestaxrateTester.setName("Fertax");

			stateprovinceTester.setStateprovinceid( new Integer(57930));
			stateprovinceTester.setIsonlystateprovinceflag("Y");
			stateprovinceTester.setName("XiaoLover");

			stateprovinceRepo.save(stateprovinceTester);
			salestaxrateTester.setStateprovince(stateprovinceTester);;



		}
		@Test
		@DisplayName("create a negative saletax saletaxrate object")
		void negativeSaleTaxCreationTest() {
			salestaxrateTester.setTaxrate(new BigDecimal(-3));
			Assertions.assertThrows(IllegalArgumentException.class, () ->{
				salestaxrateService.saveSalesTR(salestaxrateTester);
			} );
			verify(salesTRRepo,times(0)).save(salestaxrateTester);


		}
		@Test
		@DisplayName("create a  saletaxrate object with a name shorter than 5 chars")
		void shorterNameCreationTest() {
			salestaxrateTester.setName("   ");
			Assertions.assertThrows(IllegalArgumentException.class, () ->{
				salestaxrateService.saveSalesTR(salestaxrateTester);
			} );
			verify(salesTRRepo,times(0)).save(salestaxrateTester);


		}
		@Test
		@DisplayName(" create a saletaxrate with Stateprovince nonexistant")
		void nonExistStateCreationTest() {


			salestaxrateTester.setStateprovince(null);
			Assertions.assertThrows(IllegalArgumentException.class, () ->{
				salestaxrateService.saveSalesTR(salestaxrateTester);
			} );

			verify(salesTRRepo,times(0)).save(salestaxrateTester);
		}

		@Test
		@DisplayName(" create a saletaxrate with Stateprovince correct")
		void correctSaleStateCreationTest() {


			when(stateprovinceRepo.findById(57930)).thenReturn(Optional.of(stateprovinceTester));
			when(salesTRRepo.save(salestaxrateTester)).thenReturn(salestaxrateTester);

			Salestaxrate aux = salestaxrateService.saveSalesTR(salestaxrateTester);



			assertEquals(aux.getName(), salestaxrateTester.getName());


			verify(salesTRRepo,times(1)).save(salestaxrateTester);
		}




	} 
	@Nested
	@DisplayName("Edit methods for Salestaxes")
	class editSalesTaxes{
		@BeforeEach
		void setUp() {
			salestaxrateTester = new Salestaxrate();
			stateprovinceTester = new Stateprovince();


			salestaxrateTester.setTaxrate(new BigDecimal(3));
			salestaxrateTester.setName("Fertax");

			stateprovinceTester.setStateprovinceid( new Integer(57930));
			stateprovinceTester.setIsonlystateprovinceflag("Y");
			stateprovinceTester.setName("XiaoLover");

			stateprovinceRepo.save(stateprovinceTester);
			salestaxrateTester.setStateprovince(stateprovinceTester);;
			
			when(stateprovinceRepo.findById(57930)).thenReturn(Optional.of(stateprovinceTester));
			when(salesTRRepo.save(salestaxrateTester)).thenReturn(salestaxrateTester);
			
			salestaxrateService.saveSalesTR(salestaxrateTester);

		}
		
		@Test
		@DisplayName(" edit a saletaxrate with negative saleTax")
		void editNegativeSaleTaxTest() {
			salestaxrateTester.setTaxrate(new BigDecimal(-3));
			Assertions.assertThrows(IllegalArgumentException.class, () ->{
				salestaxrateService.editSalesTR(salestaxrateTester);
			} );
			

			
		}
		@Test
		@DisplayName(" edit a saletaxrate with a short name")
		void editShorterNameTest() {
			salestaxrateTester.setName("Lui");
			Assertions.assertThrows(IllegalArgumentException.class, () ->{
				salestaxrateService.editSalesTR(salestaxrateTester);
			} );
			

			
		}
		@Test
		@DisplayName(" edit a saletaxrate with a nonexist stateID")
		void editNonExistStateIdTest() {
			salestaxrateTester.setStateprovince(null);;
			Assertions.assertThrows(IllegalArgumentException.class, () ->{
				salestaxrateService.editSalesTR(salestaxrateTester);
			} );
			

			
		}
		@Test
		@DisplayName(" edit a saletaxrate correctly")
		void correctEditTest() {
			
			when(stateprovinceRepo.findById(57932)).thenReturn(Optional.of(stateprovinceTester));
			when(salesTRRepo.save(salestaxrateTester)).thenReturn(salestaxrateTester);
			
			salestaxrateTester.setName("Holiwis");
			salestaxrateTester.setTaxrate(new BigDecimal(5));
			
			stateprovinceTester.setStateprovinceid( new Integer(57932));
			stateprovinceTester.setIsonlystateprovinceflag("Y");
			stateprovinceTester.setName("AyatoLover");

			stateprovinceRepo.save(stateprovinceTester);
			salestaxrateTester.setStateprovince(stateprovinceTester);
			
			salestaxrateService.saveSalesTR(salestaxrateTester);
			
			
			
			 Salestaxrate aux = salestaxrateService.editSalesTR(salestaxrateTester);
			
			
			assertEquals("Holiwis", aux.getName());
			

			
		}
		
		
		
		
		
		
	}



}
