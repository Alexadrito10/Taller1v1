package com.icesi.samaca.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.samaca.model.person.Address;
import com.icesi.samaca.model.person.Stateprovince;
import com.icesi.samaca.repositories.AddressRepository;
import com.icesi.samaca.repositories.StateprovinceRepository;

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
	public Address saveAddress(Address addr, Integer id) throws IllegalArgumentException {

		if (addr != null && (addr.getAddressline1() !=null && !addr.getAddressline1().isBlank()) && 
				(!addr.getCity().isBlank() && addr.getCity()!=null && (addr.getCity().length()>=3)) && 
				(addr.getPostalcode().length()==6))
		{	
			Optional<Stateprovince> stateProvinceChecker = this.staprovRepos.findById(id);

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
	public Address editAddres(Address addr, Integer id) throws IllegalArgumentException{
		Address aux= null;

				Optional<Stateprovince> stateProvinceChecker = this.staprovRepos.findById(id);

				if(stateProvinceChecker.isPresent()) {
//					Address toEdit = addrRepos.getById(addr.getAddressid());
//					//toEdit.setStateprovince(addr.getStateprovince());
//					toEdit.setAddressline1(addr.getAddressline1());
//					toEdit.setAddressline2(addr.getAddressline2());
//					toEdit.setCity(addr.getCity());
//					toEdit.setModifieddate(addr.getModifieddate());
//					toEdit.setPostalcode(addr.getPostalcode());
//					toEdit.setRowguid(addr.getRowguid());
//					toEdit.setSpatiallocation(addr.getSpatiallocation());
					aux= saveAddress(addr, id);

				}else {
					throw new IllegalArgumentException("Hubo un error en la edición, revise los datos");
				}
		return aux;
	}

}
