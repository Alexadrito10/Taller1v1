package com.icesi.samaca.BusinessDelegate;

import com.icesi.samaca.Taller1AlexSamacaApplication;
import com.icesi.samaca.backend.model.hr.Employee;
import com.icesi.samaca.backend.model.person.*;
import com.icesi.samaca.backend.model.sales.Salestaxrate;
import com.icesi.samaca.backend.model.sales.Salesterritory;
import com.icesi.samaca.frontend.businessdelegate.BusinessDelegate;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = Taller1AlexSamacaApplication.class)
public class BusinessDelegateTest {

	private final String URL_ADDRESS = "http://localhost:8080/api/addresses/";
	private final String URL_COUNTRY = "http://localhost:8080/api/countryregions/";
	private final String URL_EMPLOYEE = "http://localhost:8080/api/employees/";
	private final String URL_PERSON = "http://localhost:8080/api/persons/";
	private final String URL_SALESTAX = "http://localhost:8080/api/salestaxrates/";
	private final String URL_SALESTERRITORY = "http://localhost:8080/api/salesterritories/";
	private final String URL_STATEPROVINCE = "http://localhost:8080/api/stateprovinces/";
	private final String URL_USERAPP = "http://localhost:8080/api/userapps/";
	

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    BusinessDelegate businessDelegate;
    Address address;
    Countryregion countryregion;
    Employee employee;
    Person person;
    Salestaxrate salestaxrate;
    Salesterritory salesterritory;
    Stateprovince stateprovince;
    UserApp userApp;

    @Nested
    @DisplayName("Test Class for Address")
    class AddressTest{

        @BeforeEach
        void setup(){
            address = new Address();
            address.setAddressid(5);
            address.setAddressline1("Calle 40");
            address.setAddressline2("# 5-21");
            address.setCity("Cali");
            address.setPostalcode("70896C");

            businessDelegate = new BusinessDelegate();
            businessDelegate.setRestTemplate(restTemplate);
        }

        @Test
        void getAllAddress(){
            Address[] addresses = new Address[5];
            for(int i = 0; i < addresses.length; i++){
                Address aux = new Address();
                addresses[i] = aux;
                businessDelegate.addAddress(aux);
            }

            when(restTemplate.getForObject(URL_ADDRESS, Address[].class)).thenReturn(addresses);

            assertEquals(businessDelegate.getAdresses().size(), addresses.length);
        }

        @Test
        void addAddressTest(){
            Address temp = new Address();
            temp.setAddressid(1);
            temp.setAddressline1("Calle 60");
            temp.setAddressline2("# 3-41");
            temp.setCity("Medellin");
            temp.setPostalcode("71856M");

            when(restTemplate.postForObject(URL_ADDRESS, temp, Address.class)).thenReturn(temp);
            assertEquals(businessDelegate.addAddress(temp).getAddressline1(), temp.getAddressline1());
        }

        @Test
        void findByIdAddressTest(){
            when(restTemplate.getForObject(URL_ADDRESS+address.getAddressid(), Address.class)).thenReturn(address);
            assertEquals(businessDelegate.findByIdAddress(address.getAddressid()).getAddressid(), address.getAddressid());
        }

        @Test
        void updateAddressTest(){
            businessDelegate.addAddress(address);
            address.setPostalcode("8080C");
            businessDelegate.updateAddress(address);

            verify(restTemplate).put(URL_ADDRESS+address.getAddressid(), address, Address.class);
        }

        @Test
        void deleteAddressTest(){
            businessDelegate.addAddress(address);
            businessDelegate.deleteAddress(address.getAddressid());

            verify(restTemplate).delete(URL_ADDRESS+address.getAddressid());
        }
    }

    @Nested
    @DisplayName("Test Class for CountryRegion")
    class CountryRegionTest{

        @BeforeEach
        void setup(){
            Countryregion countryregion = new Countryregion();
            countryregion.setCountryregionid(5);
            countryregion.setCountryregioncode("1204R");
            countryregion.setName("Region A");

            businessDelegate = new BusinessDelegate();
            businessDelegate.setRestTemplate(restTemplate);
        }

