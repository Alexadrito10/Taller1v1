package com.icesi.samaca.UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import com.icesi.samaca.Taller1AlexSamacaApplication;
import com.icesi.samaca.model.person.Address;
import com.icesi.samaca.model.person.Stateprovince;
import com.icesi.samaca.repositories.AddressRepository;
import com.icesi.samaca.repositories.StateprovinceRepository;
import com.icesi.samaca.services.AddresServiceImp;

@ContextConfiguration(classes= Taller1AlexSamacaApplication.class)
@ExtendWith(MockitoExtension.class)
public class AddressUT {
	
	
	private Address addressTester1;
	
	private Stateprovince stateProvincesTester1;
	
	
	@Mock
	private AddressRepository addressRepo;
	
	@Mock
	private StateprovinceRepository stateProRepository;
	
	
	@InjectMocks
	private AddresServiceImp addresServiceImp;
	
	@Nested
	@DisplayName("Save methods for Address")
	class saveAddress{
		
		@BeforeEach
		void setUp() throws ParseException {
			addressTester1 = new Address();
			stateProvincesTester1 = new Stateprovince();
			
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date= df.parse("14-03-2022");
			
			LocalDate time = new java.sql.Date(date.getTime()).toLocalDate();

			
			addressTester1.setAddressline1("Cra 1d 1b #56-154");
			addressTester1.setAddressline2("Cra 1d 56-20");
			addressTester1.setCity("Cali");
			addressTester1.setModifieddate(time);
			addressTester1.setPostalcode("066322");
			
			
			SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
			Date date1= df.parse("13-01-2022");
			long timeLong1 = date.getTime();
			LocalDate time1 = new java.sql.Date(date1.getTime()).toLocalDate();
			
			stateProvincesTester1.setStateprovinceid( new Integer(57930));
			stateProvincesTester1.setIsonlystateprovinceflag("Y");
			stateProvincesTester1.setName("XiaoLover");
			
			stateProRepository.save(stateProvincesTester1);
			addressTester1.setStateprovince(stateProvincesTester1);
			
			
			
			
		}
		@Test
		@DisplayName("Creation of a null address")
		void nullAddresCreationTest(){
			addressTester1 = null;
			Assertions.assertThrows(IllegalArgumentException.class, () ->{
				addresServiceImp.saveAddress(addressTester1);
			} );
			verify(addressRepo,times(0)).save(addressTester1);
			
			
		}
		@Test
		@DisplayName("Creation of an address that have null on his addressline1")
		void addresLine1isNullCreationTest() {
			addressTester1.setAddressline1(null);
			Assertions.assertThrows(IllegalArgumentException.class, () ->{
				addresServiceImp.saveAddress(addressTester1);
			} );
			verify(addressRepo,times(0)).save(addressTester1);
		}
		@Test
		@DisplayName("Creation of an address with a city name that have less than 3 chars")
		void cityNameShorterCreationTest() {
			addressTester1.setCity("Ca");;
			Assertions.assertThrows(IllegalArgumentException.class, () ->{
				addresServiceImp.saveAddress(addressTester1);
			} );
			verify(addressRepo,times(0)).save(addressTester1);
			
		}
		@Test
		@DisplayName("Creation of an address with a city name that is blank")
		void cityNameBlankCreationTest() {
			addressTester1.setCity("    ");;
			Assertions.assertThrows(IllegalArgumentException.class, () ->{
				addresServiceImp.saveAddress(addressTester1);
			} );
			verify(addressRepo,times(0)).save(addressTester1);
		
		}@Test
		@DisplayName("Creation of an address with incorrect char for postalcode")
		void WrongpostalCodeCreationTest() {
			
			
			addressTester1.setPostalcode("12");
			Assertions.assertThrows(IllegalArgumentException.class, () ->{
				addresServiceImp.saveAddress(addressTester1);
			} );
			verify(addressRepo,times(0)).save(addressTester1);
			
			
		}@Test
		@DisplayName("Creation of an addres with a nonexist stateprovince ")
		void nonExistentStateCreationTest() {
			addressTester1.setStateprovince(null);
			Assertions.assertThrows(IllegalArgumentException.class, () ->{
				addresServiceImp.saveAddress(addressTester1);
			} );
			verify(addressRepo,times(0)).save(addressTester1);
			
		}
		
