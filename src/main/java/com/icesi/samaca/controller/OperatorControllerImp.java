package com.icesi.samaca.controller;

import java.util.Optional;

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

import com.icesi.samaca.model.person.Address;
import com.icesi.samaca.model.person.Stateprovince;
import com.icesi.samaca.services.AddresServiceImp;
import com.icesi.samaca.services.CountryregionServiceImp;
import com.icesi.samaca.services.StateprovinceServiceImp;
import com.icesi.samaca.validation.AddressValidation;
import com.icesi.samaca.validation.StateProvinceValidation;

@Controller
public class OperatorControllerImp{
	
	private AddresServiceImp addressService;
	private StateprovinceServiceImp stateprovinceService;
	private CountryregionServiceImp countryRegionService;
	
	@Autowired
	public OperatorControllerImp(AddresServiceImp addressService, StateprovinceServiceImp stateprovinceService,CountryregionServiceImp countryRegionService ) {
		this.addressService= addressService;
		this.stateprovinceService= stateprovinceService;
		this.countryRegionService =countryRegionService;
	}
	
	@GetMapping("/operator")
	public String index(Model model) {
		return "operator/index";
	}
	
	@GetMapping("/address")
	public String address(Model model) {
		model.addAttribute("address", addressService.findAll());
		
		return "operator/address";
	}
	
	@GetMapping("/address/add")
	public String saveAddress(Model model){
		model.addAttribute("address", new Address());
		model.addAttribute("stateprovinces", addressService.findAllStateProvinces());
		return "operator/add-address";
	}
	
	@PostMapping("/address/add")
	public String saveAddress(@Validated(AddressValidation.class) @ModelAttribute Address address, BindingResult bindingResult, Model model,
			@RequestParam(value="action", required= true)String action){
		if(action.equals("Cancel")){
			return "redirect:/address/";
		}
		if(bindingResult.hasErrors()){
			model.addAttribute("address", address);
			model.addAttribute("stateprovince");
			return "/operator/add-address";
		}else {
			addressService.saveAddress(address);
			return "redirect:/address/";
		}
	}
	
	@GetMapping("/address/update/{id}")
	public String updateAddress(@PathVariable("id")Integer id, Model model){
		Optional<Address> address= addressService.findById(id);
		if(address.isEmpty()){
			throw new IllegalArgumentException("No hay una dirección creada con este id");
		}
		model.addAttribute("address", address.get());
		model.addAttribute("statesprovinces", addressService.findAllStateProvinces());
		return "operator/update-address";
	}
	
	@PostMapping("/address/update/{id}")
	public String updateAddress(@PathVariable("id")Integer id, @Validated(AddressValidation.class)Address address, BindingResult bindingResult,
			Model model, @RequestParam(value="action", required= true)String action){
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("address", addressService.findById(id).get());
				model.addAttribute("statesprovince", addressService.findAllStateProvinces());
				return "operator/update-address";
			}
			address.setAddressid(id);
			addressService.editAddres(address);
		}
		return "redirect:/address/";
	}
	
	@GetMapping("/stateprovince")
	public String stateprovince(Model model){
		model.addAttribute("stateprovince", stateprovinceService.findAll());
		return "operator/stateprovince";
	}
	
	@GetMapping("/stateprovince/add")
	public String saveStateprovince(Model model){
		model.addAttribute("stateprovince", new Stateprovince());
		model.addAttribute("countryregions", countryRegionService.findAll());
		return "operator/add-stateprovince";
	}
	
	@PostMapping("/stateprovince/add")
	public String saveStateprovince(@Validated(StateProvinceValidation.class) @ModelAttribute Stateprovince stateprovince, BindingResult bindingResult,
			Model model, @RequestParam(value="action", required= true)String action) {
		if(action.equals("Cancel")) {
			return "redirect:/stateprovince";
		}
		if(bindingResult.hasErrors()) {
			model.addAttribute("stateprovince", stateprovince);
			model.addAttribute("countryregion");
			return "operator/add-stateprovince";
		}else {
			stateprovinceService.saveStateprov(stateprovince, stateprovince.getCountryregion().getCountryregionid());
			return "redirect:/stateprovince";
		}
	}

	@GetMapping("/stateprovince/update/{id}")
	public String updateStateProvince(@PathVariable("id")Integer id, Model model) {
		Optional<Stateprovince> province= stateprovinceService.findById(id);
		if(province.isEmpty()) {
			throw new IllegalArgumentException("No hay un estado provincia creado con este id");
		}
		model.addAttribute("stateprovince", province.get());
		model.addAttribute("countryregions", stateprovinceService.findAllCountries());
		return "operator/update-stateprovince";
	}
	
	@PostMapping("/stateprovince/update/{id}")
	public String updateStateProvince(@PathVariable("id")Integer id, @Validated(StateProvinceValidation.class)Stateprovince stateprovince, BindingResult bindingResult,
			Model model, @RequestParam(value="action", required= true)String action) {
		if(!action.equals("Cancel")){
			if(bindingResult.hasErrors()){
				model.addAttribute("stateprovince", stateprovinceService.findById(id).get());
				model.addAttribute("countryregion", stateprovinceService.findAllCountries());
				return "operator/update-stateprovince";
			}
			stateprovince.setStateprovinceid(id);
			stateprovinceService.editStateproV(stateprovince, stateprovince.getCountryregion().getCountryregionid());
		}
		return "redirect:/stateprovince";
	}
}