        @Test
        void getAllCountryRegion(){
            Countryregion[] countryregions = new Countryregion[5];
            for (int i = 0; i < countryregions.length; i++){
                Countryregion aux = new Countryregion();
                countryregions[i] = aux;
                businessDelegate.addCountry(aux);
            }
            when(restTemplate.getForObject(URL_COUNTRY, Countryregion[].class)).thenReturn(countryregions);
            assertEquals(businessDelegate.getCountries().size(), countryregions.length);
        }

        @Test
        void addCountryRegionTest(){
            Countryregion temp = new Countryregion();
            temp.setCountryregionid(5);
            temp.setName("Region B");
            temp.setCountryregioncode("745B");

            when(restTemplate.postForObject(URL_COUNTRY, temp, Countryregion.class)).thenReturn(temp);
            assertEquals(businessDelegate.addCountry(temp).getCountryregionid(), temp.getCountryregionid());
        }

        @Test
        void findByIdCountryRegion(){
            Countryregion aux = new Countryregion();
            aux.setCountryregionid(10);
            when(restTemplate.getForObject(URL_COUNTRY+aux  .getCountryregionid(), Countryregion.class)).thenReturn(aux );
            assertEquals(businessDelegate.findByIdCountryRegion(aux .getCountryregionid()).getCountryregionid(), aux    .getCountryregionid());
        }

        @Test
        void updateCountryRegionTest(){
            Countryregion aux = new Countryregion();
            businessDelegate.addCountry(aux);
            aux.setCountryregioncode("COL_B");
            businessDelegate.updateCountry(aux);

            verify(restTemplate).put(URL_COUNTRY+aux.getCountryregionid(), aux, Countryregion.class);
        }

        @Test
        void deleteCountryRegionTest(){
            Countryregion aux = new Countryregion();
            businessDelegate.addCountry(aux);
            businessDelegate.deleteCountry(aux.getCountryregionid());

            verify(restTemplate).delete(URL_COUNTRY+aux.getCountryregionid());
        }
    }

    @Nested
    @DisplayName("Test Class for Employee")
    class EmployeeTest{

        @BeforeEach
        void setup() throws ParseException {
            Employee employee = new Employee();
            employee.setBusinessentityid(5);

            employee.setGender("Male");
            employee.setHiredate(new Date());
            employee.setJobtitle("Engineer");
            employee.setLoginid("1234");

            employee.setNationalidnumber("12354568798");
            employee.setOrganizationnode("ONG");
            employee.setSickleavehours(50);
            employee.setVacationhours(30);

            businessDelegate = new BusinessDelegate();
            businessDelegate.setRestTemplate(restTemplate);
        }

        @Test
        void getAllEmployeeTest(){
            Employee[] employees = new Employee[5];
            for (int i = 0; i < employees.length; i++){
                Employee aux = new Employee();
                employees[i] = aux;
                businessDelegate.addEmployee(aux);
            }

            when(restTemplate.getForObject(URL_EMPLOYEE, Employee[].class)).thenReturn(employees);
            assertEquals(businessDelegate.getEmployees().size(), employees.length);
        }

        @Test
        void addEmployeeTest() throws ParseException {
            Employee temp = new Employee();

            temp.setGender("Female");
            temp.setHiredate(new Date());
            temp.setJobtitle("Designer");

            temp.setNationalidnumber("987654321");
            temp.setOrganizationnode("ONG");
            temp.setSickleavehours(30);
            temp.setVacationhours(40);

            when(restTemplate.postForObject(URL_EMPLOYEE, temp, Employee.class)).thenReturn(temp);
            assertEquals(businessDelegate.addEmployee(temp).getBusinessentityid(), temp.getBusinessentityid());
        }

        @Test
        void findByIdEmployeeTest(){
            Employee aux = new Employee();
            aux.setBusinessentityid(10);
            when(restTemplate.getForObject(URL_EMPLOYEE+aux.getBusinessentityid(), Employee.class)).thenReturn(aux);
            assertEquals(businessDelegate.findByIdEmployeee(aux.getBusinessentityid()).getBusinessentityid(), aux.getBusinessentityid());
        }

        @Test
        void updateEmployeeTest(){
            Employee aux = new Employee();
            businessDelegate.addEmployee(aux);
            aux.setVacationhours(10);
            businessDelegate.updateEmployee(aux);

            verify(restTemplate).put(URL_EMPLOYEE+aux.getBusinessentityid(), aux, Employee.class);
        }

