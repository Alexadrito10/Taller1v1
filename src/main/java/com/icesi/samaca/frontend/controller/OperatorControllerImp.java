package com.icesi.samaca.frontend.controller;

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

import com.icesi.samaca.backend.model.person.Address;
import com.icesi.samaca.backend.model.person.Stateprovince;
import com.icesi.samaca.backend.services.AddresServiceImp;
import com.icesi.samaca.backend.services.CountryregionServiceImp;
import com.icesi.samaca.backend.services.StateprovinceServiceImp;
import com.icesi.samaca.backend.validation.AddressValidation;
import com.icesi.samaca.backend.validation.StateProvinceValidation;
import com.icesi.samaca.frontend.businessdelegate.BusinessDelegate;

@Controller
public class OperatorControllerImp{
	@Autowired
	private BusinessDelegate bDelegate;
	
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
		model.addAttribute("address", bDelegate.getAdresses());
		
		return "operator/address";
	}
	
	@GetMapping("/address/add")
	public String saveAddress(Model model){
		model.addAttribute("address", new Address());
		model.addAttribute("stateprovinces", bDelegate.getStateProvinces());
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
			bDelegate.addAddress(address);
			return "redirect:/address/";
		}
	}
	
	@GetMapping("/address/update/{id}")
	public String updateAddress(@PathVariable("id")Integer id, Model model){
		Address address= bDelegate.findByIdAddress(id);
//		if(address!=null){
//			throw new IllegalArgumentException("No hay una dirección creada con este id");
//		}
		model.addAttribute("address", address);
		model.addAttribute("statesprovinces", bDelegate.getStateProvinces());
		return "operator/update-address";
	}
	
	@PostMapping("/address/update/{id}")
	public String updateAddress(@PathVariable("id")Integer id, @Validated(AddressValidation.class)Address address, BindingResult bindingResult,
			Model model, @RequestParam(value="action", required= true)String action){
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("address", bDelegate.findByIdAddress(id));
				model.addAttribute("statesprovince", bDelegate.getStateProvinces());
				return "operator/update-address";
			}
			address.setAddressid(id);
			bDelegate.updateAddress(address);
			//addressService.editAddres(address);
		}
		return "redirect:/address/";
	}
	
	@GetMapping("/stateprovince")
	public String stateprovince(Model model){
		model.addAttribute("stateprovince", bDelegate.getStateProvinces());
		return "operator/stateprovince";
	}
	
	@GetMapping("/stateprovince/add")
	public String saveStateprovince(Model model){
		model.addAttribute("stateprovince", new Stateprovince());
		model.addAttribute("countryregions", bDelegate.getCountries());
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
			model.addAttribute("countryregion", bDelegate.getCountries());
			return "operator/add-stateprovince";
		}else {
			this.bDelegate.addStateProvince(stateprovince);
//			stateprovinceService.saveStateprov(stateprovince);
			return "redirect:/stateprovince";
		}
	}

	@GetMapping("/stateprovince/update/{id}")
	public String updateStateProvince(@PathVariable("id")Integer id, Model model) {
		Stateprovince province= bDelegate.findByIdStateProvince(id);
		//Optional<Stateprovince> province= stateprovinceService.findById(id);
		if(province.equals(null)) {
			throw new IllegalArgumentException("No hay un estado provincia creado con este id");
		}
		model.addAttribute("stateprovince", province);
		model.addAttribute("countryregions", bDelegate.getCountries());
		return "operator/update-stateprovince";
	}
	
	@PostMapping("/stateprovince/update/{id}")
	public String updateStateProvince(@PathVariable("id")Integer id, @Validated(StateProvinceValidation.class)Stateprovince stateprovince, BindingResult bindingResult,
			Model model, @RequestParam(value="action", required= true)String action) {
		if(!action.equals("Cancel")){
			if(bindingResult.hasErrors()){
				model.addAttribute("stateprovince", stateprovince);
				model.addAttribute("countryregion", bDelegate.getCountries());
				return "operator/update-stateprovince";
			}
			stateprovince.setStateprovinceid(id);
			//stateprovinceService.editStateproV(stateprovince);
			bDelegate.updateStateProvince(stateprovince);
		}
		return "redirect:/stateprovince";
	}
	
	@GetMapping("/address/{id}")
	public String addressesRefs(@PathVariable("id")Integer id, Model model) {
		model.addAttribute("address", addressService.findByStateprov(id));
		return "operator/addresses-refs";
	}
}
