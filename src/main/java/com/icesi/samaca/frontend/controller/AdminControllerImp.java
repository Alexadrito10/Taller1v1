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

import com.icesi.samaca.backend.model.person.Countryregion;
import com.icesi.samaca.backend.model.sales.Salestaxrate;
import com.icesi.samaca.backend.repositories.StateprovinceRepository;
import com.icesi.samaca.backend.services.CountryregionServiceImp;
import com.icesi.samaca.backend.services.SalestaxrateServiceImp;
import com.icesi.samaca.backend.services.StateprovinceServiceImp;
import com.icesi.samaca.backend.validation.CountryRegionValidation;
import com.icesi.samaca.backend.validation.SalesTaxRateValidation;

@Controller
public class AdminControllerImp{

	private CountryregionServiceImp countryRegionService;
	private SalestaxrateServiceImp salestaxrateService;
	private StateprovinceServiceImp stateprovinceService;
	
	@Autowired
	public AdminControllerImp(CountryregionServiceImp countryregionService, SalestaxrateServiceImp salestaxrateService,StateprovinceServiceImp stateprovinceService) {	
	this.countryRegionService = countryregionService;
	this.salestaxrateService = salestaxrateService;
	this.stateprovinceService = stateprovinceService;
	}
	
	@GetMapping("/admin")
	public String index(Model model) {
		return "admin/index";
	}
	
	@GetMapping("/countryregion")
	public String countryregion(Model model) {
		model.addAttribute("countryregion", countryRegionService.findAll());
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
			countryRegionService.saveCr(countryregion);
			return "redirect:/countryregion";
		}
	} 
	
	@GetMapping("/countryregion/update/{id}")
	public String updateCountryRegion(@PathVariable("id")Integer id, Model model){
		Optional<Countryregion> country =  countryRegionService.findById(id);
		if(country.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		model.addAttribute("countryregion", country.get());
		return "admin/update-countryregion";
	}
	
	@PostMapping("/countryregion/update/{id}")
	public String updateCountryRegion(@PathVariable("id")Integer id, @Validated(CountryRegionValidation.class)Countryregion countryregion,
			BindingResult bindingResult, Model model, @RequestParam(value="action", required= true) String action){
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("countryregion", countryRegionService.findById(id).get());
				return "admin/update-countryregion";
			}
			countryregion.setCountryregionid(id);
			countryRegionService.editCr(countryregion);
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
	
}