        @Test
        void deleteEmployeeTest(){
            Employee aux = new Employee();
            businessDelegate.addEmployee(aux);
            businessDelegate.deleteEmployee(aux);

            verify(restTemplate).delete(URL_EMPLOYEE+aux.getBusinessentityid());
        }
    }

    @Nested
    @DisplayName("Test Class for Person")
    class PersonTest{

        @BeforeEach
        void setup(){
            Person person = new Person();
            person.setFirstname("Peter");
            person.setMiddlename("Federico");
            person.setLastname("Alexander");
            person.setTitle("Natural");

            businessDelegate = new BusinessDelegate();
            businessDelegate.setRestTemplate(restTemplate);
        }

        @Test
        void getAllPersontest(){
            Person[] people = new Person[5];
            for (int i = 0; i < people.length; i++){
                Person aux = new Person();
                people[i] = aux;
                businessDelegate.addPerson(aux);
            }
            when(restTemplate.getForObject(URL_PERSON,Person[].class)).thenReturn(people);
            assertEquals(businessDelegate.getPersons().size(), people.length);
        }

        @Test
        void findByIdPersonTest(){
            Person aux = new Person();
            aux.setBusinessentityid(10);
            when(restTemplate.getForObject(URL_PERSON+aux.getBusinessentityid(), Person.class)).thenReturn(aux);
            assertEquals(businessDelegate.findByIdPerson(aux.getBusinessentityid()).getBusinessentityid(), aux.getBusinessentityid());
        }

        @Test
        void addPersonTest(){
            Person temp = new Person();
            temp.setFirstname("Peter");
            temp.setMiddlename("Federico");
            temp.setLastname("Alexander");
            temp.setTitle("Natural");
            temp.setEmailpromotion(50);

            when(restTemplate.postForObject(URL_PERSON, temp, Person.class)).thenReturn(temp);
            assertEquals(businessDelegate.addPerson(temp).getBusinessentityid(), temp.getBusinessentityid());
        }

//        @Test
//        void updatePersonTest(){
//            Person aux = new Person();
//            businessDelegate.addPerson(aux);
//            person.setTitle("aux");
//            businessDelegate.updatePerson(aux);
//
//            verify(restTemplate).put(URL_PERSON+person.getBusinessentityid(), aux, Person.class);
//        }

        @Test
        void deletePersonTest(){
            Person aux = new Person();
            businessDelegate.addPerson(aux);
            businessDelegate.deletePerson(aux);

            verify(restTemplate).delete(URL_PERSON+aux.getBusinessentityid());
        }
    }

    @Nested
    @DisplayName("Test Class for SaleTaxRate")
    class SaleTaxRateTest{

        @BeforeEach
        void setup(){
            Salestaxrate salestaxrate = new Salestaxrate();
            salestaxrate.setName("TAXRATE");
            salestaxrate.setTaxrate(new BigDecimal(500));

            businessDelegate = new BusinessDelegate();
            businessDelegate.setRestTemplate(restTemplate);
        }

        @Test
        void getSalesTaxRateTest(){
            Salestaxrate[] salestaxrates = new Salestaxrate[5];
            for(int i = 0; i < salestaxrates.length; i++){
                Salestaxrate aux = new Salestaxrate();
                salestaxrates[i] = aux;
                businessDelegate.addSalestaxrate(aux);
            }
            when(restTemplate.getForObject(URL_SALESTAX, Salestaxrate[].class)).thenReturn(salestaxrates);
            assertEquals(businessDelegate.getSalestaxrate().size(), salestaxrates.length);
        }

        @Test
        void findByIdSalesTaxRateTest(){
            Salestaxrate aux = new Salestaxrate();
            aux.setSalestaxrateid(10);
            when(restTemplate.getForObject(URL_SALESTAX+aux.getSalestaxrateid(), Salestaxrate.class)).thenReturn(aux);
            assertEquals(businessDelegate.findByIdSalesTax(aux.getSalestaxrateid()).getSalestaxrateid(), aux.getSalestaxrateid());
        }

