package com.icesi.samaca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icesi.samaca.model.person.Countryregion;
import com.icesi.samaca.services.CountryregionServiceImp;
import com.icesi.samaca.services.SalestaxrateServiceImp;
import com.icesi.samaca.validation.CountryRegionValidation;

@Controller
public class adminControllerImp implements adminController{

	private CountryregionServiceImp countryRegionService;
	private SalestaxrateServiceImp salestaxrateService;
	
	@Autowired
	public adminControllerImp(CountryregionServiceImp countryregionService, SalestaxrateServiceImp salestaxrateService) {	
	this.countryRegionService = countryregionService;
	this.salestaxrateService = salestaxrateService;
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
			return"/admin/add_countryregion";
			
		}else {
			countryRegionService.saveCr(countryregion);
			return "redirect:/countryregion";
		}
	} 
	
	
	@GetMapping("/salestaxrate")
	public String salestaxrate(Model model) {
		return "admin/salestaxrate";
	}
	
}
