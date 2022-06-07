package com.icesi.samaca.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icesi.samaca.backend.model.person.Address;
import com.icesi.samaca.backend.model.person.Countryregion;
import com.icesi.samaca.backend.model.person.Stateprovince;
import com.icesi.samaca.backend.repositories.AddressRepository;
import com.icesi.samaca.backend.repositories.StateprovinceRepository;

@Service
public class AddresServiceImp implements AddressService {

	AddressRepository addrRepos;
	StateprovinceRepository staprovRepos;


	@Autowired
	public AddresServiceImp(AddressRepository aRepo, StateprovinceRepository staRepos) {
		addrRepos = aRepo;
		staprovRepos = staRepos;
	}




	@Override
	public Address saveAddress(Address addr) throws IllegalArgumentException {

		if (addr != null && (addr.getAddressline1() !=null && !addr.getAddressline1().isBlank()) && 
				(!addr.getCity().isBlank() && addr.getCity()!=null && (addr.getCity().length()>=3)) && 
				(addr.getPostalcode().length()==6))
		{	
			Optional<Stateprovince> stateProvinceChecker = this.staprovRepos.findById(addr.getStateprovince().getStateprovinceid());

			if(stateProvinceChecker.isPresent()) {
				addr.setStateprovince(stateProvinceChecker.get());
				addrRepos.save(addr);
			}else {
				throw new IllegalArgumentException("Hubo un error en la creacion, revise los datos");

			}
		}else {
			throw new IllegalArgumentException("Hubo un error en la creacion, revise los datos");
		}



		return addr;
	}

	@Override
	@Transactional
	public Address editAddres(Address addr) throws IllegalArgumentException{
		Address aux= null;

				Optional<Address> stateProvinceChecker = this.addrRepos.findById(addr.getAddressid());

				if(stateProvinceChecker.isPresent()) {
					aux= saveAddress(addr);

				}else {
					throw new IllegalArgumentException("Hubo un error en la edición, revise los datos");
				}
		return aux;
	}
	
	public Optional<Address> findById(Integer id){
		return addrRepos.findById(id);
	}
	
	public Iterable<Address> findAll(){
		return addrRepos.findAll();
	}
	
	public Iterable<Stateprovince> findAllStateProvinces(){
		return staprovRepos.findAll();
	}
	public Iterable<Address> findByStateprov(Integer id){
		Stateprovince stateprov= staprovRepos.findById(id).get();
		List<Address> addressList= stateprov.getAddresses();
		Iterable<Address> addressIterable= addressList;
		
		return addressIterable;
	}




	@Override
	public Address deleteAddress(Integer addressid) {
		Optional<Address >result = addrRepos.findById(addressid);
		addrRepos.delete(addrRepos.findById(addressid).get());
		return result.get();
	}
	

}
