package com.osintegrators.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository repository;
	
	
	public void createAddress(Address address) {
		repository.save(address);

	}

	public Address getAddressByName(String expectedName) {
		Address address = repository.findByName(expectedName);
		return address;
	}

	public void deleteAddress(Address address) {
		repository.delete(address);
	}

	public List<Address> getAllAddresses() {
		return repository.findAll();
	}

}
