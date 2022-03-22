package com.icesi.samaca.services;

import org.springframework.stereotype.Service;

import com.icesi.samaca.model.person.Address;
import com.icesi.samaca.repositories.AddressRepository;
import com.icesi.samaca.repositories.StateprovinceRepository;

@Service
public class AddresServiceImp implements AddressService {

	AddressRepository addrRepos;
	StateprovinceRepository staprovRepos;



	public AddresServiceImp(AddressRepository aRepo, StateprovinceRepository staRepos) {


		addrRepos = aRepo;
		staprovRepos = staRepos;
	}




	@Override
	public Address saveAddress(Address addr) {

		try {
			if ((addr.getAddressline1() !=null) && (addr.getCity().length()>=3) && 
					(addr.getPostalcode().length()==6)&& 
					(staprovRepos.findById(addr.getStateprovince().getStateprovinceid()).isPresent())) 
			{	
				addr.setStateprovince(staprovRepos.getById(addr.getStateprovince().getStateprovinceid()));
				addrRepos.save(addr);


			}else {
				throw new IllegalArgumentException();
			}
			
		}catch (IllegalArgumentException e) {
			System.out.println("Hubo un error en la creacion, revise los datos");

		}

		return addr;
	}

	@Override
	public boolean editAddres(Address addr) {
		boolean result = false;
		
		try {
			if ((addr.getAddressline1() !=null) && (addr.getCity().length()>=3) && 
					(addr.getPostalcode().length()==6)&& 
					(staprovRepos.findById(addr.getStateprovince().getStateprovinceid()).isPresent())) 
			{
				Address toEdit = addrRepos.getById(addr.getAddressid());
				toEdit.setStateprovince(staprovRepos.getById(addr.getStateprovince().getStateprovinceid()));
				toEdit.setAddressline1(addr.getAddressline1());
				toEdit.setAddressline2(addr.getAddressline2());
				toEdit.setCity(addr.getCity());
				toEdit.setModifieddate(addr.getModifieddate());
				toEdit.setPostalcode(addr.getPostalcode());
				toEdit.setRowguid(addr.getRowguid());
				toEdit.setSpatiallocation(addr.getSpatiallocation());
				addrRepos.save(toEdit);
			}
			
			
			
		}catch (IllegalArgumentException e) {
			System.out.println("Hubo un error en la creacion, revise los datos");

		}
		
		return result;
	}

}
