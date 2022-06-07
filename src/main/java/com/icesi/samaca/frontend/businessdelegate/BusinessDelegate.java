package com.icesi.samaca.frontend.businessdelegate;

import java.util.Arrays;
import java.util.List;

import com.icesi.samaca.backend.model.person.*;
import com.icesi.samaca.backend.model.sales.Salestaxrate;
import com.icesi.samaca.backend.model.sales.Salesterritory;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.icesi.samaca.backend.model.hr.Employee;

import lombok.Getter;
import lombok.Setter;

@Component
public class BusinessDelegate {
	
	private final String URL_ADDRESS = "http://localhost:8080/api/addresses/list";
	private final String URL_COUNTRY = "http://localhost:8080/api/countryregions/list";
	private final String URL_EMPLOYEE = "http://localhost:8080/api/employees/list";
	private final String URL_PERSON = "http://localhost:8080/api/person/list";
	private final String URL_SALESTAX = "http://localhost:8080/api/salestaxrate/list";
	private final String URL_SALESTERRITORY = "http://localhost:8080/api/salesterritory/list";
	private final String URL_STATEPROVINCE = "http://localhost:8080/api/stateprovince/list";
	private final String URL_USERAPP = "http://localhost:8080/api/userapp/list";
	
	@Getter
	@Setter
	private RestTemplate restTemplate;

	public BusinessDelegate(){
		this.restTemplate = new RestTemplate();
	}
	
	//Address
	public List<Address> getAdresses(){
		Address[] addressArray = restTemplate.getForObject(URL_ADDRESS, Address[].class);
		return Arrays.asList(addressArray);
				
	}
	
	 public Address addAddress(Address a) {
		 return restTemplate.postForObject(URL_ADDRESS, a, Address.class);
	 }
	 //falta revisar esta
	 public void updateAddress(Address a) {
		 restTemplate.put(URL_ADDRESS+a.getAddressid(), a, Address.class);
		
	 }
	 
	 public void deleteAddress(Integer addressid) {
		 restTemplate.delete(URL_ADDRESS+addressid);
	 }
	 public Address findById(Integer addressid) {
		 return restTemplate.getForObject(URL_ADDRESS+addressid, Address.class);
		 
	 }
	 
	 //Countryregion
	 public List<Countryregion> getCountries(){
		 Countryregion[] countryRegionArray = restTemplate.getForObject(URL_COUNTRY, Countryregion[].class);
		 return Arrays.asList(countryRegionArray);
		 
	 }
	 public Countryregion addCountry(Countryregion cr){
		 return restTemplate.postForObject(URL_COUNTRY, cr,Countryregion.class);
		
	 }
	 
	 public void updateCountry(Countryregion cr){
		 restTemplate.put(URL_COUNTRY+cr.getCountryregionid(), cr,Countryregion.class);
		
	 }
	 
	 public void deleteCountry(Countryregion cr){
		 restTemplate.delete(URL_COUNTRY+cr.getCountryregionid());
		
	 }

	 //Employee
	 public List<Employee> getEmployees(){
		 Employee[] employeesArray = restTemplate.getForObject(URL_EMPLOYEE, Employee[].class);
		 return Arrays.asList(employeesArray);
		 
	 }
	 public Employee addEmployee(Employee e){
		 return restTemplate.postForObject(URL_EMPLOYEE, e,Employee.class);
		
	 }
	 
	 public void updateEmployee(Employee e){
		 restTemplate.put(URL_EMPLOYEE+e.getBusinessentityid(), e,Employee.class);
		
	 }
	 
	 public void deleteEmployee(Employee e){
		 restTemplate.delete(URL_EMPLOYEE+e.getBusinessentityid());
		
	 }

	 //Person
	public List<Person> getPerson(){
		Person[] people = restTemplate.getForObject(URL_PERSON, Person[].class);
		return Arrays.asList(people);
	}

	public Person addPerson(Person p){
		return restTemplate.postForObject(URL_PERSON, p, Person.class);
	}

	public void updatePerson(Person p){
		restTemplate.put(URL_PERSON+p.getBusinessentityid(), p, Person.class);
	}

	public void deletePerson(Person p){
		restTemplate.delete(URL_PERSON+p.getBusinessentityid());
	}

	//Salestaxrate
	public List<Salestaxrate> getSalestaxrate(){
		Salestaxrate[] salestaxrates = restTemplate.getForObject(URL_SALESTAX, Salestaxrate[].class);
		return Arrays.asList(salestaxrates);
	}

	public Salestaxrate addSalestaxrate(Salestaxrate s){
		return restTemplate.postForObject(URL_SALESTAX, s, Salestaxrate.class);
	}

	public void updateSalestaxrate(Salestaxrate s){
		restTemplate.put(URL_SALESTAX+s.getSalestaxrateid(), s, Salestaxrate.class);
	}

	public void deleteSalestaxrate(Salestaxrate s){
		restTemplate.delete(URL_SALESTAX+s.getSalestaxrateid());
	}

	//Salesterritory
	public List<Salesterritory> getSalesTerritory(){
		Salesterritory[] salesterritories = restTemplate.getForObject(URL_SALESTERRITORY, Salesterritory[].class);
		return Arrays.asList(salesterritories);
	}

	public Salesterritory addSalesTerritory(Salesterritory st){
		return restTemplate.postForObject(URL_SALESTERRITORY, st, Salesterritory.class);
	}

	public void updateSalesTerritory(Salesterritory st){
		restTemplate.put(URL_SALESTERRITORY+st.getTerritoryid(), st, Salesterritory.class);
	}

	public void deleteSalesTeritory(Salesterritory st){
        restTemplate.delete(URL_SALESTERRITORY+st.getTerritoryid());
	}

	//StateProvince
	public List<Stateprovince> getStateProvince(){
		Stateprovince[] stateprovinces = restTemplate.getForObject(URL_STATEPROVINCE, Stateprovince[].class);
		return Arrays.asList(stateprovinces);
	}

	public Stateprovince addStateProvince(Stateprovince sp){
		return restTemplate.postForObject(URL_STATEPROVINCE, sp, Stateprovince.class);
	}

	public void updateStateProvince(Stateprovince sp){
		restTemplate.put(URL_STATEPROVINCE+sp.getStateprovinceid(), sp, Stateprovince.class);
	}

	public void deleteStateProvince(Stateprovince sp){
		restTemplate.delete(URL_STATEPROVINCE+sp.getStateprovinceid());
	}

	//User
	public List<UserApp> getUsers(){
		UserApp[] users = restTemplate.getForObject(URL_USERAPP, UserApp[].class);
		return  Arrays.asList(users);
	}

	public UserApp addUser(UserApp user){
		return restTemplate.postForObject(URL_USERAPP, user, UserApp.class);
	}

	public void updateUser(UserApp user){
		restTemplate.put(URL_USERAPP+user.getId(), user, UserApp.class);
	}

	public void deleteUser(UserApp user){
		restTemplate.delete(URL_USERAPP+user.getId());
	}
}
