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

import com.icesi.samaca.model.person.Address;
import com.icesi.samaca.services.AddresServiceImp;
import com.icesi.samaca.services.StateprovinceServiceImp;
import com.icesi.samaca.validation.AddressValidation;

@Controller
public class operatorControllerImp{
	
	private AddresServiceImp addressService;
	private StateprovinceServiceImp stateprovinceService;
	
	@Autowired
	public operatorControllerImp(AddresServiceImp addressService, StateprovinceServiceImp stateprovinceService ) {
		this.addressService= addressService;
		this.stateprovinceService= stateprovinceService;
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
	
	@GetMapping("/")
	public String updateAddress() {
		return "";
	}
	
	
	

}