        @Test
        void addSalesTaxRateTest(){
            Salestaxrate temp = new Salestaxrate();
            temp.setName("TAXRATE");
            temp.setTaxrate(new BigDecimal(1500));

            when(restTemplate.postForObject(URL_SALESTAX, temp, Salestaxrate.class)).thenReturn(temp);
            assertEquals(businessDelegate.addSalestaxrate(temp).getSalestaxrateid(), temp.getSalestaxrateid());
        }

        @Test
        void updateSalesTaxRateTest(){
            Salestaxrate aux = new Salestaxrate();
            businessDelegate.addSalestaxrate(aux);
            aux.setName("RATE");
            businessDelegate.updateSalestaxrate(aux);

            verify(restTemplate).put(URL_SALESTAX+aux.getSalestaxrateid(), aux, Salestaxrate.class);
        }

//        @Test
//        void deleteSalesTaxRateTest(){
//            Salestaxrate aux = new Salestaxrate();
//            businessDelegate.addSalestaxrate(aux);
//            businessDelegate.deleteSalestaxrate(aux);
//
//            verify(restTemplate).delete(URL_SALESTAX+salestaxrate.getSalestaxrateid());
//        }
    }

    @Nested
    @DisplayName("Test Class for SalesTerritory")
    class SalesTerritoryTest{

        @BeforeEach
        void setup(){
            Salesterritory salesterritory = new Salesterritory();
            salesterritory.setCostlastyear(new BigDecimal(500));
            salesterritory.setCountryregioncode("COL");
            salesterritory.setName("TERRITORY");
            salesterritory.setCostytd(new BigDecimal(200));

            businessDelegate = new BusinessDelegate();
            businessDelegate.setRestTemplate(restTemplate);
        }

        @Test
        void getAllSalesTerritoryTest(){
            Salesterritory[] salesterritories = new Salesterritory[5];
            for(int i = 0; i < salesterritories.length; i++){
                Salesterritory aux = new Salesterritory();
                salesterritories[i] = aux;
                businessDelegate.addSalesTerritory(aux);
            }
            when(restTemplate.getForObject(URL_SALESTERRITORY, Salesterritory[].class)).thenReturn(salesterritories);
            assertEquals(businessDelegate.getSalesTerritory().size(), salesterritories.length);
        }

        @Test
        void findByIdSalesTerritoryTest(){
            Salesterritory aux = new Salesterritory();
            aux.setTerritoryid(10);
            when(restTemplate.getForObject(URL_SALESTERRITORY+aux.getTerritoryid(), Salesterritory.class)).thenReturn(aux);
            assertEquals(businessDelegate.findByIdSalesTerritory(aux.getTerritoryid()).getTerritoryid(), aux.getTerritoryid());
        }

        @Test
        void addSalesTerritoryTest(){
            Salesterritory temp = new Salesterritory();
            temp.setCostlastyear(new BigDecimal(250));
            temp.setCountryregioncode("PACIFIC");
            temp.setName("TERR");
            temp.setCostytd(new BigDecimal(102));

            when(restTemplate.postForObject(URL_SALESTERRITORY,temp, Salesterritory.class)).thenReturn(temp);
            assertEquals(businessDelegate.addSalesTerritory(temp).getTerritoryid(), temp.getTerritoryid());
        }

//        @Test
//        void updateSalesTerritoryTest(){
//            Salesterritory aux = new Salesterritory();
//            businessDelegate.addSalesTerritory(aux);
//            aux.setCountryregioncode("COUNTRY000");
//            businessDelegate.updateSalesTerritory(aux);
//
//            verify(restTemplate).put(URL_SALESTERRITORY+aux.getTerritoryid(), Salesterritory.class);
//        }

        @Test
        void deleteSalesTerritoryTest(){
            Salesterritory aux = new Salesterritory();
            businessDelegate.addSalesTerritory(aux);
            businessDelegate.deleteSalesTeritory(aux);

            verify(restTemplate).delete(URL_SALESTERRITORY+aux.getTerritoryid());
        }
    }

    @Nested
    @DisplayName("Test Class for StateProvince")
    class StateProvinceTest{

        @BeforeEach
        void setup(){
            Stateprovince stateprovince = new Stateprovince();
            stateprovince.setStateprovincecode("PROV05");
            stateprovince.setName("PROVINCE");

            businessDelegate = new BusinessDelegate();
            businessDelegate.setRestTemplate(restTemplate);
        }

