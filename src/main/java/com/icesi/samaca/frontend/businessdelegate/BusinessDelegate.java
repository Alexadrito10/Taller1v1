package com.icesi.samaca.frontend.businessdelegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.icesi.samaca.backend.model.hr.Employee;
import com.icesi.samaca.backend.model.person.Address;
import com.icesi.samaca.backend.model.person.Countryregion;

import lombok.Getter;
import lombok.Setter;

@Component
public class BusinessDelegate {
	
	private final String URL_ADDRESS = "http://localhost:8080/api/addresses/list";
	private final String URL_COUNTRY = "http://localhost:8080/api/countryregions/list";
	private final String URL_EMPLOYEE = "http://localhost:8080/api/employees/list";
	
	
	@Getter
	@Setter
	@Autowired
	private RestTemplate restTemplate;
	
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
	
	
	
	
	

}