		@Test
		@DisplayName("Creation of a correct address")
		void createACorrectAddres() {
			
			addressTester1.setAddressid(new Integer(32));
			when(stateProRepository.findById(new Integer(57930))).thenReturn(Optional.of(stateProvincesTester1));
			when(addressRepo.save(addressTester1)).thenReturn(addressTester1);
			
			Address aux = addresServiceImp.saveAddress(addressTester1);
			
			assertEquals(new Integer(57930), aux.getStateprovince().getStateprovinceid());
			
			verify(addressRepo,times(1)).save(addressTester1);
			
			
		}
		
		
		
		
		
	}
	@Nested
	@DisplayName("Edit methods for Address")
	class editAddress{
		@BeforeEach
		void setUp() throws ParseException{
			addressTester1 = new Address();
			stateProvincesTester1 = new Stateprovince();
			
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date= df.parse("14-03-2022");
			
			LocalDate time = new java.sql.Date(date.getTime()).toLocalDate();
			
			addressTester1.setAddressline1("Cra 1d 1b #56-154");
			addressTester1.setAddressline2("Cra 1d 56-20");
			addressTester1.setCity("Cali");
			addressTester1.setModifieddate(time);
			addressTester1.setPostalcode("066322");
			addressTester1.setAddressid(new Integer(32));
			
			
			SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
			Date date1= df.parse("13-01-2022");
			long timeLong1 = date.getTime();
			LocalDate time1 = new java.sql.Date(date1.getTime()).toLocalDate();
			
			stateProvincesTester1.setStateprovinceid( new Integer(57930));
			stateProvincesTester1.setIsonlystateprovinceflag("Y");
			stateProvincesTester1.setName("XiaoLover");
			
			stateProRepository.save(stateProvincesTester1);
			addressTester1.setStateprovince(stateProvincesTester1);
			
			when(stateProRepository.findById(new Integer(57930))).thenReturn(Optional.of(stateProvincesTester1));
			when(addressRepo.save(addressTester1)).thenReturn(addressTester1);
			
			addresServiceImp.saveAddress(addressTester1);
			
		}
		@Test
		@DisplayName("edit of an addres to an addresline1 null")
		void editAddresLine1NullTest() {
			addressTester1.setAddressline1(null);
			//System.out.println(addressRepo.findById(new Integer(32)));
			Assertions.assertThrows(IllegalArgumentException.class, () ->{
				addresServiceImp.editAddres(addressTester1);
			} );
			
		}
		@Test
		@DisplayName("edit of an addres to a cityname shorter than 3")
		void editCityShorterTest() {
			addressTester1.setCity("Ca");
			//System.out.println(addressRepo.findById(new Integer(32)));
			Assertions.assertThrows(IllegalArgumentException.class, () ->{
				addresServiceImp.editAddres(addressTester1);
			} );
			
		}
		
		@Test
		@DisplayName("edit of an addres to an postalcode in wrong format")
		void editWrongPostalCodeTest() {
			addressTester1.setPostalcode("00");;
			//System.out.println(addressRepo.findById(new Integer(32)));
			Assertions.assertThrows(IllegalArgumentException.class, () ->{
				addresServiceImp.editAddres(addressTester1);
			} );
			
		}
		@Test
		@DisplayName("Edit for correct addres in the correct way")
		void correctEditTest() {
			addressTester1.setAddressline1("Cra 12 #45-30");
			addressTester1.setCity("Medallo");
			addressTester1.setPostalcode("X1A0M4");
			
			System.out.println(addressTester1.getStateprovince().getStateprovinceid().compareTo( stateProRepository.findById(57930).get().getStateprovinceid()));
			Address aux = addresServiceImp.editAddres(addressTester1);
			
			assertEquals("Cra 12 #45-30",aux.getAddressline1());
			assertEquals("Medallo",aux.getCity());
			assertEquals("X1A0M4",aux.getPostalcode());
			
			
			
		}
		
		
		
		
	}

	
	
	
	
	
	

}