        @Test
        void getAllStateProvinceTest(){
            Stateprovince[] stateprovinces = new Stateprovince[5];
            for(int i = 0; i < stateprovinces.length; i++){
                Stateprovince aux = new Stateprovince();
                stateprovinces[i] = aux;
                businessDelegate.addStateProvince(aux);
            }
            when(restTemplate.getForObject(URL_STATEPROVINCE, Stateprovince[].class)).thenReturn(stateprovinces);
            assertEquals(businessDelegate.getStateProvinces().size(), stateprovinces.length);
        }

        @Test
        void findByIdStateProvinceTest(){
            Stateprovince aux = new Stateprovince();
            aux.setStateprovinceid(10);
            when(restTemplate.getForObject(URL_STATEPROVINCE+aux.getStateprovinceid(), Stateprovince.class)).thenReturn(aux);
            assertEquals(businessDelegate.findByIdStateProvince(aux.getStateprovinceid()).getStateprovinceid(), aux.getStateprovinceid());
        }

        @Test
        void addStateProvinceTest(){
            Stateprovince temp = new Stateprovince();
            temp.setStateprovincecode("PROV05");
            temp.setName("PROVINCE");

            when(restTemplate.postForObject(URL_STATEPROVINCE,temp, Stateprovince.class)).thenReturn(temp);
            assertEquals(businessDelegate.addStateProvince(temp).getStateprovinceid(), temp.getStateprovinceid());
        }

//        @Test
//        void updateStateProvinceTest(){
//            Stateprovince aux = new Stateprovince();
//            businessDelegate.addStateProvince(aux);
//            aux.setStateprovincecode("PROV24");
//            businessDelegate.updateStateProvince(aux);
//
//            verify(restTemplate).put(URL_STATEPROVINCE+aux.getStateprovinceid(), Stateprovince.class);
//        }

        @Test
        void deleteStateProvinceTest(){
            Stateprovince aux = new Stateprovince();
            businessDelegate.addStateProvince(aux);
            businessDelegate.deleteStateProvince(aux);

            verify(restTemplate).delete(URL_STATEPROVINCE+aux.getStateprovinceid());
        }
    }

    @Nested
    @DisplayName("Test Class for SalesTerritory")
    class UserAppTest{

        @BeforeEach
        void setup(){
            UserApp userApp = new UserApp();
            userApp.setUsername("UserNew");
            userApp.setPassword("0000");

            businessDelegate = new BusinessDelegate();
            businessDelegate.setRestTemplate(restTemplate);
        }

        @Test
        void getAllUserAppTest(){
            UserApp[] userApps = new UserApp[5];
            for(int i = 0; i < userApps.length; i++){
                UserApp aux = new UserApp();
                userApps[i] = aux;
                businessDelegate.addUser(aux);
            }
            when(restTemplate.getForObject(URL_USERAPP, UserApp[].class)).thenReturn(userApps);
            assertEquals(businessDelegate.getUsers().size(), userApps.length);
        }

        @Test
        void findByIdUserAppTest(){
            UserApp aux = new UserApp();
            aux.setId(10);
            when(restTemplate.getForObject(URL_USERAPP+aux.getId(), UserApp.class)).thenReturn(aux);
            assertEquals(businessDelegate.findByIdUser(aux.getId()).getId(), aux.getId());
        }

        @Test
        void addUserAppTest(){
            UserApp temp = new UserApp();
            temp.setUsername("NEWUSER");
            temp.setPassword("1234");

            when(restTemplate.postForObject(URL_USERAPP,temp, UserApp.class)).thenReturn(temp);
            assertEquals(businessDelegate.addUser(temp).getId(), temp.getId());
        }

//        @Test
//        void updateUserAppTest(){
//            UserApp aux = new UserApp();
//            businessDelegate.addUser(aux);
//            aux.setPassword("COL000");
//            businessDelegate.updateUser(aux);
//
//            verify(restTemplate).put(URL_USERAPP+aux.getId(), UserApp.class);
//        }

        @Test
        void deleteUserAppTest(){
            UserApp aux = new UserApp();
            businessDelegate.addUser(aux);
            businessDelegate.deleteUser(aux);

            verify(restTemplate).delete(URL_USERAPP+aux.getId());
        }
    }

}
