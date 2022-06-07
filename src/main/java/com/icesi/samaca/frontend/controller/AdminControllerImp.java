package com.icesi.samaca.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

import com.icesi.samaca.backend.model.hr.Employee;
import com.icesi.samaca.backend.model.person.Countryregion;
import com.icesi.samaca.backend.model.sales.Salestaxrate;
import com.icesi.samaca.backend.repositories.StateprovinceRepository;
import com.icesi.samaca.backend.services.CountryregionServiceImp;
import com.icesi.samaca.backend.services.EmployeeService;
import com.icesi.samaca.backend.services.EmployeeServiceImp;
import com.icesi.samaca.backend.services.PersonServiceImp;
import com.icesi.samaca.backend.services.SalestaxrateServiceImp;
import com.icesi.samaca.backend.services.StateprovinceServiceImp;
import com.icesi.samaca.backend.validation.CountryRegionValidation;
import com.icesi.samaca.backend.validation.SalesTaxRateValidation;
import com.icesi.samaca.frontend.businessdelegate.BusinessDelegate;
import com.icesi.samaca.backend.model.person.Person;

@Controller
public class AdminControllerImp{

	@Autowired
	private BusinessDelegate bDelegate;
	private CountryregionServiceImp countryRegionService;
	private SalestaxrateServiceImp salestaxrateService;
	private StateprovinceServiceImp stateprovinceService;
	private EmployeeServiceImp employeeService;
	private PersonServiceImp personService;
	
	@Autowired
	public AdminControllerImp(CountryregionServiceImp countryregionService, SalestaxrateServiceImp salestaxrateService,StateprovinceServiceImp stateprovinceService,EmployeeServiceImp employeeService,PersonServiceImp personServiceImp) {	
	this.countryRegionService = countryregionService;
	this.salestaxrateService = salestaxrateService;
	this.stateprovinceService = stateprovinceService;
	this.employeeService = employeeService;
	personService = personServiceImp;
	}
	
	@GetMapping("/admin")
	public String index(Model model) {
		return "admin/index";
	}
	
	@GetMapping("/countryregion")
	public String countryregion(Model model) {
		model.addAttribute("countryregion", bDelegate.getCountries());
		return "admin/countryregion";
		
	}
	@GetMapping("/countryregion/add")
	public String saveCountryRegion(Model model){
		model.addAttribute("countryregion",new Countryregion());
	
		return "admin/add-countryregion";
	}
	
	
	@PostMapping("/countryregion/add")
	public String saveCountryRegion(@Validated(CountryRegionValidation.class) @ModelAttribute Countryregion countryregion
			,BindingResult bindingResult, Model model,@RequestParam(value = "action",required = true) String action)throws Exception{
		if(action.equals("Cancel")) {
			return "redirect:/countryregion/";
		}
		if(bindingResult.hasErrors()) {
			model.addAttribute("countryregion", countryregion);
			return"/admin/add-countryregion";
			
		}else {
			bDelegate.addCountry(countryregion);
//			countryRegionService.saveCr(countryregion);
			return "redirect:/countryregion";
		}
	} 
	
	@GetMapping("/countryregion/update/{id}")
	public String updateCountryRegion(@PathVariable("id")Integer id, Model model){
		//Optional<Countryregion> country =  countryRegionService.findById(id);
		Countryregion country= bDelegate.findByIdCountryRegion(id);
		if(country.equals(null)){
			throw new IllegalArgumentException();
		}
		
		model.addAttribute("countryregion", country);
		return "admin/update-countryregion";
	}
	
	@PostMapping("/countryregion/update/{id}")
	public String updateCountryRegion(@PathVariable("id")Integer id, @Validated(CountryRegionValidation.class)Countryregion countryregion,
			BindingResult bindingResult, Model model, @RequestParam(value="action", required= true) String action){
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("countryregion", countryregion);
				return "admin/update-countryregion";
			}
			countryregion.setCountryregionid(id);
			bDelegate.updateCountry(countryregion);
//			countryRegionService.editCr(countryregion);
		}
		return "redirect:/countryregion";
	}
	
	@GetMapping("/salestaxrate")
	public String salestaxrate(Model model) {
		model.addAttribute("salestaxrate", salestaxrateService.findAll());
		return "admin/salestaxrate";
	}
	
	@GetMapping("/salestaxrate/add")
	public String saveSalestaxrate(Model model){
		model.addAttribute("salestaxrate", new Salestaxrate());
		System.out.println("taX: "+ salestaxrateService.findAll());
		System.out.println("state: " +stateprovinceService.findAll());
		model.addAttribute("stateprovinces", stateprovinceService.findAll());
		return "admin/add-salestaxrate";
	}
	
	@PostMapping("/salestaxrate/add")
	public String saveSalestaxrate(@Validated(SalesTaxRateValidation.class) @ModelAttribute Salestaxrate salestaxrate, BindingResult bindingResult,
			Model model, @RequestParam(value = "action",required = true) String action) {
		if(action.equals("Cancel")){
			return "redirect:/salestaxrate/";
			
		}
		if(bindingResult.hasErrors()){
			model.addAttribute("salestaxrate", salestaxrate);
			model.addAttribute("stateprovince");
			return "admin/add-salestaxrate";
			
		}else {
			salestaxrateService.saveSalesTR(salestaxrate);
			return "redirect:/salestaxrate/";
		}
		
	}
	
	@GetMapping("/salestaxrate/update/{id}")
	public String updateSalestaxrate(@PathVariable("id")Integer id, Model model){
		Optional<Salestaxrate> tax= salestaxrateService.findById(id);
		if(tax.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		model.addAttribute("salestaxrate", tax.get());
		model.addAttribute("stateprovinces", salestaxrateService.findAll());
		return "admin/update-salestaxrate";
	}
	
	@PostMapping("/salestaxrate/update/{id}")
	public String updateSalestaxrate(@PathVariable("id")Integer id, @Validated(SalesTaxRateValidation.class)Salestaxrate salestaxrate, BindingResult bindingResult,
			Model model, @RequestParam(value="action", required= true) String action){
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()){
				model.addAttribute("salestaxrate", salestaxrateService.findById(id).get());
				return "admin/update-salestaxrate";
			}
			salestaxrate.setSalestaxrateid(id);
			salestaxrateService.editSalesTR(salestaxrate);
		}
		return "redirect:/salestaxrate";
		
	}
	
	@GetMapping("/stateprovince/{id}")
	public String StateProvRefs(@PathVariable("id")Integer id, Model model) {
		model.addAttribute("stateprovince", stateprovinceService.findByCountry(id));
		return "admin/stateprov-refs";
	}
	
	@GetMapping("/employee")
	public String employee(Model model) {
		model.addAttribute("employee", employeeService.findAllEmployees());
		return "admin/employee";
		
	}

	@GetMapping("/employee/add")
	public String saveEmployee(Model model){
		model.addAttribute("employee",new Employee());
	
		return "admin/add-employee";
	}
	@PostMapping("/employee/add")
	public String saveEmployee( @ModelAttribute Employee employee
			,BindingResult bindingResult, Model model,@RequestParam(value = "action",required = true) String action)throws Exception{
		if(action.equals("Cancel")) {
			return "redirect:/employee/";
		}
		if(bindingResult.hasErrors()) {
			model.addAttribute("employee", employee);
			return"/admin/add-employee";
			
		}else {
			employeeService.save(employee);
			return "redirect:/employee";
		}
	} 
	
	@GetMapping("/employee/update/{id}")
	public String updateEmployee(@PathVariable("id")Integer id, Model model){
		Employee employee =  employeeService.findEmployeeById(id);
		if(employee == null) {
			throw new IllegalArgumentException();
		}
		
		model.addAttribute("employeee", employee);
		return "admin/update-employee";
	}
	
	@PostMapping("/employee/update/{id}")
	public String updateEmployee(@PathVariable("id")Integer id,Employee employee,
			BindingResult bindingResult, Model model, @RequestParam(value="action", required= true) String action){
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("employee", employeeService.findEmployeeById(id));
				return "admin/update-employee";
			}
			employee.setBusinessentityid(id);
			employeeService.update(employee);
		}
		return "redirect:/employee";
	}
	
	
	@GetMapping("/person")
	public String Person(Model model) {
		model.addAttribute("person", personService.findAllPersons());
		return "admin/person";
		
	}
	
	@GetMapping("/person/add")
	public String savePerson(Model model){
		model.addAttribute("person", new Person());
	
		return "admin/add-employee";
	}
	
	@PostMapping("/person/add")
	public String savePerson( @ModelAttribute Person person
			,BindingResult bindingResult, Model model,@RequestParam(value = "action",required = true) String action)throws Exception{
		if(action.equals("Cancel")) {
			return "redirect:/person/";
		}
		if(bindingResult.hasErrors()) {
			model.addAttribute("person", person);
			return"/admin/add-person";
			
		}else {
			personService.save(person);
			return "redirect:/person";
		}
	}
	@GetMapping("/person/update/{id}")
	public String updatePerson(@PathVariable("id")Integer id, Model model){
		Person person =  personService.findPersonById(id);
		if(person == null) {
			throw new IllegalArgumentException();
		}
		
		model.addAttribute("person", person);
		return "admin/update-person";
	}
	
	@PostMapping("/person/update/{id}")
	public String updatePerson(@PathVariable("id")Integer id,Person person,
			BindingResult bindingResult, Model model, @RequestParam(value="action", required= true) String action){
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("employee", personService.findPersonById(id));
				return "admin/update-person";
			}
			person.setBusinessentityid(id);
			personService.update(person);
		}
		return "redirect:/person";
	}
		
}
